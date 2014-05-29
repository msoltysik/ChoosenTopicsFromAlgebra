package algorithms;

import algorithms.interfaces.IGenerator;

@SuppressWarnings("UnusedDeclaration")

public class Wichman_Hill implements IGenerator {
    private long seed;

    public Wichman_Hill() {
        seed = System.currentTimeMillis();

    }
    public long getRandomNumber() {
        seed = (16555425264690L * seed) % 27817185604309L;
        return seed;
    }


    public long getRandomNumber(long maxValue) {
        long randomNumber = getRandomNumber();
        return randomNumber % maxValue;
    }

    public long getRandomNumber(long minValue, long maxValue) {

        if (minValue > maxValue) {
            long tmp = minValue;
            minValue = maxValue;
            maxValue = tmp;
        }

        long randomNumber = getRandomNumber();
        return minValue + (randomNumber % (maxValue - minValue + 1));
    }
}

