package com.wikipediaSemanticAnalyser.factories;


import com.wikipediaSemanticAnalyser.data.semantics.InputData;
import com.wikipediaSemanticAnalyser.tokenizing.Tokenizer;
import com.wikipediaSemanticAnalyser.tokenizing.TokenizerImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by Oliver on 5/17/2017.
 */
public class InputDataFactoryTest {

    private Tokenizer tokenizer = new TokenizerImpl();

    private MultiListFactory multiListFactory = new MultiListFactoryImpl();

    private InputDataFactory inputDataFactory = new InputDataFactoryImpl(tokenizer, multiListFactory);

    @Test
    public void testCreate() {
        String sentence = "boys drink beer in pub";
        List<List<String>> tagSequencesMultiList = new ArrayList<>();
        List<String> tagsSequenceList = new ArrayList<>();
        tagSequencesMultiList.add(tagsSequenceList);
        tagsSequenceList.add(new String("N V N PR N"));

        InputData inputData = inputDataFactory.create(sentence, tagSequencesMultiList);

        assertFalse(inputData.containsSubSentences());
        assertEquals(5, inputData.getTokensList().size());
        assertEquals(5, inputData.getTagsList().size());

        assertEquals("boys", inputData.getTokensList().get(0));
        assertEquals("drink", inputData.getTokensList().get(1));
        assertEquals("beer", inputData.getTokensList().get(2));
        assertEquals("in", inputData.getTokensList().get(3));
        assertEquals("pub", inputData.getTokensList().get(4));

        assertEquals("N", inputData.getTagsList().get(0));
        assertEquals("V", inputData.getTagsList().get(1));
        assertEquals("N", inputData.getTagsList().get(2));
        assertEquals("PR", inputData.getTagsList().get(3));
        assertEquals("N", inputData.getTagsList().get(4));

    }

    @Test
    public void testCreateWithSubSentences() {
        String inputDataString = "john, harry, mike and bob drink beer in pub#N, N, N AO N V N PR N";
        String sentence = "john, harry, mike and bob drink beer in pub";
        List<List<String>> tagSequencesMultiList = new ArrayList<>();

        List<String> tagsSequenceList1 = new ArrayList<>();
        tagsSequenceList1.add(new String("N"));

        List<String> tagsSequenceList2 = new ArrayList<>();
        tagsSequenceList2.add(new String("N"));

        List<String> tagsSequenceList3 = new ArrayList<>();
        tagsSequenceList3.add(new String("N AO N V N PR N"));

        tagSequencesMultiList.add(tagsSequenceList1);
        tagSequencesMultiList.add(tagsSequenceList2);
        tagSequencesMultiList.add(tagsSequenceList3);

        InputData inputData = inputDataFactory.create(sentence, tagSequencesMultiList);

        List<List<String>> tokensMultiList = inputData.getTokensMultiList();
        assertEquals(3, tokensMultiList.size());
        assertEquals("john", tokensMultiList.get(0).get(0));
        assertEquals("harry", tokensMultiList.get(1).get(0));
        assertEquals("mike", tokensMultiList.get(2).get(0));
        assertEquals("and", tokensMultiList.get(2).get(1));
        assertEquals("bob", tokensMultiList.get(2).get(2));
        assertEquals("drink", tokensMultiList.get(2).get(3));
        assertEquals("beer", tokensMultiList.get(2).get(4));
        assertEquals("in", tokensMultiList.get(2).get(5));
        assertEquals("pub", tokensMultiList.get(2).get(6));

        List<List<String>> tagsMultiList = inputData.getTagsMultiList();
        assertEquals(3, tagsMultiList.size());
        assertEquals("N", tagsMultiList.get(0).get(0));
        assertEquals("N", tagsMultiList.get(1).get(0));
        assertEquals("N", tagsMultiList.get(2).get(0));
        assertEquals("AO", tagsMultiList.get(2).get(1));
        assertEquals("N", tagsMultiList.get(2).get(2));
        assertEquals("V", tagsMultiList.get(2).get(3));
        assertEquals("N", tagsMultiList.get(2).get(4));
        assertEquals("PR", tagsMultiList.get(2).get(5));
        assertEquals("N", tagsMultiList.get(2).get(6));

    }
}