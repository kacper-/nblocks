package com.km.nblocks.ann;

import com.km.nblocks.ann.ac.impl.SoftSign;
import com.km.nblocks.ann.dropout.impl.BooleanDropOut;

import java.util.concurrent.ThreadLocalRandom;

public class NetUtil {
    public static NetNN createNet(NetConfig config) {
        return new NetNN(config.getAc(), config.getDropOut(), config.getSize(), config.getSizeLast(), config.getLf());
    }

    public static void train(NetTrainConfig trainSet) {

        if (trainSet.getSignal()[0].length != trainSet.getNet().getSize()) {
            throw new IllegalArgumentException(String.format("Wrong size of signal[][x] expected = %d actual = %d", trainSet.getNet().getSize(), trainSet.getSignal()[0].length));
        }
        if (trainSet.getSignal().length != trainSet.getExpected().length) {
            throw new IllegalArgumentException(String.format("Different size of signal and expected result, signal = %d expected result = %d", trainSet.getSignal().length, trainSet.getExpected().length));
        }
        for (int i = 0; i < trainSet.getRep(); i++) {
            int j = ThreadLocalRandom.current().nextInt(trainSet.getSignal().length);
            trainSet.getNet().teach(trainSet.getSignal()[j], trainSet.getExpected()[j]);
        }
    }

    public static NetConfig getNN() {
        return new NetConfig(new SoftSign(), new BooleanDropOut(), 16, 16, 0.05d);
    }

    public static NetConfig getN1() {
        return new NetConfig(new SoftSign(), new BooleanDropOut(), 16, 1, 0.02d);
    }
}
