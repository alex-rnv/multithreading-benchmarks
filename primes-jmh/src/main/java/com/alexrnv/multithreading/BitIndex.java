package com.alexrnv.multithreading;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class BitIndex {

    @State(Scope.Thread)
    public static class IndexState {
         long index = 198537889;
    }

    @Benchmark
    @Fork(1)
    public int testWithDivision(IndexState state) {
        return (int)(state.index / 2 - 1);
    }

    @Benchmark
    @Fork(1)
    public int testWithShift(IndexState state) {
        return (int)(state.index >> 2 - 1);
    }
}
