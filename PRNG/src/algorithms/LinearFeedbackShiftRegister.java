package algorithms;

public class LinearFeedbackShiftRegister {
    private static final int M = 32;
    // hard-coded for 32-bits
    private static final int[] TAPS = {1, 2, 22, 32};
    
    private static boolean[] bits = new boolean[M + 1];
    
    private static long seed;
    
    
    private static void generateLFSR(long seed) {
        for(int i = 0; i < M; i++) {
            bits[i] = (((1 << i) & seed) >>> i) == 1;
        }
    }
    public LinearFeedbackShiftRegister() {
    	seed = System.currentTimeMillis() + System.identityHashCode(new Object()); 
        for(int i = 0; i < M; i++) {
            bits[i] = (((1 << i) & seed) >>> i) == 1;
        }
    }
    
    
    /* generate a random int uniformly on the interval [-2^31 + 1, 2^31 - 1] */
    public int nextInt() {
        //printBits();
        
        // calculate the integer value from the registers
        int next = 0;
        for(int i = 0; i < M; i++) {
            next |= (bits[i] ? 1 : 0) << i;
        }
        
        // allow for zero without allowing for -2^31
        if (next < 0) next++;
        
        // calculate the last register from all the preceding
        bits[M] = false;
        for(int i = 0; i < TAPS.length; i++) {
            bits[M] ^= bits[M - TAPS[i]];
        }
        
        // shift all the registers
        for(int i = 0; i < M; i++) {
            bits[i] = bits[i + 1];
        }
        
        return next;
    }
    
    /** returns random double uniformly over [0, 1) */
    public double nextDouble() {
        return ((nextInt() / (Integer.MAX_VALUE + 1.0)) + 1.0) / 2.0;
    }
    
    /** returns random boolean */
    public boolean nextBoolean() {
    	int rand = nextInt();
    	if (rand % 2 == 0)
    		return true;
    	else return false;
    }
    
    private void printBits() {
        System.out.print(bits[M] ? 1 : 0);
        System.out.print(" -> ");
        for(int i = M - 1; i >= 0; i--) {
            System.out.print(bits[i] ? 1 : 0);
        }
        System.out.println();
    }
}
