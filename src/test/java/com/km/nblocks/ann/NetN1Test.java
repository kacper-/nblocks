package com.km.nblocks.ann;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;

public class NetN1Test {
    private NetNN net;
    private double[][] signal;
    private double[][] expected;

    @Test
    public void process() {
        long start = Calendar.getInstance().getTimeInMillis();
        NetUtil.train(getTrainingSet());
        for (int i = 0; i < signal.length; i++) {
            double[] result = net.process(signal[i]);
            System.out.printf("%d : expected = %.3f actual = %.3f\n", i, expected[i][0], result[0]);
            Assert.assertEquals(expected[i][0], result[0], 0.05);
        }
        long stop = Calendar.getInstance().getTimeInMillis();
        System.out.printf("N1 test time = %d ms\n", stop - start);
    }

    private NetTrainConfig getTrainingSet() {
        NetConfig config = NetUtil.getN1();

        int COUNT = 10;
        signal = new double[COUNT][config.getSize()];
        expected = new double[COUNT][config.getSizeLast()];

        signal[0] = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        expected[0] = new double[]{0.0};
        signal[1] = new double[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        expected[1] = new double[]{0.1};
        signal[2] = new double[]{0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        expected[2] = new double[]{0.2};
        signal[3] = new double[]{0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        expected[3] = new double[]{0.3};
        signal[4] = new double[]{0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        expected[4] = new double[]{0.4};
        signal[5] = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0};
        expected[5] = new double[]{0.5};
        signal[6] = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0};
        expected[6] = new double[]{0.6};
        signal[7] = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0};
        expected[7] = new double[]{0.7};
        signal[8] = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0};
        expected[8] = new double[]{0.8};
        signal[9] = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
        expected[9] = new double[]{0.9};

        net = NetUtil.createNet(config);

        Assert.assertNotNull(net);

        return new NetTrainConfig(net, signal, expected, 1000000);
    }


}