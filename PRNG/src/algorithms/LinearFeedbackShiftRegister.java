package algorithms;

import algorithms.interfaces.IGenerator;

@SuppressWarnings("UnusedDeclaration")

public class LinearFeedbackShiftRegister implements IGenerator {
    private static final int M = 32;
    private static boolean[] bits = new boolean[M + 1];
    // hard-coded for 32-bits
    private static final int[] TAPS = {1, 2, 22, 32};


    public LinearFeedbackShiftRegister() {
        long seed = System.currentTimeMillis() + System.identityHashCode(new Object());
        for (int i = 0; i < M; i++) {
            bits[i] = (((1 << i) & seed) >>> i) == 1;
        }
    }

    private static void generateLFSR(long seed) {
        for (int i = 0; i < M; i++) {
            bits[i] = (((1 << i) & seed) >>> i) == 1;
        }
    }

    /* generate a random int uniformly on the interval [-2^31 + 1, 2^31 - 1] */
    public long getRandomNumber() {
    	generateLSFR(System.currentTimeMillis() + System.identityHashCode(new Object()));
        //printBits();

        // calculate the integer value from the registers
        int next = 0;
        for (int i = 0; i < M; i++) {
            next |= (bits[i] ? 1 : 0) << i;
        }

        // allow for zero without allowing for -2^31
        if (next < 0) next++;

        // calculate the last register from all the preceding
        bits[M] = false;
        for (int TAP : TAPS) {
            bits[M] ^= bits[M - TAP];
        }

        // shift all the registers
        System.arraycopy(bits, 1, bits, 0, M);

        return Math.abs(next);
    }

    /**
     * returns random double uniformly over [0, 1)
     */
    public double nextDouble() {
        return ((getRandomNumber() / (Integer.MAX_VALUE + 1.0)) + 1.0) / 2.0;
    }

    /**
     * returns random boolean
     */
    public boolean nextBoolean() {
        long rand = getRandomNumber();
        return rand % 2 == 0;
    }

    private void printBits() {
        System.out.print(bits[M] ? 1 : 0);
        System.out.print(" -> ");
        for (int i = M - 1; i >= 0; i--) {
            System.out.print(bits[i] ? 1 : 0);
        }
        System.out.println();
    }


    public long getRandomNumber(long maxValue) {
        long randomNumber = getRandomNumber();
        return randomNumber % (maxValue + 1);
    }

    public long getRandomNumber(long minValue, long maxValue) {
        long randomNumber = getRandomNumber();
        return minValue + (randomNumber % (maxValue - minValue + 1));
    }
}
