package com.alexrnv.multithreading.primes;

import com.alexrnv.multithreading.util.ReflectionUtil;
import com.alexrnv.multithreading.util.Stopwatch;
import org.junit.Ignore;
import org.junit.Test;

import static com.alexrnv.multithreading.primes.SkipEvenByteArraySieve.StartFrom.*;
import static org.junit.Assert.assertEquals;

public class V5PrimesCalculatorMarkingWithRacesTest {

    @Test
    public void largestPrimeQuickEven() throws InterruptedException {
        assertEquals(997, new V5_PrimesCalculatorParallelMarkingWithRaces(1000, PRIME, 1, true).largestPrime());
        assertEquals(997, new V5_PrimesCalculatorParallelMarkingWithRaces(1000, PRIME, 2, true).largestPrime());
        assertEquals(997, new V5_PrimesCalculatorParallelMarkingWithRaces(1000, PRIME, 3, false).largestPrime());
        assertEquals(997, new V5_PrimesCalculatorParallelMarkingWithRaces(1000, TRIPLE_PRIME, 1, false).largestPrime());
        assertEquals(997, new V5_PrimesCalculatorParallelMarkingWithRaces(1000, TRIPLE_PRIME, 2, true).largestPrime());
        assertEquals(997, new V5_PrimesCalculatorParallelMarkingWithRaces(1000, TRIPLE_PRIME, 3, false).largestPrime());
        assertEquals(997, new V5_PrimesCalculatorParallelMarkingWithRaces(1000, PRIME_SQUARE, 1, true).largestPrime());
        assertEquals(997, new V5_PrimesCalculatorParallelMarkingWithRaces(1000, PRIME_SQUARE, 4, false).largestPrime());
    }

    @Test
    public void largestPrimeQuickOdd() throws InterruptedException {
        assertEquals(997, new V5_PrimesCalculatorParallelMarkingWithRaces(1001, PRIME, 1, false).largestPrime());
    }

    @Test

    public void largestPrimeQuickEven2() throws InterruptedException {
        assertEquals(997,  new V5_PrimesCalculatorParallelMarkingWithRaces(998, PRIME, 1, false).largestPrime());
    }

    @Test
    public void largestPrimeQuickOdd2() throws InterruptedException {
        assertEquals(997,  new V5_PrimesCalculatorParallelMarkingWithRaces(999, PRIME, 1, false).largestPrime());
    }

    @Test
    @Ignore
    public void largestPrimeOdd() throws InterruptedException {
        Stopwatch stopwatch = Stopwatch.start();
        assertEquals(2147483629,  new V5_PrimesCalculatorParallelMarkingWithRaces(Integer.MAX_VALUE, PRIME, 4, false).largestPrime());
        System.out.printf("%s: %s\n", ReflectionUtil.getEnclosingMethodName(), stopwatch.timeElapsed());
    }

    @Test
    @Ignore
    public void largestPrimeEven() throws InterruptedException {
        Stopwatch stopwatch = Stopwatch.start();
        assertEquals(2147483629,  new V5_PrimesCalculatorParallelMarkingWithRaces(Integer.MAX_VALUE -1, PRIME, 4, false).largestPrime());
        System.out.printf("%s: %s\n", ReflectionUtil.getEnclosingMethodName(), stopwatch.timeElapsed());
    }
}