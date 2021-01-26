package com.alexrnv.multithreading.util;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Based on P. Lawrey's answer https://stackoverflow.com/questions/12424633/atomicbitset-implementation-for-java
 */
public class AtomicBitSet {
    private final int length;
    private final AtomicIntegerArray array;

    public AtomicBitSet(int length) {
        this.length = length;
        int intLength = (length + 31) >>> 5; // unsigned / 32
        array = new AtomicIntegerArray(intLength);
    }

    public void set(long n) {
        int bit = 1 << n;
        int idx = (int) (n >>> 5);
        while (true) {
            int num = array.get(idx);
            int num2 = num | bit;
            if (num == num2 || array.compareAndSet(idx, num, num2))
                return;
        }
    }

    public boolean get(long n) {
        int bit = 1 << n;
        int idx = (int) (n >>> 5);
        int num = array.get(idx);
        return (num & bit) != 0;
    }

    public int getLength() {
        return length;
    }
}
