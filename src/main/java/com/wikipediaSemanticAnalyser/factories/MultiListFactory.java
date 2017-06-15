package com.wikipediaSemanticAnalyser.factories;

import java.util.List;

/**
 * Created by oled on 6/15/2017.
 */
public interface MultiListFactory {

    List<List<String>> create(List<String> items);

}
