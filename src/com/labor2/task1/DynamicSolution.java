package com.labor2.task1;

import com.labor2.Solution;

/**
 * Implements the longest common sub-sequence finding formula using dynamic programming
 */
@SuppressWarnings("unused")
public class DynamicSolution implements Solution {

    /**
     * Finds the length of the longest common sub-sequence
     *
     * @param sequence1 first sequence
     * @param sequence2 second sequence
     *
     * @return length of the longest sub-sequence
     */
    public int longestSubSequence(int[] sequence1, int[] sequence2) {
        int m = sequence1.length;
        int n = sequence2.length;

        int c[][] = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (sequence1[i - 1] == sequence2[j - 1]) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                } else if (c[i - 1][j] >= c[i][j - 1]) {
                    c[i][j] = c[i - 1][j];
                } else {
                    c[i][j] = c[i][j - 1];
                }
            }
        }

        return c[m][n];
    }

    /**
     * Tests the solution and prints the result to the console
     */
    @Override
    public void test() {
        int[] testSizes = new int[] {12, 14, 16, 18, 20};

        for (int size: testSizes) {
            int[] sequence1 = RecursiveSolution.generateSequence(size);
            int[] sequence2 = RecursiveSolution.generateSequence(size);

            long startTime = System.nanoTime();

            System.out.println("Dynamically finding the longest common sub-sequence in two sequences of " + size + " elements...");

            System.out.println(longestSubSequence(sequence1, sequence2));

            System.out.println("Searching took " + (System.nanoTime() - startTime) + " nanoseconds");
        }
    }
}
