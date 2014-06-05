package algorithms;

import algorithms.interfaces.IGenerator;

@SuppressWarnings("UnusedDeclaration")

public class Wichman_Hill implements IGenerator {
    private long seed;

    /**
     *
     */
    public Wichman_Hill() {
        seed = System.currentTimeMillis() + System.identityHashCode(new Object());
    }

    /**
     *
     * @param seed number used to initialize a pseudorandom number generator
     */
    public Wichman_Hill(long seed) {
        this.seed = seed;
    }

    /**
     * @return pseudo-random number between 0 and max.
     */
    public long getRandomNumber() {
        seed = (16555425264690L * seed) % 27817185604309L;
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

