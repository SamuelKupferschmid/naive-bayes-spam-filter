package com.samuelkupferschmid.fhnw.dist;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class DataTrainingTest {

    @Test
    public void Test() throws IOException, URISyntaxException {
        String[] spamTrain = DataHelper.getFileContents("spam-train");
        String[] hamTrain = DataHelper.getFileContents("ham-train");
        String[] spamValidation = DataHelper.getFileContents("spam-validation");
        String[] hamValidation = DataHelper.getFileContents("ham-validation");
        String[] spamTest = DataHelper.getFileContents("spam-test");
        String[] hamTest = DataHelper.getFileContents("ham-test");

        Spamfilter spamfilter = new Spamfilter(spamTrain,hamTrain);
        Trainer trainer = new Trainer(spamfilter, spamValidation, hamValidation);

        Performance perf = trainer.train(spamTest,hamTest,3);
    }
}
