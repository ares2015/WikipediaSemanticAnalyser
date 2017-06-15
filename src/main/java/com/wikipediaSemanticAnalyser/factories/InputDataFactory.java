package com.wikipediaSemanticAnalyser.factories;

import com.wikipediaSemanticAnalyser.data.InputData;

import java.util.List;

/**
 * Created by oled on 6/15/2017.
 */
public interface InputDataFactory {

    InputData create(String sentence, List<List<String>> tagSequencesMultiList);

}