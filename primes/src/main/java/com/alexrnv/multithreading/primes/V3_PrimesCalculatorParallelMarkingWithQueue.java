package com.alexrnv.multithreading.primes;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Conveyor implementation with one prime-checking thread and multiple marking threads.
 * All threads modify the same sieve, so it has to be either synchronized, or allow benign races.
 * With properly synchronized AtomicBitSet, it is a classic example of false sharing in action.
 * The running time is order of magnitude worse than the first naive implementation.
 * Though it might be useful for research.
 * Work dispatch is done via the blocking queue.
 */
public class V3_PrimesCalculatorParallelMarkingWithQueue {

    private final AtomicBitsetSieve sieve;
    private final BlockingQueue<Integer> queue;
    private final int parallelism = Runtime.getRuntime().availableProcessors() - 1;
    private final List<MarkingThread> markingThreads = new ArrayList<>();

    public V3_PrimesCalculatorParallelMarkingWithQueue(int limit) {
        this.sieve = new AtomicBitsetSieve(limit);

        this.queue = new ArrayBlockingQueue<>(parallelism);
        for (int i = 0; i < parallelism; i++) {
            MarkingThread thread = new MarkingThread(sieve, queue);
            markingThreads.add(thread);
            thread.start();
        }
    }

    public int largestPrime() throws InterruptedException {
        for (int i = 2; i < sieve.getSize(); i++) {
            if (sieve.isNotMarked(i)) {
                queue.put(i);
            }
        }
        for (int i = 0; i < markingThreads.size(); i++) {
            queue.put(sieve.getSize());
        }
        for (MarkingThread markingThread : markingThreads) {
            markingThread.join();
        }

        for (int i = sieve.getSize() - 1; i >= 0; i--) {
            if (sieve.isNotMarked(i))
                return i;
        }
        return -1;
    }

    private static class MarkingThread extends Thread {
        private final Sieve sieve;
        private final BlockingQueue<Integer> queue;

        private MarkingThread(Sieve sieve, BlockingQueue<Integer> queue) {
            this.sieve = sieve;
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                int number = 0;
                while (number != sieve.getSize()) {
                    number = queue.take();
                    sieve.mark(number);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
