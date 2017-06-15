package com.wikipediaSemanticAnalyser.data;

/**
 * Created by oled on 6/15/2017.
 */
public class HaveBeenSequenceIndexes {

    private int startIndex;

    private int endIndex;

    public HaveBeenSequenceIndexes(int startIndex, int endIndex) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }
}
