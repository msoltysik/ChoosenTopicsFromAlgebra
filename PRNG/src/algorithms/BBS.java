package algorithms;

//Blum Blum Shub
public class BBS {
    static int p = 11;
    static int q = 19;

    public static void getRandomNumber(int x) {
            int randomNumber = (x * x) % (p * q);
            System.out.println(randomNumber);
    }
}
