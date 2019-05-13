package com.labor2.task3;

import com.labor2.Solution;

/**
 * Performs search on a hash table using multiple threads
 */
@SuppressWarnings("unused")
public class ThreadedSearchSolution implements Solution {

    /**
     * Tests the solution and prints the result to the console
     */
    @Override
    public void test() {
        int[] testSizes = new int[] {100000, 200000, 400000, 800000, 1600000};

        for (int size: testSizes) {
            final String[] data = SearchSolution.generateData(size);

            final MemoryHashTable table = new MemoryHashTable(1024);

            for (String s: data) {
                table.put(s, s);
            }

            int threadCount = 4;
            Thread[] threads = new Thread[threadCount];

            long startTime = System.currentTimeMillis();
            System.out.println("Parallelly searching a hash table with " + size + " elements...");

            for (int i = 0; i < threadCount; i++) {
                final int index = i;

                threads[i] = new Thread(() -> {
                    for (int j = index * size / threadCount; j < (index + 1) * size / threadCount; j++) {
                        table.get(data[j]);
                    }
                });

                threads[i].start();
            }

            for (int i = 0; i < threadCount; i++) {
                try {
                    threads[i].join();
                } catch(InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            System.out.println("Search took " + (System.currentTimeMillis() - startTime) + " milliseconds");
        }
    }
}
