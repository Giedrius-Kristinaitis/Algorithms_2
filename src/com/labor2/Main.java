package com.labor2;

import com.labor2.task1.DynamicSolution;
import com.labor2.task1.RecursiveSolution;
import com.labor2.task3.SearchSolution;
import com.labor2.task3.ThreadedSearchSolution;
import com.labor2.task4.ThreadedSolution;

import java.util.Scanner;

/**
 * Main program class
 */
public class Main {

    /**
     * Entry point of the program
     * @param args arguments for the program
     */
    public static void main(String[] args) {
        new Main();
    }

    /**
     * Default constructor
     */
    private Main() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter one of the following commands: rec, dyn, nddyn, prec, search, psearch, exit:");

            String command = scanner.nextLine();

            switch (command) {
                case "rec":
                    testRecursiveSolution();
                    break;
                case "dyn":
                    testDynamicSolution();
                    break;
                case "prec":
                    testParallelRecursiveSolution();
                    break;
                case "search":
                    testSearchSolution();
                    break;
                case "psearch":
                    testParallelSearchSolution();
                    break;
                case "exit":
                    System.exit(0);
                    break;
                case "nddyn":
                    testDynamicPriceSolution();
                    break;
                default:
                    System.out.println("Unknown command");
            }
        }
    }

    /**
     * Tests the dynamic solution to the biggest price fall problem
     */
    private void testDynamicPriceSolution() {
        System.out.println("********** Dynamic Biggest Price Fall **********");

        Solution solution = new com.labor2.task2.DynamicSolution();

        solution.test();

        System.out.println("************************************************");
    }

    /**
     * Tests the recursive LCS solution
     */
    private void testRecursiveSolution() {
        System.out.println("********** Recursive Longest Common Sub-sequence **********");

        Solution solution = new RecursiveSolution();

        solution.test();

        System.out.println("***********************************************************");
    }

    /**
     * Tests the dynamic LCS solution
     */
    private void testDynamicSolution() {
        System.out.println("********** Dynamic Longest Common Sub-sequence **********");

        Solution solution = new DynamicSolution();

        solution.test();

        System.out.println("*********************************************************");
    }

    /**
     * Test parallel recursive LCS solution
     */
    private void testParallelRecursiveSolution() {
        System.out.println("********** Parallel Recursive Longest Common Sub-sequence **********");

        Solution solution = new ThreadedSolution();

        solution.test();

        System.out.println("********************************************************************");
    }

    /**
     * Test hash table search solution
     */
    private void testSearchSolution() {
        System.out.println("********** HashTable Search **********");

        Solution solution = new SearchSolution();

        solution.test();

        System.out.println("**************************************");
    }

    /**
     * Test parallel hash table search solution
     */
    private void testParallelSearchSolution() {
        System.out.println("********** Parallel HashTable Search **********");

        Solution solution = new ThreadedSearchSolution();

        solution.test();

        System.out.println("***********************************************");
    }
}
