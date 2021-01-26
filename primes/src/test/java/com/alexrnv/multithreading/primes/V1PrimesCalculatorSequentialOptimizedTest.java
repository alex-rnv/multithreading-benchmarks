package com.alexrnv.multithreading.primes;

import com.alexrnv.multithreading.util.ReflectionUtil;
import com.alexrnv.multithreading.util.Stopwatch;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class V1PrimesCalculatorSequentialOptimizedTest {

    @Test
    public void largestPrimeQuick() {
        Assert.assertEquals(997, V1_PrimesCalculatorSequentialOptimized.largestPrime(1000));
    }

    @Test
    @Ignore
    public void largestPrime() {
        Stopwatch stopwatch = Stopwatch.start();
        assertEquals(2147483629, V1_PrimesCalculatorSequentialOptimized.largestPrime(Integer.MAX_VALUE));
        System.out.printf("%s: %s\n", ReflectionUtil.getEnclosingMethodName(), stopwatch.timeElapsed());
    }
}