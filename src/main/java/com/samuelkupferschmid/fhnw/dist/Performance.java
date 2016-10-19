package com.samuelkupferschmid.fhnw.dist;

/**
 * Created by samue on 19.10.2016.
 */
public class Performance {
    private double precision;
    private double recall;

    public double getPrecision() {
        return precision;
    }

    public void setPrecision(double precision) {
        this.precision = precision;
    }

    public double getRecall() {
        return recall;
    }

    public void setRecall(double recall) {
        this.recall = recall;
    }

    public double getF1Score() {
        return 2 * (getPrecision() * getRecall()) / (getPrecision() + getRecall());
    }
}
