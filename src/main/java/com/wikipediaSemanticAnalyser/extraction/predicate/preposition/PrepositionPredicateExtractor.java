package com.wikipediaSemanticAnalyser.extraction.predicate.preposition;


import com.wikipediaSemanticAnalyser.data.SemanticExtractionData;
import com.wikipediaSemanticAnalyser.data.SemanticPreprocessingData;

/**
 * Created by Oliver on 6/1/2017.
 */
public interface PrepositionPredicateExtractor {

    void extract(SemanticExtractionData semanticExtractionData, SemanticPreprocessingData semanticPreprocessingData);

}
