package com.labor2.task3;

import com.labor2.Solution;

import java.util.Random;

/**
 * Performs search on a hash table
 */
@SuppressWarnings("unused")
public class SearchSolution implements Solution {

    // used to generate random data
    private static final Random random = new Random();

    /**
     * Generates an array of random strings
     *
     * @param count size of the array
     *
     * @return random string array
     */
    public static String[] generateData(int count) {
        String[] data = new String[count];

        for (int i = 0; i < count; i++) {
            data[i] = generateRandomString(10);
        }

        return data;
    }

    /**
     * Generates a random string
     *
     * @param length number of characters in the string
     *
     * @return random string
     */
    private static String generateRandomString(int length) {
        String data = "";

        for (int i = 0; i < length; i++) {
            data += (char) (64 + random.nextInt(30));
        }

        return data;
    }

    /**
     * Tests the solution and prints the result to the console
     */
    @Override
    public void test() {
        int[] testSizes = new int[] {100000, 200000, 400000, 800000, 1600000};

        for (int size: testSizes) {
            MemoryHashTable table = new MemoryHashTable(1024);

            String[] data = generateData(size);

            for (String s: data) {
                table.put(s, s);
            }

            System.out.println("Sequentially searching a hash table with " + size + " elements...");

            long startTime = System.currentTimeMillis();

            for (String s: data) {
                table.get(s);
            }

            System.out.println("Search took " + (System.currentTimeMillis() - startTime) + " milliseconds");
        }
    }
}
