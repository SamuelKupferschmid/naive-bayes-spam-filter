package com.samuelkupferschmid.fhnw.dist;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PerformanceTest {
    private Performance performance;

    @Before
    public void Setup() {
        performance = new Performance();
        performance.setPrecision(0.3);
        performance.setRecall(0.4);
    }

    @Test
    public void getF1Score() throws Exception {
        double f1score = 2 * (.4 *.3) / (.4 + .3);
        assertEquals(f1score,performance.getF1Score(),0);
    }

}