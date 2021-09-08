package com.km.nblocks.ann;

public class NetTrainConfig {

    private final NetNN net;
    private final double[][] signal;
    private final double[][] expected;
    private final int rep;

    public NetTrainConfig(NetNN net, double[][] signal, double[][] expected, int rep) {
        this.net = net;
        this.signal = signal;
        this.expected = expected;
        this.rep = rep;
    }

    public NetNN getNet() {
        return net;
    }

    public double[][] getSignal() {
        return signal;
    }

    public double[][] getExpected() {
        return expected;
    }

    public int getRep() {
        return rep;
    }

}
