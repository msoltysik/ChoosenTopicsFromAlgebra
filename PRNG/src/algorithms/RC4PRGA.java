package algorithms;

import algorithms.interfaces.IGenerator;

@SuppressWarnings("UnusedDeclaration")

public class RC4PRGA implements IGenerator {
    private int[] s = new int[256];
    private long seed;

    public RC4PRGA() {
        for (int i = 0; i < 256; i++) {
            s[i] = i;
            seed = System.currentTimeMillis() + System.identityHashCode(new Object());
        }
    }

    public long permute() {
        long counter = 0L;
        int i = 0;
        int j = 0;
        long out = 0L;
        while (counter < seed) {
            int tmp;
            i = (i + 1) % 256;
            j = (j + s[i]) % 256;

            tmp = s[j];
            s[j] = s[i];
            s[i] = tmp;

            out += s[(s[i] + s[j]) % 256];
            counter++;
        }
        return out;
    }

    public long getRandomNumber() {
        long random = permute();
        return random & 0xffffffffL;
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
