package temp;

import algorithms.*;

import java.util.Arrays;

public class ChiSquareTest {
    static Xorshift xorshift = new Xorshift();
    static Wichman_Hill wichman_hill = new Wichman_Hill();
    static RC4PRGA rc4PRGA = new RC4PRGA();
    static ParkMiller parkMiller = new ParkMiller();
    static MultiplyWithCarry multiplyWithCarry = new MultiplyWithCarry();
    static MersenneTwister mersenneTwister = new MersenneTwister();
    static LinearFeedbackShiftRegister linearFeedbackShiftRegister = new LinearFeedbackShiftRegister();
    static LinearCongruentialGenerator linearCongruentialGenerator = new LinearCongruentialGenerator();
    static BlumBlumShub blumBlumShub = new BlumBlumShub();

    public static void main(String[] args) {
        int count = 30;
        long[] array = new long[count];

        for (int i = 0; i < count; i++) {
            array[i] = ChiSquareTest.xorshift.getRandomNumber(9);
        }
        System.out.println(Arrays.toString(array) + " : xorshift");

        for (int i = 0; i < count; i++) {
            array[i] = ChiSquareTest.wichman_hill.getRandomNumber(10);
        }
        System.out.println(Arrays.toString(array) + " : wichman_hill");

/*
        for (int i = 0; i < count; i++) {
            array[i] = ChiSquareTest.rc4PRGA.getRandomNumber(10);
        }
        System.out.println(Arrays.toString(array) + " : rc4PRGA");
*/

        for (int i = 0; i < count; i++) {
            array[i] = ChiSquareTest.parkMiller.getRandomNumber(9);
        }
        System.out.println(Arrays.toString(array) + " : parkMiller");


        for (int i = 0; i < count; i++) {
            array[i] = ChiSquareTest.multiplyWithCarry.getRandomNumber(9);
        }
        System.out.println(Arrays.toString(array) + " : multiplyWithCarry");


        for (int i = 0; i < count; i++) {
            array[i] = ChiSquareTest.mersenneTwister.getRandomNumber(9);
        }
        System.out.println(Arrays.toString(array) + " : mersenneTwister");


        for (int i = 0; i < count; i++) {
            array[i] = ChiSquareTest.linearFeedbackShiftRegister.getRandomNumber(9);
        }
        System.out.println(Arrays.toString(array) + " : linearFeedbackShiftRegister");


        for (int i = 0; i < count; i++) {
            array[i] = ChiSquareTest.linearCongruentialGenerator.getRandomNumber(9);
        }
        System.out.println(Arrays.toString(array) + " : linearCongruentialGenerator");


        for (int i = 0; i < count; i++) {
            array[i] = ChiSquareTest.blumBlumShub.getRandomNumber(9);
        }
        System.out.println(Arrays.toString(array) + " : blumBlumShub");


    }
}
