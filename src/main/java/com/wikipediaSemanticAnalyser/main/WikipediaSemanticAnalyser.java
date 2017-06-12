package com.wikipediaSemanticAnalyser.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by oled on 6/12/2017.
 */
@RestController
@EnableAutoConfiguration
public class WikipediaSemanticAnalyser {

    @RequestMapping(path = "/analyseObject", method = RequestMethod.GET, produces = {MediaType.TEXT_PLAIN_VALUE})
    String process(@RequestParam(value = "object") String object) {
        return "hello";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WikipediaSemanticAnalyser.class, args);
    }
}
