package com.alexrnv.multithreading.primes;

import java.util.BitSet;

/**
 * Sequential in the name implies that it only works for sequential algorithms,
 * when primes are calculated in order. This allows to optimize the marking cycle
 * by starting from the square of prime.
 * Backed by the Java's BitSet.
 */
public class SequentialBitSetSieve implements Sieve {
    private final int size;
    private final BitSet sieve;

    public SequentialBitSetSieve(int size) {
        this.size = size;
        this.sieve = new BitSet(size);
        this.sieve.set(0);
        this.sieve.set(1);
    }

    @Override
    public void mark(int prime) {
        for (long j = prime * prime; j < size; j += prime) {
            sieve.set((int)j);
        }
    }

    @Override
    public boolean isNotMarked(int i) {
        return !sieve.get(i);
    }

    @Override
    public int getSize() {
        return size;
    }


}
