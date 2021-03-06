package com.km.nblocks.ann.ac.impl;

import com.km.nblocks.ann.ac.AC;

public class Logistic implements AC {
    public double f(double x) {
        return 1d / (1d + Math.exp(-x));
    }

    public double f1(double x) {
        return f(x) * (1d - f(x));
    }

}
