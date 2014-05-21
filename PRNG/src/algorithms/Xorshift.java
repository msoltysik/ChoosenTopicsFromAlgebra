package algorithms;

@SuppressWarnings("UnusedDeclaration")

public abstract class Xorshift {
    private static final long max = ((long) 2 << 30) - 1;
    private static long seed = System.currentTimeMillis() + System.identityHashCode(new Object());

    /**
     * @return pseudo-random number between 0 and max.
     */
    public static long getRandomNumber() {
        seed ^= seed >> 12;
        seed ^= seed << 25;
        seed ^= seed >> 27;
        seed = (seed * 2685821657736338717L) % max;
        return Math.abs(seed);
    }

    /**
     * @param maxValue maximum value which the function can return.
     * @return pseudo-random number between 0 and maxValue param.
     */
    public static long getRandomNumber(long maxValue) {
        long randomNumber = getRandomNumber();
        return randomNumber % (maxValue + 1);
    }

    /**
     * @param minValue minimum value which the function can return.
     * @param maxValue maximum value which the function can return.
     * @return pseudo-random number between minValue and maxValue param.
     */
    public static long getRandomNumber(long minValue, long maxValue) {
        long randomNumber = getRandomNumber();
        return minValue + (randomNumber % (maxValue - minValue + 1));
    }
}