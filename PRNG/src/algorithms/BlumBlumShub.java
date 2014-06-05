package algorithms;

import algorithms.interfaces.IGenerator;

@SuppressWarnings({"UnusedDeclaration", "NumericOverflow"})
public class BlumBlumShub implements IGenerator {
    // dla p = 11, q = 19 cykl wynosi 4
    private static final long p = 15107;
    private static final long q = 4337423;
    private long seed;

    public BlumBlumShub() {
        seed = System.currentTimeMillis() + System.identityHashCode(new Object());
    }
    /**
     * @return pseudo-random number between .
     */
    public long getRandomNumber() {
        seed = (seed * seed) % (p * q);
        return Math.abs(seed);
    }

    /**
     * @param maxValue maximum value which the function can return.
     * @return pseudo-random number between 0 and maxValue.
     */
    public long getRandomNumber(long maxValue) {
        long randomNumber = getRandomNumber();
        return randomNumber % (maxValue + 1);
    }

    /**
     * @param minValue minimum value which the function can return.
     * @param maxValue maximum value which the function can return.
     * @return pseudo-random number between minValue param and maxValue param.
     */
    public long getRandomNumber(long minValue, long maxValue) {
        long randomNumber = getRandomNumber();
        return minValue + (randomNumber % (maxValue - minValue + 1));
    }
}
