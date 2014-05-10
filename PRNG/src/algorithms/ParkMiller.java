package algorithms;

// Park–Miller random number generator
@SuppressWarnings("ALL")
public class ParkMiller {
    private static final long max = ((long) 2 << 30) - 1;
    private static final long a = 16807;
    private static long seed = System.currentTimeMillis();

    public static long getRandomNumber() {
        seed = (a * seed) % max;
        return seed;
    }

    public static long getRandomNumber(long maxValue) {
        seed = getRandomNumber();
        return seed % (maxValue + 1);
    }

    public static long getRandomNumber(long minValue, long maxValue) {
        seed = getRandomNumber();
        return minValue + (seed % (maxValue - minValue + 1));
    }


}
