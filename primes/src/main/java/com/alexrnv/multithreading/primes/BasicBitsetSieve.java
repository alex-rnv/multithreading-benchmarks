package com.alexrnv.multithreading.primes;

import java.util.BitSet;

/**
 * Sieve implementation based on BitSet.
 * Marking iteration starts from the prime multiplied by 2, with the step equal to the prime.
 * Backed by the Java's BitSet.
 */
public class BasicBitsetSieve implements Sieve {
    private final int size;
    private final BitSet sieve;

    public BasicBitsetSieve(int size) {
        this.size = size;
        this.sieve = new BitSet(size);
        this.sieve.set(0);
        this.sieve.set(1);
    }

    @Override
    public void mark(int prime) {
        for (int j = 2 * prime; j < size; j += prime) {
            sieve.set(j);
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
