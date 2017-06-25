package com.wikipediaSemanticAnalyser.crawling;

import com.wikipediaSemanticAnalyser.preprocessing.SentencesPreprocessor;
import com.wikipediaSemanticAnalyser.tokenizing.Tokenizer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by oled on 6/14/2017.
 */
public class CrawlerImpl implements Crawler {

    private SentencesPreprocessor sentencesPreprocessor;

    private Tokenizer tokenizer;

    private Set<String> urls = new HashSet<String>();

    public CrawlerImpl(SentencesPreprocessor sentencesPreprocessor, Tokenizer tokenizer) {
        this.sentencesPreprocessor = sentencesPreprocessor;
        this.tokenizer = tokenizer;
    }

    @Override
    public List<String> crawl(String object, List<String> sentencesList, int depth) throws IOException, InterruptedException {
        String url = "https://en.wikipedia.org/wiki/" + object;
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();

            Elements ps = doc.select("p");
            String[] sentences = ps.text().split("\\.");
            for (String sentence : sentences) {
                String preprocessedSentence = sentencesPreprocessor.preprocess(sentence);
                sentencesList.add(preprocessedSentence);
                System.out.println(preprocessedSentence);
            }
            Thread.sleep(3000);
            if (depth == 1) {
                Elements linksOnPage = doc.select("a[href]");
                populateURLsSet(linksOnPage);
                int urlCount = 0;
                for (String urlString : urls) {
                    Optional<String> newObject = tokenizer.extractObjectFromURL(urlString);
                    if (newObject.isPresent()) {
                        urlCount++;
                        crawl(newObject.get(), sentencesList, 2);
                    }
                    if (urlCount == 5) {
                        return sentencesList;
                    }
                }
            }
        } catch (Throwable e) {
            return sentencesList;
        }
        return sentencesList;
    }

    private void populateURLsSet(Elements linksOnPage) {
        for (int i = 0; i < linksOnPage.size(); i++) {
            String url = ((Elements) linksOnPage).get(i).getElementsByAttributeStarting("href").toString();
            if (url.contains("/wiki/") && !url.contains("class")) {
                urls.add(url);
            }
        }
    }
}