package com.alexrnv.multithreading.primes;

import com.alexrnv.multithreading.util.ReflectionUtil;
import com.alexrnv.multithreading.util.Stopwatch;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class V3PrimesCalculatorParallelMarkingTest {

    @Test
    @Ignore
    public void largestPrime() throws InterruptedException {
        Stopwatch stopwatch = Stopwatch.start();
        assertEquals(2147483629, new V3_PrimesCalculatorParallelMarkingWithQueue(Integer.MAX_VALUE).largestPrime());
        System.out.printf("%s: %s\n", ReflectionUtil.getEnclosingMethodName(), stopwatch.timeElapsed());
    }
}