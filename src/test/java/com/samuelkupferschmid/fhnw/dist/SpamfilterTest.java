package com.samuelkupferschmid.fhnw.dist;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by samue on 18.10.2016.
 */
public class SpamfilterTest {

    @Test
    public void TestSimpleSpam() {
        String spamContent = "viagra for free";
        Spamfilter f = new Spamfilter();

        f.feed(spamContent,true);
        f.train();
        assertTrue(f.isSpam(spamContent));
    }

    @Test
    public void TestFeedAddWords() {
        Spamfilter f = new Spamfilter();
        assertEquals(0,f.spam.size());

        f.feed("viagra for free",true);

        assertTrue(f.spam.containsKey("viagra"));
        assertTrue(f.spam.containsKey("for"));
        assertTrue(f.spam.containsKey("free"));
    }

    @Test
    public void TestFeedCountContents() {
        Spamfilter f = new Spamfilter();
        assertEquals(0,f.spamFeedings);

        f.feed("viagra for free",true);

        assertEquals(1,f.spamFeedings);
    }

    @Test
    public void TestSpamProbability() {
        Spamfilter f = new Spamfilter();

        String spam = "viagra for free";
        f.feed(spam,true);
        f.feed("hello dear sir",false);
        f.train();

        assertEquals(1,f.spamProbability(spam),.1);
    }

    @Test
    public void TestSpamProbabilityWithHam() {
        Spamfilter f = new Spamfilter();

        String spam = "viagra for free";
        String ham = "hello dear sir";

        f.feed(spam,true);
        f.feed(ham,false);
        f.train();

        assertEquals(0,f.spamProbability(ham),.1);
    }

    @Test
    public void TestNormalizationExpand() {
        Spamfilter f = new Spamfilter();
        f.feed("sex",true);
        f.feed("work",false);

        f.train();

        assertTrue(f.spam.containsKey("work"));
        assertEquals(0,f.ham.get("sex"),.1);
    }

}