package com.wikipediaSemanticAnalyser.factories;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Oliver on 5/17/2017.
 */
public class MultiListFactoryTest {

    private MultiListFactory multiListFactory = new MultiListFactoryImpl();

    @Test
    public void testCreateTagSubPaths() {
        List<String> tags = new ArrayList<>();
        tags.add("N");
        tags.add("N,");
        tags.add("AJ");
        tags.add("V,");
        tags.add("AJ");
        tags.add("N");

        List<List<String>> tagSubPaths = multiListFactory.create(tags);
        assertEquals(3, tagSubPaths.size());
    }

    @Test
    public void testCreateSubsentences() {
        List<String> tokens = new ArrayList<>();
        tokens.add("john,");
        tokens.add("boris,");
        tokens.add("mike");
        tokens.add("and");
        tokens.add("james");
        tokens.add("smoke");
        tokens.add("weed,");
        tokens.add("hash");
        tokens.add("and");
        tokens.add("cigarettes");

        List<List<String>> subSentences = multiListFactory.create(tokens);
        assertEquals(4, subSentences.size());
    }
}