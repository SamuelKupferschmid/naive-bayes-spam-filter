package com.samuelkupferschmid.fhnw.dist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by samue on 18.10.2016.
 */
public class Spamfilter {

    Map<String,Double> spam;
    Map<String,Double> ham;
    public int spamFeedings = 0;
    public int hamFeedings = 0;
    private final static double epsilon = 0.01;

    public Spamfilter() {
        spam = new HashMap<String, Double>();
        ham = new HashMap<String, Double>();
    }

    public void feed(String content, boolean isSpam) {
        Map<String,Double> map = isSpam ? spam : ham;

        for (String word :content.split(" ")) {
            Double val = map.containsKey(word) ? map.get(word) : 0;
            map.put(word,++val);
        }

        if(isSpam)
            spamFeedings++;
        else
            hamFeedings++;
    }

    public void train() {
        normalize(spam,spamFeedings,ham);
        normalize(ham,hamFeedings,spam);
    }

    private void normalize(Map<String,Double> map, int size, Map<String, Double> otherMap) {
        for(String word : map.keySet()) {
            map.put(word,map.get(word) / size);

            if(!otherMap.containsKey(word))
                otherMap.put(word,epsilon);
        }
    }

    public boolean isSpam(String content) {
        return true;
    }

    public double spamProbability(String content) {

        String[] words = content.split(" ");

        Double ph = 1.;
        Double ps = 1.;

        for(String word : words) {
            if(spam.containsKey(word))
                ps *= spam.get(word);

            if(ham.containsKey(word))
                ph *= ham.get(word);
        }


        return ps / (ph + ps);
    }
}
