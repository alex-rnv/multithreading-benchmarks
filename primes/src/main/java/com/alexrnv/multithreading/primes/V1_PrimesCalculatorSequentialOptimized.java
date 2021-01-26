package com.alexrnv.multithreading.primes;

/**
 * Applied one well-known optimization, which results from the following fact:
 * by the time we reach index n in the sequential sieve algorithm, we know that all non-primes in the
 * range [n, n*n] are already marked during previous iterations.
 * Proof: indeed, for every prime p<n, the following is true: n<p*n<n*n and p*n is marked as non-prime at p-th
 * iteration.
 * This fact leads to two very important facts:
 * 1) marking for the prime p can start from p*p, with a step p
 * 2) it is enough to do algorithm iterations till sqrt(limit), to mark all non-primes below that limit.
 *
 * The first idea is captured in Sequential* sieve implementations.
 * Also, it is important to mention, that the fact N1 may not work in different parallel workflows.
 *
 */
public class V1_PrimesCalculatorSequentialOptimized {

    public static int largestPrime(int limit) {
        SequentialBitSetSieve sieve = new SequentialBitSetSieve(limit);
        return largestPrime(sieve);
    }

    public static int largestPrime(Sieve sieve) {
        int limit = sieve.getSize();
        int sqrt = (int)Math.sqrt(limit);
        for (int counter = 2; counter <= sqrt; counter++) {
            if (sieve.isNotMarked(counter)) {
                sieve.mark(counter);
            }
        }

        for (int j = sieve.getSize() - 1; j > 0; j--) {
            if (sieve.isNotMarked(j)) {
                return j;
            }
        }
        return -1;
    }

}
