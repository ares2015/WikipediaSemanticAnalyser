package com.wikipediaSemanticAnalyser.data.crawling;

/**
 * Created by Oliver on 6/26/2017.
 */
public class SentenceObjectData {

    private String sentence;

    private String object;

    public SentenceObjectData(String sentence, String object) {
        this.sentence = sentence;
        this.object = object;
    }

    public String getSentence() {
        return sentence;
    }

    public String getObject() {
        return object;
    }

}
