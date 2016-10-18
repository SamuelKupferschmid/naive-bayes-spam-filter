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

}