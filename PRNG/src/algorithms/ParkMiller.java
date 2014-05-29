package algorithms;

import algorithms.interfaces.IGenerator;

@SuppressWarnings("ALL")

public class ParkMiller implements IGenerator {
    private static final long max = ((long) 2 << 30) - 1;
    private static final long a = 16807;
    private static long seed;

    public ParkMiller() {
        seed = System.currentTimeMillis() + System.identityHashCode(new Object());
    }

    public ParkMiller(long seed) {
        this.seed = seed;
    }

    /**
     * @return pseudo-random number between minValue and maxValue.
     */
    public long getRandomNumber() {
        seed = (a * seed) % max;
        return seed;
    }

    /**
     * @param maxValue maximum value which the function can return.
     * @return pseudo-random number between minValue and maxValue.
     */
    public long getRandomNumber(long maxValue) {
        seed = getRandomNumber();
        return seed % (maxValue + 1);
    }

    /**
     * @param minValue minimum value which the function can return.
     * @param maxValue maximum value which the function can return.
     * @return pseudo-random number between minValue and maxValue.
     */
    public long getRandomNumber(long minValue, long maxValue) {
        seed = getRandomNumber();
        return minValue + (seed % (maxValue - minValue + 1));
    }


}
