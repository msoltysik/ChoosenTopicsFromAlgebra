package algorithms;

import algorithms.interfaces.IGenerator;

@SuppressWarnings("UnusedDeclaration")
public class MultiplyWithCarry implements IGenerator {

    private long c = 3912021;

    private long seed;

    public MultiplyWithCarry() {
        seed = System.currentTimeMillis() + System.identityHashCode(new Object());
    }

    public MultiplyWithCarry(long seed) {
        this.seed = seed;
    }


    public long getRandomNumber() {
        long a = 0xffffda61L;
        long t = a * seed + c;
        long b = 0xffffffffL;
        seed = t % b;
        c = t / b;
        return Math.abs(seed);

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
