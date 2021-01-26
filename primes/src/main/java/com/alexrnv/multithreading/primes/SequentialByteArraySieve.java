package com.alexrnv.multithreading.primes;

/**
 * Sequential in the name implies that it only works for sequential algorithms,
 * when primes are calculated in order. This allows to optimize the marking cycle
 * by starting from the square of prime.
 * Backed by the array of bytes.
 */
public class SequentialByteArraySieve implements Sieve {
    private final int size;
    private final byte[] sieve;

    public SequentialByteArraySieve(int size) {
        this.size = size;
        this.sieve = new byte[((size >> 1) - 1)];
    }

    public void mark(int prime) {
        for (int j = prime * prime; j < size; j += 2 * prime) {
            sieve[bitIndex(j)] = 1;
        }
    }

    public boolean isNotMarked(int i) {
        return sieve[bitIndex(i)] == 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    private int bitIndex(int i) {
        return (i >> 1) - 1;
    }
}
