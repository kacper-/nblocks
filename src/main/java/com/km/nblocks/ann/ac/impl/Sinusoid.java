package com.km.nblocks.ann.ac.impl;

import com.km.nblocks.ann.ac.AC;

public class Sinusoid implements AC {
    public double f(double x) {
        return Math.sin(x);
    }

    public double f1(double x) {
        return Math.cos(x);
    }
}
