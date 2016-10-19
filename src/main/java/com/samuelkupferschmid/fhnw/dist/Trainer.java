package com.samuelkupferschmid.fhnw.dist;

public class Trainer {
    private Spamfilter spamfilter;

    public Trainer(Spamfilter spamfilter) {
        this.spamfilter = spamfilter;
    }

    public Performance train(String[] validationSpam, String[] validationHam, String[] testSpam, String[] testHam, int iterations) {
        return null;
    }

    public Performance calculatePerformance(double spamThreshold) {
     return null;
    }

    public Performance MaximizeFScore(int samples) {
        return null;
    }

    public Spamfilter getSpamfilter() {
        return spamfilter;
    }

}
