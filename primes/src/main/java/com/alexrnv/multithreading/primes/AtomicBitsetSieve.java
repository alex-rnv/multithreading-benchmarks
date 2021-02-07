package com.alexrnv.multithreading.primes;

import com.alexrnv.multithreading.util.AtomicBitSet;

/**
 * Sieve implementation based on BitSet.
 * Marking iteration starts from the prime multiplied by 2, with the step equal to the prime.
 * Backed by the AtomicBitSet (get and set are implemented as CAS-es on the integer's in the backing array).
 */
public class AtomicBitsetSieve implements Sieve {
    private final int size;
    private final AtomicBitSet sieve;

    public AtomicBitsetSieve(int size) {
        this.size = size;
        this.sieve = new AtomicBitSet(size);
        this.sieve.set(0);
        this.sieve.set(1);
    }

    @Override
    public void mark(int prime) {
        for (long j = 2L * prime; j < size; j += prime) {
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
