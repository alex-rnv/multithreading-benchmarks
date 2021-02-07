package com.alexrnv.multithreading.primes;

/**
 * Optimized version of the sieve that tracks only odd numbers.
 * Indexes start from 3 and increment with the step 2: 3,5,7.
 * Accessing the Sieve with even indexes will give incorrect results.
 * The main loop in the parent class is an example of correct iteration over the Sieve.
 * Current implementation allows to increase the upper limit to 2*Integer.MAX_VALUE-1.
 * Backed by byte array.
 * This particular version marks prime itself (if StartFrom.PRIME is chosen), as well as all the multiples. The logic of this
 * specific (and partially broken) implementation is described at
 * https://www.hpl.hp.com/techreports/2004/HPL-2004-209.pdf
 */
public class SkipEvenByteArraySieve implements Sieve {

    public enum StartFrom {
        PRIME, TRIPLE_PRIME, PRIME_SQUARE
    }

    private final int size;
    private final byte[] sieve;
    private final StartFrom startFrom;

    public SkipEvenByteArraySieve(int size, StartFrom startFrom) {
        this.size = size;
        this.sieve = new byte[(size >> 1) - 1];
        this.startFrom = startFrom;
    }

    @Override
    public void mark(int prime) {
        long start = prime;
        switch (this.startFrom) {
            case TRIPLE_PRIME:
                start *= 3;
            case PRIME_SQUARE:
                start *= prime;
        }
        for (long j = start; j < size; j += 2L * prime) {
            sieve[bitIndex(j)] = 1;
        }
    }

    @Override
    public boolean isNotMarked(int i) {
        return sieve[bitIndex(i)] == 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    private int bitIndex(long i) {
        return (int)((i >> 1) - 1);
    }
}
