package com.wikipediaSemanticAnalyser.extraction;


import com.wikipediaSemanticAnalyser.data.SemanticExtractionData;
import com.wikipediaSemanticAnalyser.data.SemanticPreprocessingData;

/**
 * Created by Oliver on 2/17/2017.
 */
public interface SemanticRelationsExtractor {

    SemanticExtractionData extract(SemanticPreprocessingData semanticPreprocessingData);

}
