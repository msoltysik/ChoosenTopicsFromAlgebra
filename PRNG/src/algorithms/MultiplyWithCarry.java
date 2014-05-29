package algorithms;

import algorithms.interfaces.IGenerator;

@SuppressWarnings("UnusedDeclaration")
public class MultiplyWithCarry implements IGenerator {

    private int c = 3912021;

    private long x;

    public MultiplyWithCarry() {
        x = System.currentTimeMillis() + System.identityHashCode(new Object());
    }


    public long getRandomNumber() {
        long a = 0xffffda61L;
        long t = a * x + c;
        long b = 0xffffffffL;
        x = t % b;
        c = (int) (t / b);
        return (int) x;

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
