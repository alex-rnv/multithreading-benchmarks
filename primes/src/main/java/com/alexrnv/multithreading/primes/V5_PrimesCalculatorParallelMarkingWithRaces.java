package com.alexrnv.multithreading.primes;

import java.util.ArrayList;
import java.util.List;

/**
 * This particular implementation can not use BitSet based sieves, because it's implementation via array of longs
 * is subject to lost update anomaly (operations writing to the bits fitting in the same computer word
 * will override each other!). The only race we allow in this implementation is stale reads (call
 * it snapshot isolation, in the database transactions language).
 */
public class V5_PrimesCalculatorParallelMarkingWithRaces {

    private final Sieve sieve;
    private final List<MarkingThread> markingThreads = new ArrayList<>();
    private final int[] basePrimes = {3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59};

    public V5_PrimesCalculatorParallelMarkingWithRaces(int limit,
                                                       SkipEvenByteArraySieve.StartFrom start,
                                                       int parallelism,
                                                       boolean shuffleStart) {
        if (parallelism <= 0) {
            parallelism = Runtime.getRuntime().availableProcessors();
        }
        if (parallelism > basePrimes.length && shuffleStart) {
            throw new RuntimeException("please provide bigger basePrimes array");
        }
        this.sieve = new SkipEvenByteArraySieve(limit, start);
        int sqrt = (int)Math.sqrt(sieve.getSize());
        for (int i = 0; i < parallelism; i++) {
            MarkingThread thread = new MarkingThread(sieve, sqrt, shuffleStart ? basePrimes[i] : 3);
            markingThreads.add(thread);
        }
    }

    public int largestPrime() throws InterruptedException {
        for (int i = 0; i < markingThreads.size(); i++) {
            markingThreads.get(i).start();
        }

        for (int i = 0; i < markingThreads.size(); i++) {
            markingThreads.get(i).join();
        }

        int last = ((sieve.getSize()&1) == 0) ? sieve.getSize() - 1 : sieve.getSize() - 2;
        for (int j = last; j > 0; j -= 2) {
            if (sieve.isNotMarked(j)) {
                return j;
            }
        }
        return -1;
    }

    public static class MarkingThread extends Thread {
        private final Sieve sieve;
        private final int limit;
        private final int start;

        public MarkingThread(Sieve sieve, int limit, int start) {
            this.sieve = sieve;
            this.limit = limit;
            this.start = start;
        }

        @Override
        public void run() {
            mark(sieve, limit, start);
        }

        public static void mark(Sieve sieve, int limit, int start) {
            for (int counter = start; counter <= limit; counter += 2) {
                if (sieve.isNotMarked(counter)) {
                    sieve.mark(counter);
                }
            }
        }
    }
}
