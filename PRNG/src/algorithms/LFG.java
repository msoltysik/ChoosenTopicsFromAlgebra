package algorithms;

public class LFG {
	 
	/* Definicje wspolczynnikow j, k oraz m */
	private static final int j = 22;
	private static final int k = 43;
	private static final long m = ((long)2 << 31); /* 2^32 */
	 
	/* Definicja wspolczynnika a dla generatora LCG dla zainicjowania tablicy */
	private static final long a = 69069;
	 
	/* Tablica x - rejestr cykliczny oraz jej aktualny wskaznik */
	private static long x[] = new long[k];
	private static int i;
	 
	/* Zainicjuj tablice X - uzyj gneratora LCG o wspolczynnikach:
	m = 2^32, a = 69069, c = 0.
	dla takiego generatora wszystkie posrednie wyniki mieszcza sie w 49 bitach */
	public static void initX(long seed){  
		x[0] = seed;
		for (int ii=1; ii<k; ii++)
		x[ii] = (a*x[ii-1]) % m;
		i = 0;
	}  
	 
	//Calculate a*x mod m
	private static long mult(long a, long  x, long m) {
		long b,n,r;
		 
		r = 0;
		n = 1;
		b = 1;
		while (n <= 64) {
			if ((a & b) != 0)
				r = (r + x) % m;
				x = (x + x) % m;
				b *= 2;
				n++;
		}
		 
		return r;
	}  
	 
	/* Generator ALFG */
	public static long genRandALFG(){
		long result;
		 
		x[i] = (x[(k + i - j) % k] + x[i]) % m;
		result = x[i];
		i = (i + 1) % k;
		 
		return result;
	}
	 
	/* Generator MLFG */
	public static long genRandMLFG(){
		long result;
		 
		x[i] = mult(x[(k + i - j) % k], x[i], m);
		result = x[i];
		i = (i + 1) % k;
		 
		return result;
	}
	 
	/* Generator TGFSR */
	public static long genRandTGFSR(){
		long result;
		 
		x[i] = (x[(k + i - j) % k] ^ x[i]) % m;
		result = x[i];
		i = (i + 1) % k;
		 
		return result;
	}
	 
	 
	public static void main(String[] args) {
		int ii, n, g;
		long seed;
		 
		System.out.println("Podaj wartosc poczatkowa ziarna (seed)");
		seed = Console.readLong("");
		 
		System.out.println("Podaj ile liczb pseudolosowych wylosowac");
		n = Console.readInt("");
		 
		System.out.println("Podaj, ktory generator uzyc:");
		System.out.println("1-ALFG - Addytywny Opozniony Generator Fibonacciego");
		System.out.println("2-MLFG - Multiplikatywny Opozniony Generator Fibonacciego");
		System.out.println("3-TGFSR - Dwupunktowy uogolniony rejestr przesuwny ze sprzezeniem zwrotnym");
		g = Console.readInt("");
		 
		initX(seed);
		switch (g){
			case 1:
				for (ii=0; ii<n; ii++)
				System.out.println(genRandALFG());
				break;
			case 2:
				for (ii=0; ii<n; ii++)
				System.out.println(genRandMLFG());
				break;
			case 3:
				for (ii=0; ii<n; ii++)
				System.out.println(genRandTGFSR());
				break;
		}
	}
	 
}
