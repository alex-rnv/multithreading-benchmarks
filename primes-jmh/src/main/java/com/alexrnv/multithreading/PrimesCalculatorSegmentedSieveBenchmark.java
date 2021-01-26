package com.alexrnv.multithreading;

import com.alexrnv.multithreading.primes.V4_PrimesCalculatorSegmentedSieve;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.SECONDS)
@Fork(1)
public class PrimesCalculatorSegmentedSieveBenchmark {

    @State(Scope.Thread)
    public static class BlockSize2M {
        int N = Integer.MAX_VALUE;
        int blockSize = 2*1024*1024;
        V4_PrimesCalculatorSegmentedSieve calculator;

        @Setup(Level.Invocation)
        public void setUp() {
            calculator = new V4_PrimesCalculatorSegmentedSieve(blockSize);
        }
    }

    @State(Scope.Thread)
    public static class BlockSize1M {
        int N = Integer.MAX_VALUE;
        int blockSize = 1024*1024;
        V4_PrimesCalculatorSegmentedSieve calculator;

        @Setup(Level.Invocation)
        public void setUp() {
            calculator = new V4_PrimesCalculatorSegmentedSieve(blockSize);
        }
    }

    @State(Scope.Thread)
    public static class BlockSize16K {
        int N = Integer.MAX_VALUE;
        int blockSize = 1024*16;
        V4_PrimesCalculatorSegmentedSieve calculator;

        @Setup(Level.Invocation)
        public void setUp() {
            calculator = new V4_PrimesCalculatorSegmentedSieve(blockSize);
        }
    }

    @State(Scope.Thread)
    public static class BlockSize256K {
        int N = Integer.MAX_VALUE;
        int blockSize = 1024*256;
        V4_PrimesCalculatorSegmentedSieve calculator;

        @Setup(Level.Invocation)
        public void setUp() {
            calculator = new V4_PrimesCalculatorSegmentedSieve(blockSize);
        }
    }

    @Benchmark
    public long testBlock2M(BlockSize2M state) {
        return state.calculator.largestPrime(state.N);
    }

    @Benchmark
    public long testBlock1M(BlockSize1M state) {
        return state.calculator.largestPrime(state.N);
    }

    @Benchmark
    public long testBlock256K(BlockSize256K state) {
        return state.calculator.largestPrime(state.N);
    }

    @Benchmark
    public long testBlock16K(BlockSize16K state) {
        return state.calculator.largestPrime(state.N);
    }
}
