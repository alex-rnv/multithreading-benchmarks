package com.alexrnv.multithreading.primes;

import com.alexrnv.multithreading.util.ReflectionUtil;
import com.alexrnv.multithreading.util.Stopwatch;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class V0PrimesCalculatorNaiveTest {

    @Test
    public void largestPrimeWhileLoopQuick() {
        assertEquals(997, V0_PrimeCalculatorNaive.largestPrimeWhileLoop(1000));
    }

    @Test
    public void largestPrimeForLoopQuick() {
        assertEquals(997, V0_PrimeCalculatorNaive.largestPrimeForLoop(1000));
    }

    @Test
    @Ignore
    public void largestPrimeWhileLoop() {
        Stopwatch stopwatch = Stopwatch.start();
        assertEquals(2147483629, V0_PrimeCalculatorNaive.largestPrimeWhileLoop(Integer.MAX_VALUE));
        System.out.printf("%s: %s\n", ReflectionUtil.getEnclosingMethodName(), stopwatch.timeElapsed());
    }

    @Test
    @Ignore
    public void largestPrimeForLoop() {
        Stopwatch stopwatch = Stopwatch.start();
        assertEquals(2147483629, V0_PrimeCalculatorNaive.largestPrimeForLoop(Integer.MAX_VALUE));
        System.out.printf("%s: %s\n", ReflectionUtil.getEnclosingMethodName(), stopwatch.timeElapsed());
    }
}