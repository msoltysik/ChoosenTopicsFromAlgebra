package algorithms;

@SuppressWarnings("UnusedDeclaration")
public class Xorshift {
    private static final long max = ((long) 2 << 30) - 1;
    private static long seed = System.currentTimeMillis();

    public static long getRandomNumber() {
        seed ^= seed >> 12;
        seed ^= seed << 25;
        seed ^= seed >> 27;
        seed = (seed * 2685821657736338717L) % max;
        return Math.abs(seed);
    }

    public static long getRandomNumber(long maxValue) {
        long randomNumber = getRandomNumber();
        return randomNumber % (maxValue + 1);
    }

    public static long getRandomNumber(long minValue, long maxValue) {
        long randomNumber = getRandomNumber();
        return minValue + (randomNumber % (maxValue - minValue + 1));
    }
}
