package com.labor2.task4;

import com.labor2.Solution;
import com.labor2.task1.RecursiveSolution;

/**
 * Solves the longest common sub-sequence problem using recursion and multiple threads
 */
@SuppressWarnings("unused")
public class ThreadedSolution implements Solution {

    /**
     * Stores data related to one thread's task
     */
    protected class ThreadResult {

        protected int result;
    }

    /**
     * Finds the length of longest common sub-sequence
     *
     * @param sequence1 first sequence
     * @param sequence2 second sequence
     *
     * @return length of the longest sub-sequence
     */
    public int longestSubSequence(int[] sequence1, int[] sequence2) {
        final RecursiveSolution solution = new RecursiveSolution();

        if (sequence1.length < 2 || sequence2.length < 2) {
            return solution.longestSubSequence(sequence1.length - 1, sequence2.length - 1, sequence1, sequence2);
        }

        ThreadResult result1 = new ThreadResult();
        ThreadResult result2 = new ThreadResult();
        ThreadResult result3 = new ThreadResult();
        ThreadResult result4 = new ThreadResult();

        Thread one = new Thread(() -> {
            result1.result = solution.longestSubSequence(sequence1.length - 4, sequence2.length - 1, sequence1, sequence2);
        });

        Thread two = new Thread(() -> {
            result2.result = solution.longestSubSequence(sequence1.length - 3, sequence2.length - 2, sequence1, sequence2);
        });

        Thread three = new Thread(() -> {
            result3.result = solution.longestSubSequence(sequence1.length - 2, sequence2.length - 3, sequence1, sequence2);
        });

        Thread four = new Thread(() -> {
            result4.result = solution.longestSubSequence(sequence1.length - 1, sequence2.length - 4, sequence1, sequence2);
        });

        one.start();
        two.start();
        three.start();
        four.start();

        try {
            one.join();
            two.join();
            three.join();
            four.join();
        } catch(InterruptedException ex) {
            ex.printStackTrace();
        }

        int combinedResult1 = sequence1[sequence1.length - 4] == sequence2[sequence2.length - 2] ? result1.result + 1 : Math.max(result1.result, result2.result);
        int combinedResult2 = sequence1[sequence1.length - 3] == sequence2[sequence2.length - 3] ? result2.result + 1 : Math.max(result2.result, result3.result);
        int combinedResult3 = sequence1[sequence1.length - 2] == sequence2[sequence2.length - 4] ? result3.result + 1 : Math.max(result3.result, result4.result);

        int combinedResult4 = sequence1[sequence1.length - 3] == sequence2[sequence2.length - 2] ? combinedResult1 + 1 : Math.max(combinedResult1, combinedResult2);
        int combinedResult5 = sequence1[sequence1.length - 2] == sequence2[sequence2.length - 3] ? combinedResult2 + 1 : Math.max(combinedResult2, combinedResult3);

        int combinedResult6 = sequence1[sequence1.length - 2] == sequence2[sequence2.length - 2] ? combinedResult4 + 1 : Math.max(combinedResult4, combinedResult5);

        return combinedResult6;
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

            long startTime = System.currentTimeMillis();

            System.out.println("Parallelly finding the longest common sub-sequence in two sequences of " + size + " elements...");

            System.out.println(longestSubSequence(sequence1, sequence2));

            System.out.println("Searching took " + (System.currentTimeMillis() - startTime) + " milliseconds");
        }
    }
}
