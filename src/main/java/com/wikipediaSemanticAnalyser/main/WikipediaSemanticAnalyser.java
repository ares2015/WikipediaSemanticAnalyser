package com.wikipediaSemanticAnalyser.main;

import com.wikipediaSemanticAnalyser.crawling.Crawler;
import com.wikipediaSemanticAnalyser.data.crawling.SentenceObjectData;
import com.wikipediaSemanticAnalyser.data.semantics.SemanticExtractionData;
import com.wikipediaSemanticAnalyser.database.DatabaseInserter;
import com.wikipediaSemanticAnalyser.extraction.SemanticExtractionProcessor;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oled on 6/12/2017.
 */
@RestController
@EnableAutoConfiguration
public class WikipediaSemanticAnalyser {

    private DatabaseInserter databaseInserter;

    private Crawler crawler;

    private SemanticExtractionProcessor semanticExtractionProcessor;

    private final static Logger LOGGER = Logger.getLogger(WikipediaSemanticAnalyser.class.getName());

    public WikipediaSemanticAnalyser() {
        final ApplicationContext context = new ClassPathXmlApplicationContext("spring_beans.xml");
        this.databaseInserter = (DatabaseInserter) context.getBean("databaseInserter");
        this.crawler = (Crawler) context.getBean("crawler");
        this.semanticExtractionProcessor = (SemanticExtractionProcessor) context.getBean("semanticExtractionProcessor");
    }

    @RequestMapping(path = "/analyseObject", method = RequestMethod.GET, produces = {MediaType.TEXT_PLAIN_VALUE})
    String process(@RequestParam(value = "object") String object) throws IOException, InterruptedException {
        List<SentenceObjectData> sentencesList = crawler.crawl(object, new ArrayList<SentenceObjectData>(), 1);
        List<SemanticExtractionData> semanticExtractionDataList = semanticExtractionProcessor.process(sentencesList);
        for (SemanticExtractionData semanticExtractionData : semanticExtractionDataList) {
            databaseInserter.insert(semanticExtractionData);
        }
        String result = sentencesList.size() + " sentences were processed and " + semanticExtractionDataList.size() + " were processed " +
                "semantically";
        LOGGER.info(result);
        return result;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WikipediaSemanticAnalyser.class, args);
    }
}
