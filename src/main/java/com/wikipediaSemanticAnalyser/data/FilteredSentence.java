package com.wikipediaSemanticAnalyser.data;

import java.util.List;

/**
 * Created by oled on 6/15/2017.
 */
public class FilteredSentence {

    private List<String> filteredTags;

    private List<String> filteredTokens;

    public FilteredSentence(List<String> filteredTags, List<String> filteredTokens) {
        this.filteredTags = filteredTags;
        this.filteredTokens = filteredTokens;
    }

    public List<String> getFilteredTokens() {
        return filteredTokens;
    }

    public List<String> getFilteredTags() {
        return filteredTags;
    }
}
