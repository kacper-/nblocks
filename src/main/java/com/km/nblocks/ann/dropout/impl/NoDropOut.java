package com.km.nblocks.ann.dropout.impl;

import com.km.nblocks.ann.dropout.DropOut;

public class NoDropOut implements DropOut {
    @Override
    public double apply(double x) {
        return x;
    }
}
