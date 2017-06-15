package com.wikipediaSemanticAnalyser.extraction.predicate.verb.sequence;

import java.util.List;
import java.util.Set;

/**
 * Created by Oliver on 5/29/2017.
 */
public class VerbPredicateSequenceExtractorImpl implements VerbPredicateSequenceExtractor {

    @Override
    public String extract(List<String> tokensList, List<String> tagsList, int sequenceStartIndex, Set<String> allowedTags) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = sequenceStartIndex; i <= tagsList.size() - 1; i++) {
            String tag = tagsList.get(i);
            if (i == sequenceStartIndex) {
                stringBuilder.append(tokensList.get(i));
                stringBuilder.append(" ");
            } else if (allowedTags.contains(tag)) {
                stringBuilder.append(tokensList.get(i));
                stringBuilder.append(" ");
            } else {
                break;
            }
        }
        return stringBuilder.toString();
    }
}
