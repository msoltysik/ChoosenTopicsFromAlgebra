package algorithms;

@SuppressWarnings("UnusedDeclaration")

public class LinearCongruentialGenerator {
    private final static long a = 25173;
    private final static long b = 13849;
    private final static long m = 32768;
    private static long seed = System.currentTimeMillis() + System.identityHashCode(new Object());

    /**
     * @return pseudo-random number from 0 to max.
     */
    public static long getRandomNumber() {
        seed = (a * seed + b) % m;
        return seed;
    }

    /**
     * @param maxValue maximum value which the function can return.
     * @return pseudo-random number from 0 to maxValue.
     */
    public static long getRandomNumber(long maxValue) {
        seed = getRandomNumber();
        return seed % (maxValue + 1);
    }

    /**
     * @param minValue minimum value which the function can return.
     * @param maxValue maximum value which the function can return.
     * @return pseudo-random number between minValue and maxValue.
     */
    public static long getRandomNumber(long minValue, long maxValue) {
        seed = getRandomNumber();
        return minValue + (seed % (maxValue - minValue + 1));
    }


}
