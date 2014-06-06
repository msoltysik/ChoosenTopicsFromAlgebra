import algorithms.interfaces.IGenerator;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("Usage: java -jar PRNG.jar <1> <2> <3> <4>\n" +
                    "<1> - Algorithm name,\n" +
                    "<2> - maxvalue,\n" +
                    "<3> - iteration,\n" +
                    "<4> - times.");
            System.exit(-1);
        }
        String[] strings = {
                "BlumBlumShub", "LinearCongruentialGenerator", "LinearFeedbackShiftRegister",
                "MersenneTwister", "MultiplyWithCarry", "ParkMiller",
                "Wichman_Hill", "Xorshift", "RC4PRGA"
        };
        if (Arrays.asList(strings).contains(args[0])) {
            save(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
        } else {
            System.out.println("Błędna nazwa algorytmu.");
        }
    }

    private static void save(String className, int max, int iteration, int times) {
        IGenerator generator = null;
        Constructor constructor;
        try {
            Class aClass = Class.forName("algorithms." + className);
            constructor = aClass.getConstructor(new Class[]{long.class});
            generator = (IGenerator) constructor.newInstance(getSeed());
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(String.format("temp.txt", className), "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < iteration; i++) {
            for (int j = 0; j < times; j++) {
                if (writer != null) {
                    if (generator != null) {
                        writer.print(generator.getRandomNumber(max));
                    }
                    if (j + 1 != times) {
                        writer.print(",");
                    }

                }
            }
            if (writer != null) {
                writer.println();
            }
        }

        if (writer != null) {
            writer.close();
        }
    }

    public static long getSeed() {
        SecureRandom random = new SecureRandom();
        byte seed[] = random.generateSeed(20);
        return new BigInteger(seed).longValue();
    }
}
