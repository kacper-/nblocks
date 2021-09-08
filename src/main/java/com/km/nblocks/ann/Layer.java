package com.km.nblocks.ann;

import com.km.nblocks.ann.ac.AC;
import com.km.nblocks.ann.dropout.DropOut;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

class Layer implements Serializable {
    private final static double WEIGHT_INIT_LIMIT = 0.05d;
    private final int neuronCount;
    private final int weightCount;
    private final double learningFactor;
    private final AC ac;
    private final DropOut dropOut;
    private double[][] weights;
    private double[][] weightDeltas;
    private double[] outputs;
    private double[] inputs;

    Layer(int neuronCount, int weightCount, double learningFactor, AC ac, DropOut dropOut) {
        this.ac = ac;
        this.dropOut = dropOut;
        this.learningFactor = learningFactor;
        this.neuronCount = neuronCount;
        this.weightCount = weightCount;
        initWeights();
        initInputs();
        initOutputs();
    }

    double[][] getWeights() {
        return weights;
    }

    private void initInputs() {
        inputs = new double[weightCount];
    }

    double[] getOutputs() {
        return outputs;
    }

    private void initOutputs() {
        outputs = new double[neuronCount];
    }

    private void initWeights() {
        weights = new double[neuronCount][weightCount];
        weightDeltas = new double[neuronCount][weightCount];
        for (int n = 0; n < neuronCount; n++) {
            for (int w = 0; w < weightCount; w++) {
                weights[n][w] = (ThreadLocalRandom.current().nextDouble() * WEIGHT_INIT_LIMIT);
                weightDeltas[n][w] = 0d;
            }
        }
    }

    void process(double[] signal) {
        if (signal.length != weightCount)
            throw new IllegalArgumentException(String.format("Wrong signal size expected = %d actual = %d", weightCount, signal.length));
        saveInputs(signal);
        for (int n = 0; n < neuronCount; n++) {
            outputs[n] = 0;
            outputs[n] = ac.f(combinedSignal(n));
        }
    }

    private double combinedSignal(int n) {
        double o = 0d;
        for (int w = 0; w < weightCount; w++) {
            o += weights[n][w] * inputs[w];
        }
        return o;
    }

    private void saveInputs(double[] signal) {
        System.arraycopy(signal, 0, inputs, 0, weightCount);
    }

    void calculateWeightDeltas(double[] outputDiff) {
        double f1Val;
        for (int n = 0; n < neuronCount; n++) {
            f1Val = ac.f1(combinedSignal(n));
            for (int w = 0; w < weightCount; w++) {
                weightDeltas[n][w] = dropOut.apply(learningFactor * outputDiff[n] * f1Val * inputs[w]);
            }
        }
    }

    void applyWeightDeltas() {
        for (int n = 0; n < neuronCount; n++) {
            for (int w = 0; w < weightCount; w++) {
                weights[n][w] -= weightDeltas[n][w];
                weightDeltas[n][w] = 0d;
            }
        }
    }
}
