package com.wikipediaSemanticAnalyser.crawling;

import java.io.IOException;
import java.util.List;

/**
 * Created by oled on 6/14/2017.
 */
public interface Crawler {

    List<String> crawl(String object, List<String> sentencesList, int depth) throws IOException, InterruptedException;

}
