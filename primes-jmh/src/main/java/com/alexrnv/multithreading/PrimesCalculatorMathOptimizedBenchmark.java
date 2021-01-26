package com.alexrnv.multithreading;

import com.alexrnv.multithreading.primes.V1_PrimesCalculatorSequentialOptimized;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.SECONDS)
@Fork(1)
public class PrimesCalculatorMathOptimizedBenchmark {

    int N = Integer.MAX_VALUE;

    @Benchmark
    public long test() {
        return V1_PrimesCalculatorSequentialOptimized.largestPrime(N);
    }
}
