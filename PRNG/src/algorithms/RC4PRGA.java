package algorithms;

import algorithms.interfaces.IGenerator;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("UnusedDeclaration")

public class RC4PRGA implements IGenerator {
    List<Integer> list = new ArrayList<>();
    private long seed;


    public RC4PRGA() {
        for (int i = 0; i < 256; i++) {
            list.add(i);
        }
        int j = 0;
        java.util.Collections.shuffle(list);
        for(int i = 0; i < 256; i++){
            char[] key = "keyforplaintext12345".toCharArray();
            j = (j + list.get(i) + Character.getNumericValue(key[i% key.length])) % 256;
            int tmp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, tmp);
        }
    }

    private String prga(){
        int i,j;
        i = j = 0;

        i = (i+1) % 256;
        j = j + list.get(i) % 256;

        int tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
        int t = list.get((i+j)%256);
        return Integer.toHexString(list.get(t));
    }

    public long getRandomNumber() {
        String nextpart;
        String out = "";
        for(int i = 0; i < 7; i++){
            nextpart = prga();
            out = out + nextpart;
        }
        return Long.parseLong(out,16);
    }

    public long getRandomNumber(long maxValue) {
        long randomNumber = getRandomNumber();
        return randomNumber % maxValue;
    }

    public long getRandomNumber(long minValue, long maxValue) {

        if (minValue > maxValue) {
            long tmp = minValue;
            minValue = maxValue;
            maxValue = tmp;
        }

        long randomNumber = getRandomNumber();
        return minValue + (randomNumber % (maxValue - minValue + 1));
    }
}