package com.wikipediaSemanticAnalyser.main;

import com.wikipediaSemanticAnalyser.database.DatabaseInserter;
import com.wikipediaSemanticAnalyser.preprocessing.SentencesPreprocessor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
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

/**
 * Created by oled on 6/12/2017.
 */
@RestController
@EnableAutoConfiguration
public class WikipediaSemanticAnalyser {

    private DatabaseInserter databaseInserter;

    private SentencesPreprocessor sentencesPreprocessor;

    public WikipediaSemanticAnalyser() {
        final ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        this.databaseInserter = (DatabaseInserter) context.getBean("databaseInserter");
        this.sentencesPreprocessor = (SentencesPreprocessor) context.getBean("sentencePreprocessor");
    }

    @RequestMapping(path = "/analyseObject", method = RequestMethod.GET, produces = {MediaType.TEXT_PLAIN_VALUE})
    String process(@RequestParam(value = "object") String object) throws IOException {
        String url = "https://en.wikipedia.org/wiki/" + object;
        Document doc = Jsoup.connect(url).get();
        Elements ps = doc.select("p");
        Elements linksOnPage = doc.select("a[href]");
        String[] sentences = ps.text().split("\\.");
        StringBuilder stringBuilder = new StringBuilder();
        for (String sentence : sentences) {
            String preprocessedSentence = sentencesPreprocessor.preprocess(sentence);
            System.out.println(preprocessedSentence);
            stringBuilder.append(preprocessedSentence);
            stringBuilder.append(System.lineSeparator());
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WikipediaSemanticAnalyser.class, args);
    }
}
