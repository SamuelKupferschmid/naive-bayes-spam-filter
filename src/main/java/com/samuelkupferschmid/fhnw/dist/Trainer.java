package com.samuelkupferschmid.fhnw.dist;

class Trainer {
    private Spamfilter spamfilter;
    private String[] validationSpam;
    private String[] validationHam;

    Trainer(Spamfilter spamfilter, String[] validationSpam, String[] validationHam) {
        this.spamfilter = spamfilter;
        this.validationSpam = validationSpam;
        this.validationHam = validationHam;
    }

    public Performance train(String[] testSpam, String[] testHam, int iterations) {
        return null;
    }

    Performance calculatePerformance(double spamThreshold) {
        double prevThreshold = spamfilter.getSpamThreshhold();
        spamfilter.setSpamThreshhold(spamThreshold);


        spamfilter.setSpamThreshhold(prevThreshold);
        return null;
    }

    Performance MaximizeFScore(int samples) {
        return null;
    }

    public Spamfilter getSpamfilter() {
        return spamfilter;
    }

}
