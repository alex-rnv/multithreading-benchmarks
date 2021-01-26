package com.alexrnv.multithreading.primes;

public class V0_PrimeCalculatorNaive {

    public static int largestPrimeWhileLoop(int limit) {
        BasicBitsetSieve sieve = new BasicBitsetSieve(limit);
        int prime = 0;
        int counter = 2;
        while (counter < limit ) {
            if (sieve.isNotMarked(counter)) {
                prime = counter;
                sieve.mark(prime);
            }
            counter++;
        }
        return prime;
    }

    public static int largestPrimeForLoop(int limit) {
        BasicBitsetSieve sieve = new BasicBitsetSieve(limit);
        int prime = 0;
        for (int counter = 2; counter < limit; counter++) {
            if (sieve.isNotMarked(counter)) {
                prime = counter;
                sieve.mark(prime);
            }
        }
        return prime;
    }

}
