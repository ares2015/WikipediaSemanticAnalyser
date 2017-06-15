package com.wikipediaSemanticAnalyser.tokenizing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Oliver on 6/13/2017.
 */
public class TokenizerImpl implements Tokenizer {

    public String removeSpecialCharacters(String token) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = token.toCharArray();
        for (char c : chars) {
            if ((!",".equals(String.valueOf(c)) || "'".equals(String.valueOf(c)) || "’".equals(String.valueOf(c))) || ((Character.isDigit(c) || Character.isLetter(c)))) {
                stringBuilder.append(String.valueOf(c));
            }
        }
        return stringBuilder.toString();
    }

    public String removeBrackets(String token, char bracket1, char bracket2) {
        StringBuilder filteredSentence = new StringBuilder();
        char[] chars = token.toCharArray();
        boolean isNestedSequence = false;
        for (int i = 0; i < chars.length; i++) {
            if (bracket1 == chars[i]) {
                isNestedSequence = true;
            } else if (bracket2 == chars[i]) {
                isNestedSequence = false;
            } else if (bracket1 != chars[i] && !isNestedSequence) {
                filteredSentence.append(String.valueOf(chars[i]));
            }
        }
        return filteredSentence.toString();
    }

    public String removeDoubleQuotes(String string) {
        return string.replace("\"", "");
    }

    public String removeEmptyStrings(String string) {
        List<String> list = Arrays.asList(string.split("\\ "));
        final List<String> newList = new ArrayList<String>();
        StringBuilder newString = new StringBuilder();
        for (final String s : list) {
            if (!(s.equals(""))) {
                newList.add(s);
            }
        }

        for (int i = 0; i < newList.size(); i++) {
            newString.append(newList.get(i));
            if (i < newList.size() - 1) {
                newString.append(" ");
            }
        }
        return newString.toString();
    }

    @Override
    public String extractObjectFromURL(String url) {
        String[] split = url.split(" ");
        String[] splitWithWiki = split[1].split("/wiki/");
        return splitWithWiki[1].substring(0, splitWithWiki[1].length() - 1);
    }

    @Override
    public List<String> splitStringIntoList(String sentence) {
        String[] tokTmp;
        tokTmp = sentence.split("\\ ");
        final List<String> tokens = removeEmptyStringInSentence(tokTmp);
        List<String> filteredTokens = new ArrayList<>();
        for (String token : tokens) {
            token = removeEmptyCharsFromToken(token);
            filteredTokens.add(token);
        }
        return filteredTokens;
    }

    /**
     * Removes empty String that is located in sentences with index > 0
     * that is created by empty space behind each sentence (space between
     * end of the sentence and the start of the new sentence). It would be more
     * efficient to remove only first token (empty string) but this loop guarantees
     * that all accidental empty strings are removed.
     *
     * @param tokens Array of tokens with empty strings.
     * @return List of tokens without empty strings.
     */
    private List<String> removeEmptyStringInSentence(final String[] tokens) {
        final List<String> listTokens = new ArrayList<String>();
        for (final String token : tokens) {
            if (!token.equals("")) {
                listTokens.add(token);
            }
        }
        return listTokens;
    }

    private String removeEmptyCharsFromToken(String token) {
        char[] charTmp;
        charTmp = token.toCharArray();
        int numberOfEmptyChars = 0;
        for (int i = 0; i <= charTmp.length - 1; i++) {
            if (charTmp[i] == '\uFEFF') {
                numberOfEmptyChars++;
            }
        }
        return new String(Arrays.copyOfRange(charTmp, numberOfEmptyChars, charTmp.length));
    }
}
