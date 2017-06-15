package com.wikipediaSemanticAnalyser.extraction.predicate.noun;


import com.wikipediaSemanticAnalyser.data.SemanticExtractionData;
import com.wikipediaSemanticAnalyser.data.SemanticPreprocessingData;

/**
 * Created by Oliver on 2/16/2017.
 */
public interface NounPredicateExtractor {

    void extract(SemanticExtractionData semanticExtractionData, SemanticPreprocessingData semanticPreprocessingData);

}
