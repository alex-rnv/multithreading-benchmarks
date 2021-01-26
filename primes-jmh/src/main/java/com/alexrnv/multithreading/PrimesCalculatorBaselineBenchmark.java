package com.alexrnv.multithreading;

import com.alexrnv.multithreading.primes.V0_PrimeCalculatorNaive;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.SECONDS)
@Fork(1)
public class PrimesCalculatorBaselineBenchmark {

    int N = Integer.MAX_VALUE;

    @Benchmark
    public int testWhileLoop() {
        return V0_PrimeCalculatorNaive.largestPrimeWhileLoop(N);
    }

    @Benchmark
    public int testForLoop() {
        return V0_PrimeCalculatorNaive.largestPrimeForLoop(N);
    }
}
