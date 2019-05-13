package com.labor2.task2;

import com.labor2.Solution;

import java.util.Random;

/**
 * Finds the biggest price fall
 */
@SuppressWarnings("unused")
public class DynamicSolution implements Solution {

    // used to generate random data
    private final Random random = new Random(2019);

    /**
     * Finds the biggest price fall
     *
     * @param fluctuations array of price fluctuations
     *
     * @return
     */
    public int priceFall(int[] fluctuations) {
        int[] sum = new int[fluctuations.length];

        int min = Integer.MAX_VALUE;

        sum[0] = fluctuations[0];

        for (int i = 1; i < fluctuations.length; i++) {
            int ithSum = Math.min(sum[i - 1] + fluctuations[i], fluctuations[i]);

            sum[i] = ithSum;

            if (ithSum < min) {
                min = ithSum;
            }
        }

        return min;
    }

    /**
     * Tests the solution and prints the result to the console
     */
    @Override
    public void test() {
        int[] testSizes = new int[] {10000, 20000, 40000, 80000, 160000};

        for (int size: testSizes) {
            int[] fluctuations = generateFluctuation(size);

            long startTime = System.currentTimeMillis();

            System.out.println("Dynamically finding the biggest price fall in an array of " + size + " elements...");

            System.out.println(priceFall(fluctuations));

            System.out.println("Searching took " + (System.currentTimeMillis() - startTime) + " milliseconds");
        }
    }

    /**
     * Generates a random array of price fluctuations
     *
     * @param length length of the array
     *
     * @return fluctuation array
     */
    private int[] generateFluctuation(int length) {
        int[] fluctuations = new int[length];

        for (int i = 0; i < length; i++) {
            fluctuations[i] = random.nextInt(10) * (random.nextBoolean() ? 1 : -1);
        }

        return fluctuations;
    }
}
