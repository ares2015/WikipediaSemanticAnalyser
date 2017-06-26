package com.wikipediaSemanticAnalyser.extraction.predicate.preposition;


import com.wikipediaSemanticAnalyser.data.semantics.SemanticExtractionData;
import com.wikipediaSemanticAnalyser.data.semantics.SemanticPreprocessingData;

/**
 * Created by Oliver on 6/1/2017.
 */
public interface PrepositionPredicateExtractor {

    void extract(SemanticExtractionData semanticExtractionData, SemanticPreprocessingData semanticPreprocessingData);

}
