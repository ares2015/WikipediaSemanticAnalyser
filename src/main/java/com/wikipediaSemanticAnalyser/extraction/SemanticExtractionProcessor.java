package com.wikipediaSemanticAnalyser.extraction;

import com.wikipediaSemanticAnalyser.data.SemanticExtractionData;

import java.util.List;

/**
 * Created by oled on 6/15/2017.
 */
public interface SemanticExtractionProcessor {

    List<SemanticExtractionData> process(List<String> sentencesList) throws InterruptedException;

}