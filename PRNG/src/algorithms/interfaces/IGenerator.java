package algorithms.interfaces;

@SuppressWarnings("UnusedDeclaration")
public interface IGenerator {
    public long getRandomNumber();
    public long getRandomNumber(long maxValue);
    public long getRandomNumber(long minValue, long maxValue);
}
