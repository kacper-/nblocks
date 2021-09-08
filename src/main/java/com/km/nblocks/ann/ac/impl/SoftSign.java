package com.km.nblocks.ann.ac.impl;

import com.km.nblocks.ann.ac.AC;

public class SoftSign implements AC {
    public double f(double x) {
        return x / (1d + Math.abs(x));
    }

    public double f1(double x) {
        return 1d / Math.pow(1d + Math.abs(x), 2);
    }
}
