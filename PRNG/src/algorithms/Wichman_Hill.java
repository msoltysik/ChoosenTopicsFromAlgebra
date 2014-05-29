package algorithms;
public abstract class Wichman_Hill {
	private static long xseed = System.currentTimeMillis() + System.identityHashCode(new Object());
	private static long yseed = System.currentTimeMillis() + System.identityHashCode(new Object());
	private static long zseed = System.currentTimeMillis() + System.identityHashCode(new Object());
	
	private static long x = xseed;
	private static long y = yseed;
	private static long z = zseed;
	private static long generator() {
		x = (171*x)%30269;
		y = (172*y)%30307;
		z = (170*z)%30323;
		return (x/30269 + y/30307 + z/30323);
	}
	/**
	 * 
	 * @return pseudorandom number between 0 and 1
	 */
	public static double getRandomNumber() {
		long u = generator();
		double randomNumber = u % 1.0;
		return randomNumber;
	}
	
	public static long getRandomNumber(long maxValue) {
		long randomNumber = generator();
		return randomNumber % maxValue;
	}
	
	public static long getRandomNumber(long minValue, long maxValue) {
		
		if (minValue > maxValue) {
			long tmp = minValue;
			minValue = maxValue;
			maxValue = tmp;
		}
		
		long randomNumber = generator();
		return minValue + (randomNumber % (maxValue - minValue + 1));
	}
}

