package com.wikipediaSemanticAnalyser.extraction.predicate.verb;


import com.wikipediaSemanticAnalyser.data.semantics.SemanticExtractionData;
import com.wikipediaSemanticAnalyser.data.semantics.SemanticPreprocessingData;

/**
 * Created by Oliver on 2/17/2017.
 */
public interface VerbPredicateExtractor {

    void extract(SemanticExtractionData semanticExtractionData, SemanticPreprocessingData semanticPreprocessingData);

}
