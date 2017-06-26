package com.wikipediaSemanticAnalyser.crawling;

import com.wikipediaSemanticAnalyser.data.crawling.SentenceObjectData;

import java.io.IOException;
import java.util.List;

/**
 * Created by oled on 6/14/2017.
 */
public interface Crawler {

    List<SentenceObjectData> crawl(String object, List<SentenceObjectData> sentencesList, int depth) throws IOException, InterruptedException;

}
