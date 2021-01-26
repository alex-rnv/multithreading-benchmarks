package com.alexrnv.multithreading;

import com.alexrnv.multithreading.primes.SkipEvenByteArraySieve;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

import static com.alexrnv.multithreading.primes.V5_PrimesCalculatorParallelMarkingWithRaces.MarkingThread.mark;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.SECONDS)
@Fork(1)
public class PrimesCalculatorParallelWithRacesBenchmark {

    @State(Scope.Benchmark)
    public static class Sieve {
        int N = Integer.MAX_VALUE;
        int sqrt = (int)Math.sqrt(N);
        SkipEvenByteArraySieve.StartFrom start = SkipEvenByteArraySieve.StartFrom.PRIME;
        SkipEvenByteArraySieve sieve;

        @Setup(Level.Invocation)
        public void setUp() {
            sieve = new SkipEvenByteArraySieve(N, start);
        }
    }

    @Benchmark
    @Threads(1)
    public boolean test_1Thread_PRIME_shuffle(Sieve state) {
        mark(state.sieve, state.sqrt, 3);
        return state.sieve.isNotMarked(state.N - 2);
    }

    @Benchmark
    @Threads(2)
    public boolean test_2Threads_PRIME_shuffle(Sieve state) {
        mark(state.sieve, state.sqrt, 3);
        return state.sieve.isNotMarked(state.N - 2);
    }

    @Benchmark
    @Threads(3)
    public boolean test_3Threads_PRIME_shuffle(Sieve state) {
        mark(state.sieve, state.sqrt, 3);
        return state.sieve.isNotMarked(state.N - 2);
    }

    @Benchmark
    @Threads(4)
    public boolean test_4Threads_PRIME_shuffle(Sieve state) {
        mark(state.sieve, state.sqrt, 3);
        return state.sieve.isNotMarked(state.N - 2);
    }

    @Benchmark
    @Threads(-1)
    public boolean test_MaxCpuThreads_PRIME_shuffle(Sieve state) {
        mark(state.sieve, state.sqrt, 3);
        return state.sieve.isNotMarked(state.N - 2);
    }
}
