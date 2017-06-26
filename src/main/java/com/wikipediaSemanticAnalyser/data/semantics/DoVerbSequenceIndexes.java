package com.wikipediaSemanticAnalyser.data.semantics;

/**
 * Created by oled on 6/15/2017.
 */
public class DoVerbSequenceIndexes {

    private int startIndex = -1;

    private int endIndex = -1;

    public DoVerbSequenceIndexes() {

    }

    public DoVerbSequenceIndexes(int startIndex, int endIndex) {
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
