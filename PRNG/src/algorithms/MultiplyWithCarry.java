package algorithms;

public class MultiplyWithCarry {
	
	private final long a = 0xffffda61L;
	
	private long seed; 
	
	private int c = 3912021;
	
	private long x;
	
	private long b = 0xffffffffL;
	
	public MultiplyWithCarry(){
		seed = System.currentTimeMillis() + System.identityHashCode(new Object());
		x = seed;
	}
	
	
	public int nextInt() {
		long t = a*x + c;
		x = t % b;
		c = (int) (t/b);
		return (int) x;
 
	}
	
}
