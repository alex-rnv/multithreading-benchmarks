package com.alexrnv.multithreading.primes;

import com.alexrnv.multithreading.primes.V4_PrimesCalculatorSegmentedSieve;
import com.alexrnv.multithreading.util.ReflectionUtil;
import com.alexrnv.multithreading.util.Stopwatch;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class V4PrimesCalculatorSegmentedSieveTest {

    @Test
    public void largestPrimeQuick() {
        Stopwatch stopwatch = Stopwatch.start();
        assertEquals(997, new V4_PrimesCalculatorSegmentedSieve(16).largestPrime(1000));
        System.out.printf("%s: %s\n", ReflectionUtil.getEnclosingMethodName(), stopwatch.timeElapsed());
    }

    @Test
    public void testCorrectness() {
        assertEquals(997, new V4_PrimesCalculatorSegmentedSieve(7).largestPrime(1000));
        assertEquals(997, new V4_PrimesCalculatorSegmentedSieve(8).largestPrime(1000));
        assertEquals(997, new V4_PrimesCalculatorSegmentedSieve(9).largestPrime(1000));
        assertEquals(997, new V4_PrimesCalculatorSegmentedSieve(15).largestPrime(1000));
        assertEquals(997, new V4_PrimesCalculatorSegmentedSieve(16).largestPrime(1000));
        assertEquals(997, new V4_PrimesCalculatorSegmentedSieve(17).largestPrime(1000));
        assertEquals(997, new V4_PrimesCalculatorSegmentedSieve(30).largestPrime(1000));
        assertEquals(997, new V4_PrimesCalculatorSegmentedSieve(31).largestPrime(1000));
        assertEquals(997, new V4_PrimesCalculatorSegmentedSieve(31).largestPrime(1000));
        assertEquals(997, new V4_PrimesCalculatorSegmentedSieve(128).largestPrime(1000));
    }

    @Test
    @Ignore
    public void largestPrimeOdd() {
        Stopwatch stopwatch = Stopwatch.start();
        assertEquals(2147483629, new V4_PrimesCalculatorSegmentedSieve(64_000).largestPrime(Integer.MAX_VALUE));
        System.out.printf("%s: %s\n", ReflectionUtil.getEnclosingMethodName(), stopwatch.timeElapsed());
    }

    @Test
    @Ignore
    public void largestPrimeEven() {
        Stopwatch stopwatch = Stopwatch.start();
        assertEquals(2147483629, new V4_PrimesCalculatorSegmentedSieve( 1024*1024).largestPrime(Integer.MAX_VALUE - 1));
        System.out.printf("%s: %s\n", ReflectionUtil.getEnclosingMethodName(), stopwatch.timeElapsed());
    }
}