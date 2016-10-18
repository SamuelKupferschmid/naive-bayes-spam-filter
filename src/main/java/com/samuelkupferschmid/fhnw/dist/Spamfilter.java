package com.samuelkupferschmid.fhnw.dist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by samue on 18.10.2016.
 */
public class Spamfilter {

    Map<String,Integer> spam;
    Map<String,Integer> ham;

    public Spamfilter() {
        spam = new HashMap<String, Integer>();
        ham = new HashMap<String, Integer>();
    }

    public void feed(String content, boolean isSpam) {
        Map<String,Integer> map = isSpam ? spam : ham;

        for (String word :content.split(" ")) {
            Integer val = map.containsKey(word) ? map.get(word) : 0;

            map.put(word,++val);
        }
    }

    public void train() {

    }

    public boolean isSpam(String content) {
        return true;
    }
}
