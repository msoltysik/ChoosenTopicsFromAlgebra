package algorithms;

import algorithms.interfaces.IGenerator;

@SuppressWarnings("UnusedDeclaration")

public class Xorshift implements IGenerator {
    private static final long max = ((long) 2 << 30) - 1;
    private long seed;

    /**
     *
     */
    public Xorshift() {
        seed = System.currentTimeMillis() + System.identityHashCode(new Object());
    }

    /**
     * @param seed number used to initialize a pseudorandom number generator
     */
    public Xorshift(long seed) {
        this.seed = seed;
    }

    /**
     * @return pseudo-random number between 0 and max.
     */
    public long getRandomNumber() {
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
    public long getRandomNumber(long maxValue) {
        long randomNumber = getRandomNumber();
        return randomNumber % (maxValue + 1);
    }

    /**
     * @param minValue minimum value which the function can return.
     * @param maxValue maximum value which the function can return.
     * @return pseudo-random number between minValue and maxValue param.
     */
    public long getRandomNumber(long minValue, long maxValue) {
        long randomNumber = getRandomNumber();
        return minValue + (randomNumber % (maxValue - minValue + 1));
    }
}