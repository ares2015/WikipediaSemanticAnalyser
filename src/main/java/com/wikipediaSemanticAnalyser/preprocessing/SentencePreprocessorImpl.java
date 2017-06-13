package com.wikipediaSemanticAnalyser.preprocessing;

import com.wikipediaSemanticAnalyser.preprocessing.tokenizing.Tokenizer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oliver on 6/13/2017.
 */
public class SentencePreprocessorImpl implements SentencesPreprocessor {

    private Tokenizer tokenizer;

    public SentencePreprocessorImpl(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    @Override
    public String preprocess(String sentence) {
        String[] tokTmp;
        tokTmp = sentence.split("\\ ");
        List<String> preprocessedTokens = new ArrayList<>();
        for (String token : tokTmp) {
            if (token.contains(String.valueOf('['))) {
                token = tokenizer.removeBrackets(token, '[', ']');
            }
            token = tokenizer.removeSpecialCharacters(token);
            preprocessedTokens.add(token);
        }
        final List<String> tokens = removeEmptyStringInSentence(preprocessedTokens);
        return convertListToString(tokens);
    }

    private List<String> removeEmptyStringInSentence(List<String> filteredTokens) {
        final List<String> listTokens = new ArrayList<String>();
        for (final String token : filteredTokens) {
            if (!token.equals("")) {
                listTokens.add(token);
            }
        }
        return listTokens;
    }

    private String convertListToString(List<String> list) {
        String newString = "";
        int i = 0;
        for (String word : list) {
            if (i < list.size() - 1) {
                newString += word + " ";
            } else {
                newString += word;
            }
            i++;
        }
        return newString;
    }
}
