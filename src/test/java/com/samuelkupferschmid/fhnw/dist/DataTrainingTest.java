package com.samuelkupferschmid.fhnw.dist;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class DataTrainingTest {
    private static String[] spamTrain;
    private static String[] hamTrain;
    private static String[] spamValidation;
    private static String[] hamValidation;
    private static String[] spamTest;
    private static String[] hamTest;

    @BeforeClass
    public static void Setup() throws IOException, URISyntaxException {
        spamTrain = DataHelper.getFileContents("spam-train");
        hamTrain = DataHelper.getFileContents("ham-train");
        spamValidation = DataHelper.getFileContents("spam-validation");
        hamValidation = DataHelper.getFileContents("ham-validation");
        spamTest = DataHelper.getFileContents("spam-test");
        hamTest = DataHelper.getFileContents("ham-test");
    }

    @Test
    public void TestTrainer() {

        Spamfilter spamfilter = new Spamfilter(spamTrain,hamTrain);
        Trainer trainer = new Trainer(spamfilter, spamValidation, hamValidation);

        Performance perf = trainer.train(spamTest,hamTest,10);

        assertTrue(String.valueOf("f1 score after training: " +perf.getF1Score()), perf.getF1Score() > 0.5);
    }

    @Test
    public void TestSpamFilter() {

        Spamfilter spamfilter = new Spamfilter(spamTrain,hamTrain);
        Performance perf = new Trainer(spamfilter,null,null).calculatePerformance(spamTest,hamTest,0.5);
        assertTrue(String.valueOf(perf.getF1Score()), perf.getF1Score() > 0.5);
    }

    @Test
    public void TestSpamFilter2() {
        Spamfilter spamfilter = new Spamfilter(new String[]{"Dies ist Spam"},new String[]{"Hier haben wir Ham"});
        Performance perf = new Trainer(spamfilter,null,null).calculatePerformance(new String[]{"Spam is hier"},new String[]{"Ham haben wir auch viel"},0.5);
        assertTrue(String.valueOf(perf.getF1Score()), perf.getF1Score() > 0.5);
    }
}
