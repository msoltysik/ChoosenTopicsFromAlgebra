package algorithms;

@SuppressWarnings("UnusedDeclaration")

public abstract class InversiveCongruentialGenerator {
    private static long seed = System.currentTimeMillis() + System.identityHashCode(new Object());
}
