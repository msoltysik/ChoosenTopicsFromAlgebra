import algorithms.interfaces.IGenerator;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class SaveToFile {
    public static void main(String[] args) {
        String[] strings = {
                "BlumBlumShub", "LinearCongruentialGenerator", "LinearFeedbackShiftRegister",
                "MersenneTwister", "MultiplyWithCarry", "ParkMiller",
                "Wichman_Hill", "Xorshift"
        };
        for (String s: strings) {
            save(s, 10, 20, 3);
        }
    }

    private static void save(String className, int max, int iteration, int times) {
        IGenerator generator = null;
        try {
            generator = (IGenerator) Class.forName("algorithms." + className).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        PrintWriter writer = null;
        try {
            writer = new  PrintWriter(String.format(".temp-%s.txt", className), "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < iteration; i++) {
            for (int j = 0; j < times; j++) {
                if (writer != null) {
                    if (generator != null) {
                        writer.print(generator.getRandomNumber(max));
                    }
                    if (j+1 != times) {
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
}
