package com.alexrnv.multithreading;

import com.alexrnv.multithreading.primes.SkipEvenBitSetSieve;
import com.alexrnv.multithreading.primes.SkipEvenByteArraySieve;
import com.alexrnv.multithreading.primes.V2_PrimesCalculatorSkipEven;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

import static com.alexrnv.multithreading.primes.SkipEvenByteArraySieve.StartFrom.PRIME_SQUARE;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.SECONDS)
@Fork(1)
public class PrimesCalculatorSkipEvenBenchmark {

    @State(Scope.Thread)
    public static class BitSetSieve {
        int N = Integer.MAX_VALUE;
        SkipEvenBitSetSieve sieve;

        @Setup(Level.Invocation)
        public void setUp() {
            sieve = new SkipEvenBitSetSieve(N);
        }
    }

    @State(Scope.Thread)
    public static class ByteArraySieve {
        int N = Integer.MAX_VALUE;
        SkipEvenByteArraySieve sieve;

        @Setup(Level.Invocation)
        public void setUp() {
            sieve = new SkipEvenByteArraySieve(N, PRIME_SQUARE);
        }
    }

    @Benchmark
    public long testWithBitSetSieve(BitSetSieve state) {
        return V2_PrimesCalculatorSkipEven.largestPrime(state.sieve);
    }

    @Benchmark
    public long testWithByteArraySieve(ByteArraySieve state) {
        return V2_PrimesCalculatorSkipEven.largestPrime(state.sieve);
    }
}
