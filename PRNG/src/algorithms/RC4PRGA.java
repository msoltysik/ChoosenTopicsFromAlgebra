package algorithms;

import java.util.List;

import algorithms.interfaces.IGenerator;

@SuppressWarnings("UnusedDeclaration")

public class RC4PRGA implements IGenerator {
    List<Integer> list = new ArrayList<Integer>();
    private long seed;
    private char[] key = "keyforplaintext12345".toCharArray();
    
    
    public RC4PRGA() {
        for (int i = 0; i < 256; i++) {
            list.add(i);
        }
        int j = 0;
        java.util.Collections.shuffle(list);
        for(int i = 0; i < 256; i++){
        	j = (j + list.get(i) + Character.getNumericValue(key[i%key.length])) % 256;
        	int tmp = list.get(i);
        	list.set(i, list.get(j));
        	list.set(j, tmp);
        }
    }
    private String prga(){
    	int i = (i+1) % 256;
    	int j = j + list.get(i) % 256;
    	
    	int tmp = list.get(i);
    	list.set(i, list.get(j));
    	list.set(j, tmp);
    	int t = list.get((i+j)%256);
    	return Integer.toHexString(list.get(t));
    }

    public long getRandomNumber() {
    	String nextpart = '';
    	String out = '';
    	for(int i = 0; i < 8; i++){
    		nextpart = prga();
    		out.concat(nextpart);
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
    
