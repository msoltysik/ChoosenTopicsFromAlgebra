package algorithms;

import algorithms.interfaces.IGenerator;

@SuppressWarnings("UnusedDeclaration")

public class MersenneTwister implements IGenerator {
    private static int[] MT = new int[624];
    private static int index = 0;
    private static boolean initialized = false;

    public static void initializeGenerator() {
        MT[0] = (int) (System.currentTimeMillis() + System.identityHashCode(new Object()));
        for (int i = 1; i < 624; i++) {
//            MT[i] := last 32 bits of(1812433253 * (MT[i-1] xor (right shift by 30 bits(MT[i-1]))) + i) // 0x6c078965
            MT[i] = (1812433253 * (MT[i - 1] ^ ((MT[i - 1] >> 30) + i)));
        }
        initialized = true;
    }

    public static void initializeGenerator(int seed) {
        MT[0] = seed;
        for (int i = 1; i < 624; i++) {
//            MT[i] := last 32 bits of(1812433253 * (MT[i-1] xor (right shift by 30 bits(MT[i-1]))) + i) // 0x6c078965
            MT[i] = (1812433253 * (MT[i - 1] ^ ((MT[i - 1] >> 30) + i)));
        }
        initialized = true;
    }

    public long getRandomNumber() {
        if (!initialized) {
            initializeGenerator();
        }
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

    public static void generateNumbers() {
        for (int i = 0; i < 624; i++) {
//            int y := 32nd bit of(MT[i]) + last 31 bits of(MT[(i+1) mod 624])
            int y = (MT[i] >> 31) + (MT[(i + 1) % 624] >> 1); // ?
//            MT[i] := MT[(i + 397) mod 624] xor (right shift by 1 bit(y))
            MT[i] = MT[(i + 397) % 624] ^ y >> 1;
            if ((y % 2) == 1) {
                MT[i] = (int) (MT[i] ^ 2567483615L);
            }
        }
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
