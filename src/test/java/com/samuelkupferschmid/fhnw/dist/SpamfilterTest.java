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
        Spamfilter f = new Spamfilter(new String[]{spamContent},new String[]{});
        assertTrue(f.isSpam(spamContent));
    }

    @Test
    public void TestFeedCountContents() {

        Spamfilter f = new Spamfilter(new String[]{"viagra for free"},new String[]{});

        assertEquals(1,f.spamFeedings);
    }

    @Test
    public void TestSpam() {
        String spam = "viagra for free";
        String ham = "hello dear sir";

        Spamfilter f = new Spamfilter(new String[]{spam},new String[]{ham});

        assertTrue(f.isSpam(spam));
    }

    @Test
    public void TestHam() {
        String spam = "viagra for free";
        String ham = "hello dear sir";

        Spamfilter f = new Spamfilter(new String[]{spam},new String[]{ham});

        assertTrue(f.isSpam(spam));

        assertFalse(f.isSpam(ham));
    }

    @Test
    public void TestNormalizationExpand() {

        Spamfilter f = new Spamfilter(new String[]{"sex"},new String[]{"dear"});

        assertTrue(f.spam.containsKey("dear"));
        assertEquals(0,f.ham.get("sex"),.1);
    }

    @Test
    public void TestThresshold() {
        Spamfilter f = new Spamfilter(new String[]{"sex"},new String[]{"dear"});

        //default
        assertEquals(0.5,f.getSpamThreshhold(),0);
        f.setSpamThreshhold(0.2);
        assertEquals(0.2,f.getSpamThreshhold(),0);
    }

    @Test
    public void TestMixedHam() {
        Spamfilter f = new Spamfilter(
                new String[]{"there is spam","always spam", "one more","buy spam for free"},
                new String[]{"dear guest", "always good", "this is no spam but ham", "ham is super", "ham ham"});

        assertFalse(f.isSpam("this is ham not spam. so good !"));
    }

}