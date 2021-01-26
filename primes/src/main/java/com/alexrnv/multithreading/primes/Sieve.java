package com.alexrnv.multithreading.primes;

public interface Sieve {
    void mark(int prime);
    boolean isNotMarked(int i);
    int getSize();
}
