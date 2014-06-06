package algorithms;

import algorithms.interfaces.IGenerator;

@SuppressWarnings("UnusedDeclaration")

public class MersenneTwister implements IGenerator {
    private static int[] MT = new int[624];
    private static int index = 0;
    private static boolean initialized = false;

    /**
     *
     */
    public MersenneTwister() {
        MT[0] = (int) (System.currentTimeMillis() + System.identityHashCode(new Object()));
        for (int i = 1; i < 624; i++) {
            MT[i] = (1812433253 * (MT[i - 1] ^ ((MT[i - 1] >> 30) + i)));
        }
        initialized = true;
    }

    /**
     * @param seed number used to initialize a pseudorandom number generator
     */
    public MersenneTwister(int seed) {
        MT[0] = seed;
        for (int i = 1; i < 624; i++) {
            MT[i] = (1812433253 * (MT[i - 1] ^ ((MT[i - 1] >> 30) + i)));
        }
        initialized = true;
    }

    /**
     *
     */
    public static void generateNumbers() {
        for (int i = 0; i < 624; i++) {
            int y = (MT[i] >> 31) + (MT[(i + 1) % 624] >> 1); // ?
            MT[i] = MT[(i + 397) % 624] ^ y >> 1;
            if ((y % 2) == 1) {
                MT[i] = (int) (MT[i] ^ 2567483615L);
            }
        }
    }

    /**
     * @return pseudo-random number between 0 and max.
     */
    public long getRandomNumber() {
        if (index == 0) {
            generateNumbers();
        }

        long y = MT[index];
        y = y ^ (y >> 11);
        y = y ^ ((y << 7) & 2636928640L);
        y = y ^ ((y << 15) & 4022730752L);
        y = y ^ (y >> 18);

        index = (index + 1) % 624;
        return Math.abs((int) y);
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