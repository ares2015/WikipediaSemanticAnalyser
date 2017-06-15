package com.wikipediaSemanticAnalyser.extraction.predicate.noun;


import com.wikipediaSemanticAnalyser.data.SemanticExtractionData;
import com.wikipediaSemanticAnalyser.data.SemanticPreprocessingData;
import com.wikipediaSemanticAnalyser.tags.Tags;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Oliver on 2/16/2017.
 */
public class NounPredicateExtractorImpl implements NounPredicateExtractor {

    private final static Logger LOGGER = Logger.getLogger(NounPredicateExtractorImpl.class.getName());


    @Override
    public void extract(SemanticExtractionData semanticExtractionData, SemanticPreprocessingData semanticPreprocessingData) {
        List<String> tokensList = semanticPreprocessingData.getTokensList();
        List<String> tagsList = semanticPreprocessingData.getTagsList();
        int extractionStartIndex = getExtractionStartIndex(semanticPreprocessingData);
        int afterVerbPrepositionIndex = semanticPreprocessingData.getAfterVerbFirstPrepositionIndex();
        if (!semanticPreprocessingData.containsAfterVerbVerbIng()) {
            String atomicNounPredicate = extractAtomicNounPredicate(tokensList, tagsList, extractionStartIndex,
                    afterVerbPrepositionIndex, semanticPreprocessingData.getVerbIndex());
            semanticExtractionData.setAtomicNounPredicate(atomicNounPredicate);
            LOGGER.info("Atomic noun predicate: " + atomicNounPredicate);
        }
        String extendedNounPredicate = extractExtendedNounPredicate(tokensList, tagsList, extractionStartIndex);
        semanticExtractionData.setExtendedNounPredicate(extendedNounPredicate);
        LOGGER.info("Extended noun predicate: " + extendedNounPredicate);
    }

    private String extractAtomicNounPredicate(List<String> tokensList, List<String> encodedTagsList,
                                              int extractionStartIndex, int afterVerbPrepositionIndex, int mainVerbIndex) {
        int lastNounIndex = getLastNounVerbEdIndex(encodedTagsList, extractionStartIndex, afterVerbPrepositionIndex, mainVerbIndex);
        if (lastNounIndex > -1) {
            return tokensList.get(lastNounIndex);
        } else {
            return "";
        }
    }

    private int getLastNounVerbEdIndex(List<String> encodedTagsList, int extractionStartIndex, int afterVerbPrepositionIndex,
                                       int mainVerbIndex) {
        int lastNounIndex = -1;
        if (afterVerbPrepositionIndex > -1) {
            for (int i = extractionStartIndex; i <= afterVerbPrepositionIndex; i++) {
                String tag = encodedTagsList.get(i);
                if (mainVerbIndex != i && (Tags.NOUN.equals(tag) || Tags.VERB_ED.equals(tag))) {
                    lastNounIndex = i;
                }
            }
        } else {
            for (int i = extractionStartIndex + 1; i < encodedTagsList.size(); i++) {
                String tag = encodedTagsList.get(i);
                if (Tags.NOUN.equals(tag) || Tags.VERB_ED.equals(tag)) {
                    lastNounIndex = i;
                }
            }
        }
        return lastNounIndex;
    }

    private String extractExtendedNounPredicate(List<String> tokensList, List<String> tagsList, int verbIndex) {
        int startIndex = verbIndex + 1;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = startIndex; i < tokensList.size(); i++) {
            if (i == tokensList.size() - 1 && (Tags.PREPOSITION.equals(tagsList.get(i)) || Tags.CONJUNCTION.equals(tagsList.get(i)))) {
                break;
            }
            stringBuilder.append(tokensList.get(i));
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    private int getExtractionStartIndex(SemanticPreprocessingData semanticPreprocessingData) {
        if (semanticPreprocessingData.getHaveBeenSequenceEndIndex() > -1) {
            return semanticPreprocessingData.getHaveBeenSequenceEndIndex() - 1;
        } else if (semanticPreprocessingData.getHaveVerbEdSequenceEndIndex() > -1) {
            return semanticPreprocessingData.getHaveVerbEdSequenceEndIndex() - 1;
        } else if (semanticPreprocessingData.getDoVerbSequenceEndIndex() > -1) {
            return semanticPreprocessingData.getDoVerbSequenceEndIndex() - 1;
        } else {
            return semanticPreprocessingData.getVerbIndex();
        }
    }

}
