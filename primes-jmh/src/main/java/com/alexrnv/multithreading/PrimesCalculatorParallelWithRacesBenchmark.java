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

    private static int firstOddPrime = 3;

    @State(Scope.Benchmark)
    public static class Sieve {
        int N = Integer.MAX_VALUE;
        int sqrt = (int)Math.sqrt(N);
        SkipEvenByteArraySieve sieve;

        @Setup(Level.Invocation)
        public void setUp() {
            sieve = new SkipEvenByteArraySieve(N, SkipEvenByteArraySieve.StartFrom.PRIME);
        }
    }

    @State(Scope.Benchmark)
    public static class SieveJumpTo3P {
        int N = Integer.MAX_VALUE;
        int sqrt = (int)Math.sqrt(N);
        SkipEvenByteArraySieve sieve;

        @Setup(Level.Invocation)
        public void setUp() {
            sieve = new SkipEvenByteArraySieve(N, SkipEvenByteArraySieve.StartFrom.TRIPLE_PRIME);
        }
    }

    @State(Scope.Benchmark)
    public static class SieveJumpToPP {
        int N = Integer.MAX_VALUE;
        int sqrt = (int)Math.sqrt(N);
        SkipEvenByteArraySieve sieve;

        @Setup(Level.Invocation)
        public void setUp() {
            sieve = new SkipEvenByteArraySieve(N, SkipEvenByteArraySieve.StartFrom.PRIME_SQUARE);
        }
    }

    @State(Scope.Group)
    public static class GroupSieve {
        int N = Integer.MAX_VALUE;
        int sqrt = (int)Math.sqrt(N);
        SkipEvenByteArraySieve sieve;

        @Setup(Level.Invocation)
        public void setUp() {
            sieve = new SkipEvenByteArraySieve(N, SkipEvenByteArraySieve.StartFrom.PRIME);
        }
    }

    @Benchmark
    @Threads(1)
    public boolean test_1Thread_PRIME_no_shuffle(Sieve state) {
        mark(state.sieve, state.sqrt, firstOddPrime);
        return state.sieve.isNotMarked(state.N - 2);
    }

    @Benchmark
    @Threads(2)
    public boolean test_2Threads_PRIME_no_shuffle(Sieve state) {
        mark(state.sieve, state.sqrt, firstOddPrime);
        return state.sieve.isNotMarked(state.N - 2);
    }

    @Benchmark
    @Threads(3)
    public boolean test_3Threads_PRIME_no_shuffle(Sieve state) {
        mark(state.sieve, state.sqrt, firstOddPrime);
        return state.sieve.isNotMarked(state.N - 2);
    }

    @Benchmark
    @Threads(4)
    public boolean test_4Threads_PRIME_no_shuffle(Sieve state) {
        mark(state.sieve, state.sqrt, firstOddPrime);
        return state.sieve.isNotMarked(state.N - 2);
    }

    @Benchmark
    @Threads(4)
    public boolean test_4Threads_TRIPLE_PRIME_no_shuffle(SieveJumpTo3P state) {
        mark(state.sieve, state.sqrt, firstOddPrime);
        return state.sieve.isNotMarked(state.N - 2);
    }

    @Benchmark
    @Threads(4)
    public boolean test_4Threads_PRIME_SQUARE_no_shuffle(SieveJumpToPP state) {
        mark(state.sieve, state.sqrt, firstOddPrime);
        return state.sieve.isNotMarked(state.N - 2);
    }


    @Benchmark
    @Threads(-1)
    public boolean test_MaxCpuThreads_PRIME_no_shuffle(Sieve state) {
        mark(state.sieve, state.sqrt, firstOddPrime);
        return state.sieve.isNotMarked(state.N - 2);
    }

    @Benchmark
    @Group("test_4Threads_PRIME_shuffle")
    @GroupThreads(1)
    public boolean test_4Threads_PRIME_shuffle_3(GroupSieve state) {
        mark(state.sieve, state.sqrt, 3);
        return state.sieve.isNotMarked(state.N - 2);
    }

    @Benchmark
    @Group("test_4Threads_PRIME_shuffle")
    @GroupThreads(1)
    public boolean test_4Threads_PRIME_shuffle_5(GroupSieve state) {
        mark(state.sieve, state.sqrt, 5);
        return state.sieve.isNotMarked(state.N - 2);
    }

    @Benchmark
    @Group("test_4Threads_PRIME_shuffle")
    @GroupThreads(1)
    public boolean test_4Threads_PRIME_shuffle_7(GroupSieve state) {
        mark(state.sieve, state.sqrt, 7);
        return state.sieve.isNotMarked(state.N - 2);
    }

    @Benchmark
    @Group("test_4Threads_PRIME_shuffle")
    @GroupThreads(1)
    public boolean test_4Threads_PRIME_shuffle_11(GroupSieve state) {
        mark(state.sieve, state.sqrt, 11);
        return state.sieve.isNotMarked(state.N - 2);
    }

}
