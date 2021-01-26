package com.alexrnv.multithreading.primes;

/*
The next very important optimization comes from the fact, that 2 is the only even prime.
So we can skip all even numbers after that. That will allow us to reduce the storage and the
number of steps to perform on each iteration by 2.
Only SkipEven* sieve implementations are applicable here.
 */
public class V2_PrimesCalculatorSkipEven {

    public static long largestPrime(int limit) {
        SkipEvenBitSetSieve sieve = new SkipEvenBitSetSieve(limit);
        return largestPrime(sieve);
    }

    public static long largestPrime(Sieve sieve) {
        int limit = sieve.getSize();
        double sqrt = Math.sqrt(limit);
        for (int counter = 3; counter < sqrt; counter += 2) {
            if (sieve.isNotMarked(counter)) {
                sieve.mark(counter);
            }
        }

        int last = ((sieve.getSize()&1) == 0) ? sieve.getSize() - 1 : sieve.getSize() - 2;
        for (int j = last; j > 0; j -= 2) {
            if (sieve.isNotMarked(j)) {
                return j;
            }
        }
        return -1;
    }

}
