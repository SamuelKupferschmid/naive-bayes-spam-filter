package com.samuelkupferschmid.fhnw.dist;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by samue on 18.10.2016.
 */
public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {

        String filename = "test.txt";

        System.out.println("Load training Data...");
        String[] spamTrain = DataHelper.getFileContents("spam-train");
        String[] hamTrain = DataHelper.getFileContents("ham-train");
        String[] spamValidation = DataHelper.getFileContents("spam-validation");
        String[] hamValidation = DataHelper.getFileContents("ham-validation");
        String[] spamTest = DataHelper.getFileContents("spam-test");
        String[] hamTest = DataHelper.getFileContents("ham-test");

        Spamfilter spamfilter = new Spamfilter(spamTrain,hamTrain);
        Trainer trainer = new Trainer(spamfilter, spamValidation, hamValidation);

        System.out.println("Find best Threshold...");
        Performance perf = trainer.train(spamTest,hamTest,4);

        System.out.println("Threshold: " + spamfilter.getSpamThreshhold());
        System.out.println("Precision: " + perf.getPrecision());
        System.out.println("Recall: " + perf.getRecall());
        System.out.println("F1 Score: " + perf.getF1Score());

        System.out.println("Check your Mail...");

        String content = new String(Files.readAllBytes(Paths.get(filename)));

        if(spamfilter.isSpam(content))
            System.out.println("This mail is probably spam!");
        else
            System.out.println("This mail looks fine...");

    }
}
