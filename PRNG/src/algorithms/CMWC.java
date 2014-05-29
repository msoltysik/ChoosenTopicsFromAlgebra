package algorithms;
 
import java.util.Random;
 
public class CMWC extends Random{
	private long	carry;
	private long	multiplier;
	private int	n = 0;
	private int	r;
	private long[]	seeds;

	public CMWC(){
		this(new long[] { System.currentTimeMillis() }, System.nanoTime(), 64, 987657110L);
	}
	 /**
	 * @param seeds
	 *            given seeds to use. Only r will be used. If fewer (or none)
	 *            are provided, the remaining seeds are generated
	 * @param carry
	 *            The initial carry value to use
	 * @param r
	 *            Number lag cycles to use. Must be a power of 2.
	 * @param multiplier
	 *            Multiplier to use. Depending on the r value chosen, different
	 *            multipliers are better. See class descriptor for good r and
	 *            multiplier value combinations.
	 */
	public CMWC(long[] seeds, long carry, int r, long multiplier){
		setR(r);
		setMultiplier(multiplier);
		this.seeds = new long[r];
		if (seeds == null || seeds.length == 0){
			seeds = new long[] { System.currentTimeMillis() };
		}
		this.carry = (carry & 0xFFFFFFFFL) % multiplier;
		for (int i = 0; i < r; ++i){
			if (i < seeds.length){
				this.seeds[i] = seeds[i] & 0xFFFFFFFFL;
			}
			else{
				this.seeds[i] = super.nextInt() & 0xFFFFFFFFL;
			}
			if (this.seeds[i] == 0xFFFFFFFFL){
				this.seeds[i] = 1L;
			}
		}
	}
 
	@Override
	protected int next(int bits)
	{
		final long t = multiplier * seeds[n] + carry;
		final long div32 = t >>> 32; 
		carry = div32 + ((t & 0xFFFFFFFFL) >= 0xFFFFFFFFL - div32 ? 1L : 0L);
		seeds[n] = 0xFFFFFFFEL - (t & 0xFFFFFFFFL) - (carry - div32 << 32) - carry & 0xFFFFFFFFL;
		final long result = seeds[n];
		n = n + 1 & r - 1;
		return (int) (result >>> 32 - bits);
	}
 
	private void setMultiplier(long multiplier){
		this.multiplier = multiplier;
	}
 
	private void setR(int r){
		if (r <= 0){
			r = 256; 
		}
		else{
			boolean validR = true;
			long a = r;
			while (a != 1 && validR){
				if (a % 2 != 0){
					r = 256;
					validR = false;
				}
				a >>>= 1;
			}
		}
		this.r = r;
	}
}
