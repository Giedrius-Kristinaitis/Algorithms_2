package com.labor2.task1;

import com.labor2.Solution;

import java.util.Random;

/**
 * Implements the longest common sub-sequence finding formula using recursion
 */
@SuppressWarnings("unused")
public class RecursiveSolution implements Solution  {

    // used to generate random data
    private static final Random random = new Random(2019);

    /**
     * Recursively finds the length of the longest common sub-sequence
     *
     * @param i sequence 1 element index
     * @param j sequence 2 element index
     * @param sequence1 first sequence
     * @param sequence2 second sequence
     *
     * @return length of the longest sub-sequence
     */
    public int longestSubSequence(int i, int j, int[] sequence1, int[] sequence2) {
        if (i < 0 || j < 0) {
            return 0;
        } else if (sequence1[i] == sequence2[j]) {
            return longestSubSequence(i - 1, j - 1, sequence1, sequence2) + 1;
        } else {
            return Math.max(longestSubSequence(i - 1, j, sequence1, sequence2),
                            longestSubSequence(i, j - 1, sequence1, sequence2));
        }
    }

    /**
     * Tests the solution and prints the result to the console
     */
    @Override
    public void test() {
        int[] testSizes = new int[] {12, 14, 16, 18, 20};

        for (int size: testSizes) {
            int[] sequence1 = generateSequence(size);
            int[] sequence2 = generateSequence(size);

            long startTime = System.currentTimeMillis();

            System.out.println("Recursively finding the longest common sub-sequence in two sequences of " + size + " elements...");

            System.out.println(longestSubSequence(sequence1.length - 1, sequence2.length - 1,
                    sequence1, sequence2));

            System.out.println("Searching took " + (System.currentTimeMillis() - startTime) + " milliseconds");
        }
    }

    /**
     * Generates a random sequence
     *
     * @param length number of elements
     * @return generated sequence
     */
    public static int[] generateSequence(int length) {
        int[] data = new int[length];

        for (int i = 0; i < length; i++) {
            data[i] = random.nextInt(10);
        }

        return data;
    }
}
