package com.wikipediaSemanticAnalyser.main;

import com.wikipediaSemanticAnalyser.crawling.Crawler;
import com.wikipediaSemanticAnalyser.database.DatabaseInserter;
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

/**
 * Created by oled on 6/12/2017.
 */
@RestController
@EnableAutoConfiguration
public class WikipediaSemanticAnalyser {

    private DatabaseInserter databaseInserter;

    private Crawler crawler;

    public WikipediaSemanticAnalyser() {
        final ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        this.databaseInserter = (DatabaseInserter) context.getBean("databaseInserter");
        this.crawler = (Crawler) context.getBean("crawler");

    }

    @RequestMapping(path = "/analyseObject", method = RequestMethod.GET, produces = {MediaType.TEXT_PLAIN_VALUE})
    String process(@RequestParam(value = "object") String object) throws IOException, InterruptedException {
        crawler.crawl(object, new ArrayList<String>(), 1);
        return "";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WikipediaSemanticAnalyser.class, args);
    }
}
