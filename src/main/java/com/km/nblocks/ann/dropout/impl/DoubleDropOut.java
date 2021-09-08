package com.km.nblocks.ann.dropout.impl;

import com.km.nblocks.ann.dropout.DropOut;

import java.util.concurrent.ThreadLocalRandom;

public class DoubleDropOut implements DropOut {
    @Override
    public double apply(double x) {
        return x / ThreadLocalRandom.current().nextInt(1, 6);
    }
}
