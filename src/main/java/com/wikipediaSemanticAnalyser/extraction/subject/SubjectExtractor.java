package com.wikipediaSemanticAnalyser.extraction.subject;


import com.wikipediaSemanticAnalyser.data.semantics.SemanticExtractionData;
import com.wikipediaSemanticAnalyser.data.semantics.SemanticPreprocessingData;

/**
 * Created by Oliver on 2/16/2017.
 */
public interface SubjectExtractor {

    void extract(SemanticExtractionData semanticExtractionData, SemanticPreprocessingData semanticPreprocessingData);

}
