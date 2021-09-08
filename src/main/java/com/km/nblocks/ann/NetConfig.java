package com.km.nblocks.ann;

import com.km.nblocks.ann.ac.AC;
import com.km.nblocks.ann.dropout.DropOut;

public class NetConfig {
    private final AC ac;
    private final DropOut dropOut;
    private final int size;
    private final int sizeLast;
    private final double lf;

    public NetConfig(AC ac, DropOut dropOut, int size, int sizeLast, double lf) {
        this.ac = ac;
        this.dropOut = dropOut;
        this.size = size;
        this.sizeLast = sizeLast;
        this.lf = lf;
    }

    public AC getAc() {
        return ac;
    }

    public DropOut getDropOut() {
        return dropOut;
    }

    public int getSize() {
        return size;
    }

    public int getSizeLast() {
        return sizeLast;
    }

    public double getLf() {
        return lf;
    }
}
