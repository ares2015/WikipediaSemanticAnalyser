package com.wikipediaSemanticAnalyser.preprocessing.tokenizing;

/**
 * Created by Oliver on 6/13/2017.
 */
public interface Tokenizer {

    String removeSpecialCharacters(String token);

    String removeBrackets(String string, char bracket1, char bracket2);

    String removeDoubleQuotes(String sentence);

    String removeEmptyStrings(String sentence);

    String extractObjectFromURL(String url);

}