package com.wikipediaSemanticAnalyser.database;

import com.wikipediaSemanticAnalyser.data.SemanticExtractionData;

/**
 * Created by Oliver on 6/10/2017.
 */
public interface DatabaseInserter {

    void insert(SemanticExtractionData semanticExtractionData);

}
