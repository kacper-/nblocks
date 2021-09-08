package com.km.nblocks.ann;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;

public class NetNNTest {
    private NetConfig config;
    private NetNN net;
    private double[][] signal;
    private double[][] expected;

    @Test
    public void processSimple() {
        long start = Calendar.getInstance().getTimeInMillis();
        NetUtil.train(getTrainingConfig());
        for (int i = 0; i < signal.length; i++) {
            double[] result = net.process(signal[i]);
            System.out.printf("%d\n", i);
            for (int j = 0; j < config.getSize(); j++) {
                if (result[j] > 0.5d) {
                    System.out.printf("\t%d expected=%.2f actual=%.2f\n", j, expected[i][j], result[j]);
                    Assert.assertEquals(expected[i][j], result[j], 0.1);
                }
            }
        }
        long stop = Calendar.getInstance().getTimeInMillis();
        System.out.printf("N1 test time = %d ms\n", stop - start);
    }

    private NetTrainConfig getTrainingConfig() {
        config = NetUtil.getNN();

        int COUNT = 10;
        signal = new double[COUNT][config.getSize()];
        expected = new double[COUNT][config.getSize()];

        signal[0] = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        expected[0] = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        signal[1] = new double[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        expected[1] = new double[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        signal[2] = new double[]{0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        expected[2] = new double[]{0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        signal[3] = new double[]{0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        expected[3] = new double[]{0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        signal[4] = new double[]{0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        expected[4] = new double[]{0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        signal[5] = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0};
        expected[5] = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0};
        signal[6] = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0};
        expected[6] = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0};
        signal[7] = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0};
        expected[7] = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0};
        signal[8] = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0};
        expected[8] = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0};
        signal[9] = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
        expected[9] = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1};

        net = NetUtil.createNet(config);

        Assert.assertNotNull(net);

        return new NetTrainConfig(net, signal, expected, 1000000);
    }
}