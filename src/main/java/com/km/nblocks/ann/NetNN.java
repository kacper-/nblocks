package com.km.nblocks.ann;

import com.km.nblocks.ann.ac.AC;
import com.km.nblocks.ann.dropout.DropOut;

import java.io.Serializable;
import java.util.Arrays;

class NetNN implements Serializable {
    private final Layer front;
    private final Layer back;
    private final Layer middle;
    private final Layer middle2;
    private final int size;

    NetNN(AC ac, DropOut dropOut, int size, int sizeLast, double lf) {
        front = new Layer(size, size, lf, ac, dropOut);
        middle = new Layer(size, size, lf, ac, dropOut);
        middle2 = new Layer(size, size, lf, ac, dropOut);
        back = new Layer(sizeLast, size, lf, ac, dropOut);
        this.size = size;
    }

    public double[] process(double[] signal) {
        front.process(signal);
        middle.process(front.getOutputs());
        middle2.process(middle.getOutputs());
        back.process(middle2.getOutputs());
        return back.getOutputs();
    }

    public void teach(double[] signal, double[] expected) {
        double[] result = process(signal);
        double[] backError = calculateBackError(result, expected);
        double[] middle2Error = calculateError(back.getWeights(), backError);
        double[] middleError = calculateError(middle2.getWeights(), middle2Error);
        double[] frontError = calculateError(middle.getWeights(), middleError);
        back.calculateWeightDeltas(backError);
        middle2.calculateWeightDeltas(middle2Error);
        middle.calculateWeightDeltas(middleError);
        front.calculateWeightDeltas(frontError);
        apply();
    }

    private double[] calculateBackError(double[] result, double[] expected) {
        double[] error = new double[result.length];
        for (int i = 0; i < error.length; i++) {
            error[i] = result[i] - expected[i];
        }
        return error;
    }

    private void apply() {
        front.applyWeightDeltas();
        middle.applyWeightDeltas();
        middle2.applyWeightDeltas();
        back.applyWeightDeltas();
    }

    private double[] calculateError(double[][] weights, double[] error) {
        double[] result = new double[weights[0].length];
        Arrays.fill(result, 0);
        for (int w = 0; w < result.length; w++) {
            for (int n = 0; n < weights.length; n++)
                result[w] += weights[n][w] * error[n];
        }
        return result;
    }

    public int getSize() {
        return size;
    }
}
