package algorithms;

//Blum Blum Shub
@SuppressWarnings("UnusedDeclaration")
public class BBS {
    private static final int p = 11;
    private static final int q = 19;
    private static long seed = System.currentTimeMillis();

    public static long getRandomNumber() {
        seed = (seed * seed % (p * q));
        return Math.abs(seed);
    }

    public static long getRandomNumber(long maxValue) {
        long randomNumber = getRandomNumber();
        return randomNumber % (maxValue + 1);
    }

    public static long getRandomNumber(long minValue, long maxValue) {
        long randomNumber = getRandomNumber();
        return minValue + (randomNumber % (maxValue - minValue + 1));
    }
}
