package com.alexrnv.multithreading.primes;

import com.alexrnv.multithreading.util.ReflectionUtil;
import com.alexrnv.multithreading.util.Stopwatch;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class V2PrimesCalculatorSkipEvenTest {

    @Test
    public void largestPrimeQuickEven() {
        Assert.assertEquals(997, V2_PrimesCalculatorSkipEven.largestPrime(1000));
    }

    @Test
    public void largestPrimeQuickOdd() {
        assertEquals(997, V2_PrimesCalculatorSkipEven.largestPrime(1001));
    }

    @Test
    public void largestPrimeQuickEven2() {
        assertEquals(997, V2_PrimesCalculatorSkipEven.largestPrime(998));
    }

    @Test
    public void largestPrimeQuickOdd2() {
        assertEquals(997, V2_PrimesCalculatorSkipEven.largestPrime(999));
    }

    @Test
    @Ignore
    public void largestPrimeOdd() {
        Stopwatch stopwatch = Stopwatch.start();
        assertEquals(2147483629, V2_PrimesCalculatorSkipEven.largestPrime(Integer.MAX_VALUE));
        System.out.printf("%s: %s\n", ReflectionUtil.getEnclosingMethodName(), stopwatch.timeElapsed());
    }

    @Test
    @Ignore
    public void largestPrimeEven() {
        Stopwatch stopwatch = Stopwatch.start();
        assertEquals(2147483629, V2_PrimesCalculatorSkipEven.largestPrime(Integer.MAX_VALUE - 1));
        System.out.printf("%s: %s\n", ReflectionUtil.getEnclosingMethodName(), stopwatch.timeElapsed());
    }
}