package com.samuelkupferschmid.fhnw.dist;

import java.util.*;

public class Spamfilter {

    Map<String,Double> spam;
    Map<String,Double> ham;
    public int spamFeedings = 0;
    public int hamFeedings = 0;
    private final static double epsilon = 0.05;
    private double spamThreshhold = 0.5;

    public Spamfilter(String[] trainingSpam, String[] trainingHam) {
        spam = new HashMap<String, Double>();
        ham = new HashMap<String, Double>();

        for(String spam : trainingSpam){
            feed(spam,true);
        }

        for(String ham : trainingHam){
            feed(ham,false);
        }

        spamFeedings = trainingSpam.length;
        hamFeedings = trainingHam.length;

        cutRareWords(spam,1000);
        cutRareWords(ham,1000);

        normalize(spam,spamFeedings,ham);
        normalize(ham,hamFeedings,spam);
    }

    private void cutRareWords(Map<String,Double> data, int targetSize) {
        int pos = data.size() - targetSize;

        if(pos < 0)
            return;

        Double threshold = data.values().stream().sorted(Double::compare).skip(pos).findFirst().get();

        Object[] keys = data.keySet().toArray();
        for(Object k : keys) {
            if(data.get(k) < threshold) {
                data.remove(k);
            }
        }
    }

    private void feed(String content, boolean isSpam) {
        Map<String,Double> map = isSpam ? spam : ham;

        for (String word :content.split("\\s+")) {
            Double val = map.containsKey(word) ? map.get(word) : 0.;
            map.put(word,++val);
        }
    }

    private void normalize(Map<String,Double> map, int size, Map<String, Double> otherMap) {
        if(size == 0)
            size = 1;

        for(String word : map.keySet()) {
            map.put(word,map.get(word) / size);

            if(!otherMap.containsKey(word))
                otherMap.put(word,epsilon);
        }
    }

    public boolean isSpam(String content) {
        String[] words = content.split("\\s+");

        Double ph = 1.;
        Double ps = 1.;

        for(String word : words) {
            if(spam.containsKey(word))
                ps *= spam.get(word);

            if(ham.containsKey(word))
                ph *= ham.get(word);
        }

        ps *= spamThreshhold;
        ph *= 1 - spamThreshhold;

        return (ps / (ph + ps)) > 0.5;
    }

    public double getSpamThreshhold() {
        return spamThreshhold;
    }

    public void setSpamThreshhold(double spamThreshhold) {
        this.spamThreshhold = spamThreshhold;
    }
}
