package com.alexrnv.multithreading.primes;

import java.util.BitSet;

/**
 * Optimized version of the sieve that tracks only odd numbers.
 * Indexes start from 3 and increment with the step 2: 3,5,7.
 * Accessing the Sieve with even indexes will give incorrect results.
 * The main loop in the parent class is an example of correct iteration over the Sieve.
 * Current implementation allows to increase the upper limit to 2*Integer.MAX_VALUE-1.
 * Backed by BitSet.
 */
public class SkipEvenBitSetSieve implements Sieve {
    private final int size;
    private final BitSet sieve;

    public SkipEvenBitSetSieve(int size) {
        this.size = size;
        this.sieve = new BitSet((size >> 1) - 1);
    }

    @Override
    public void mark(int prime) {
        for (long j = prime * prime; j < size; j += 2 * prime) {
            sieve.set(bitIndex(j));
        }
    }

    @Override
    public boolean isNotMarked(int i) {
        return !sieve.get(bitIndex(i));
    }

    @Override
    public int getSize() {
        return size;
    }

    private int bitIndex(long i) {
        return (int)(i >> 1) - 1;
    }
}
