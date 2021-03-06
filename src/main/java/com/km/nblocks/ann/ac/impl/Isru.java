package com.km.nblocks.ann.ac.impl;

import com.km.nblocks.ann.ac.AC;

public class Isru implements AC {
    private final static double ALPHA = 0.9d;

    public double f(double x) {
        return x / Math.sqrt(1d + (ALPHA * x * x));
    }

    public double f1(double x) {
        return Math.pow(1d / Math.sqrt(1d + (ALPHA * x * x)), 3);
    }
}
