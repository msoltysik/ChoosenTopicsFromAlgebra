package algorithms;

import algorithms.interfaces.IGenerator;

@SuppressWarnings("UnusedDeclaration")

public class LinearCongruentialGenerator implements IGenerator {
    // MMIX by Donald Knuth
    private final static long a = 6364136223846793005L;
    private final static long b = 1442695040888963407L;
    private final static long m = (long) Math.pow(2, 64);
    private long seed;

    public LinearCongruentialGenerator() {
        this.seed = System.currentTimeMillis() + System.identityHashCode(new Object());
    }

    public LinearCongruentialGenerator(long seed) {
        this.seed = seed;
    }

    /**
     * @return pseudo-random number between 0 and max.
     */
    public long getRandomNumber() {
        seed = (a * seed + b) % m;
        return Math.abs(seed);
    }

    /**
     * @param maxValue maximum value which the function can return.
     * @return pseudo-random number between 0 and maxValue param.
     */
    public long getRandomNumber(long maxValue) {
        seed = getRandomNumber();
        return seed % (maxValue + 1);
    }

    /**
     * @param minValue minimum value which the function can return.
     * @param maxValue maximum value which the function can return.
     * @return pseudo-random number between minValue param and maxValue param.
     */
    public long getRandomNumber(long minValue, long maxValue) {
        seed = getRandomNumber();
        return minValue + (seed % (maxValue - minValue + 1));
    }
}
