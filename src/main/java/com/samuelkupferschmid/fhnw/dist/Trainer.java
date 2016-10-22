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
        iterations++;

        Performance max = null;
        for(int i = 1; i < iterations; i++) {
            Performance perf = calculatePerformance(testSpam,testHam, i / iterations);

            if(max == null || max.getF1Score() < perf.getF1Score())
                max = perf;
        }

        return max;
    }

    public Performance calculatePerformance(String[] spam, String[] ham, double spamThreshold) {
        double prevThreshold = spamfilter.getSpamThreshhold();
        spamfilter.setSpamThreshhold(spamThreshold);

        Performance p = new Performance();

        int tp = 0;
        int fp = 0;
        int tn = 0;
        int fn = 0;

        for(String s : spam) {
            if(spamfilter.isSpam(s))
                tp++;
            else
                fn++;
        }

        for(String s : ham) {
            if(spamfilter.isSpam(s))
                fp++;
            else
                tn++;
        }

        p.setPrecision((double) tp / (tp + fp));
        p.setRecall((double) tp / (fp + fn));

        spamfilter.setSpamThreshhold(prevThreshold);
        return p;
    }

    public Spamfilter getSpamfilter() {
        return spamfilter;
    }

}
