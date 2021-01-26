package com.alexrnv.multithreading.util;

import java.time.Duration;

public class Stopwatch {

    private final long startMs;

    private Stopwatch(long startMs) {
        this.startMs = startMs;
    }

    public static Stopwatch start() {
        return new Stopwatch(System.currentTimeMillis());
    }

    public String timeElapsed() {
        Duration duration = Duration.ofMillis(System.currentTimeMillis() - startMs);
        return duration.toString()
                .substring(2)
                .replaceAll("(\\d[HMS])(?!$)", "$1 ")
                .toLowerCase();
    }
}
