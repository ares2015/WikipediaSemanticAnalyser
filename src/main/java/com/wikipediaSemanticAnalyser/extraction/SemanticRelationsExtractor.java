package com.wikipediaSemanticAnalyser.extraction;


import com.wikipediaSemanticAnalyser.data.semantics.SemanticExtractionData;
import com.wikipediaSemanticAnalyser.data.semantics.SemanticPreprocessingData;

/**
 * Created by Oliver on 2/17/2017.
 */
public interface SemanticRelationsExtractor {

    SemanticExtractionData extract(SemanticPreprocessingData semanticPreprocessingData);

}
