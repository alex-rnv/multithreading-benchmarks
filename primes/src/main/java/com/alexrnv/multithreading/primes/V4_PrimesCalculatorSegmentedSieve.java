package com.alexrnv.multithreading.primes;

import java.util.ArrayList;
import java.util.BitSet;

/**
 * Further sequential optimization over SkipEven version.
 */
public class V4_PrimesCalculatorSegmentedSieve {

    private final int blockSize;
    private final BitSet block;

    public V4_PrimesCalculatorSegmentedSieve(int blockSize) {
        this.blockSize = blockSize;
        this.block = new BitSet(blockSize >> 1);
        block.set(0);
        block.set(1);
    }

    public long largestPrime(long limit) {
        ArrayList<Integer> basePrimes = new ArrayList<>();
        basePrimes.add(2);
        int base = (int)Math.sqrt(limit);
        SkipEvenBitSetSieve sieve = new SkipEvenBitSetSieve(base);
        for (int counter = 3; counter < base; counter += 2) {
            if (sieve.isNotMarked(counter)) {
                sieve.mark(counter);
                basePrimes.add(counter);
            }
        }

        long lastPrime = basePrimes.get(basePrimes.size()-1);
        int blockStart = (base / blockSize) * blockSize;
        for (long blockOffset = blockStart; blockOffset <= limit; blockOffset += blockSize) {
            int currentBlockSize = Math.min((int)(limit - blockOffset), blockSize);
            precalculate(blockOffset, basePrimes);

            for (int i = 1; i < currentBlockSize; i += 2) {
                if (!block.get(i >> 1))
                    lastPrime = blockOffset + i;
            }
            block.clear();
        }

        return lastPrime;
    }

    private void precalculate(long blockOffset, ArrayList<Integer> basePrimes) {
        for (int prime : basePrimes) {
            if (prime == 2L) continue;
            long startIndex = (blockOffset + prime - 1) / prime;
            long primeSqIdx = Math.max(startIndex, prime) * prime - blockOffset;
            if (primeSqIdx > blockSize) continue;
            int j = (int)primeSqIdx;
            if ((j & 1) == 0) j += prime;
            for (; j < blockSize; j += 2*prime) {
                block.set(j >> 1);
            }
        }
    }

}
