package com.wikipediaSemanticAnalyser.extraction;


import com.wikipediaSemanticAnalyser.data.SemanticExtractionData;
import com.wikipediaSemanticAnalyser.data.SemanticPreprocessingData;
import com.wikipediaSemanticAnalyser.extraction.predicate.noun.NounPredicateExtractor;
import com.wikipediaSemanticAnalyser.extraction.predicate.noun.NounPredicateExtractorImpl;
import com.wikipediaSemanticAnalyser.extraction.predicate.preposition.PrepositionPredicateExtractor;
import com.wikipediaSemanticAnalyser.extraction.predicate.preposition.PrepositionPredicateExtractorImpl;
import com.wikipediaSemanticAnalyser.extraction.predicate.verb.VerbPredicateExtractor;
import com.wikipediaSemanticAnalyser.extraction.predicate.verb.VerbPredicateExtractorImpl;
import com.wikipediaSemanticAnalyser.extraction.predicate.verb.sequence.VerbPredicateSequenceExtractor;
import com.wikipediaSemanticAnalyser.extraction.predicate.verb.sequence.VerbPredicateSequenceExtractorImpl;
import com.wikipediaSemanticAnalyser.extraction.subject.SubjectExtractor;
import com.wikipediaSemanticAnalyser.extraction.subject.SubjectExtractorImpl;
import com.wikipediaSemanticAnalyser.preprocessing.SemanticPreprocessor;
import com.wikipediaSemanticAnalyser.preprocessing.SemanticPreprocessorImpl;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Oliver on 5/18/2017.
 */
public class SemanticExtractionIntegrationTest {

    private SubjectExtractor subjectExtractor = new SubjectExtractorImpl();

    private VerbPredicateSequenceExtractor verbPredicateSequenceExtractor = new VerbPredicateSequenceExtractorImpl();

    private VerbPredicateExtractor verbPredicateExtractor = new VerbPredicateExtractorImpl(verbPredicateSequenceExtractor);

    private NounPredicateExtractor nounPredicateExtractor = new NounPredicateExtractorImpl();

    private PrepositionPredicateExtractor prepositionPredicateExtractor = new PrepositionPredicateExtractorImpl();

    private SemanticRelationsExtractor semanticExtractor = new SemanticRelationsExtractorImpl(subjectExtractor, verbPredicateExtractor,
            nounPredicateExtractor, prepositionPredicateExtractor);

    private SemanticPreprocessor semanticPreprocessor = new SemanticPreprocessorImpl();

    @Test
    public void test() {
        String sentence = "Mr Lavrov was quoted as saying by the Reuters news agency";
        List<String> tokensList = Arrays.asList(sentence.split("\\ "));
        String tags = "N N IA Ved PR Ving PR DET N N N";
        List<String> tagsList = Arrays.asList(tags.split("\\ "));
        SemanticPreprocessingData semanticPreprocessingData = semanticPreprocessor.preprocess(tokensList, tagsList);
        SemanticExtractionData semanticExtractionData = semanticExtractor.extract(semanticPreprocessingData);
        assertEquals("Lavrov", semanticExtractionData.getAtomicSubject());
        assertEquals("Mr Lavrov ", semanticExtractionData.getExtendedSubject());
        assertEquals("was", semanticExtractionData.getAtomicVerbPredicate());
        assertEquals("quoted as saying by Reuters news agency ", semanticExtractionData.getExtendedNounPredicate());
        assertEquals("as saying by Reuters news agency ", semanticExtractionData.getPrepositionPredicate());
    }

    @Test
    public void test2() {
        String sentence = "The play was first produced in 1934 in Los Angeles under the title Woman on Trial";
        List<String> tokensList = Arrays.asList(sentence.split("\\ "));
        String tags = "DET N IA AV Ved PR NR PR N N PR DET N N PR N";
        List<String> tagsList = Arrays.asList(tags.split("\\ "));
        SemanticPreprocessingData semanticPreprocessingData = semanticPreprocessor.preprocess(tokensList, tagsList);
        SemanticExtractionData semanticExtractionData = semanticExtractor.extract(semanticPreprocessingData);
        assertEquals("play", semanticExtractionData.getAtomicSubject());
        assertEquals("", semanticExtractionData.getExtendedSubject());
        assertEquals("was", semanticExtractionData.getAtomicVerbPredicate());
        assertEquals("produced", semanticExtractionData.getAtomicNounPredicate());
        assertEquals("first produced in 1934 in Los Angeles under title Woman on Trial ", semanticExtractionData.getExtendedNounPredicate());
        assertEquals("in 1934 in Los Angeles under title Woman on Trial ", semanticExtractionData.getPrepositionPredicate());
    }

    @Test
    public void test3() {
        String sentence = "A drone looks like a conflation of a giant insect and a light aircraft";
        List<String> tokensList = Arrays.asList(sentence.split("\\ "));
        String tags = "DET N V PR DET N PR DET AJ N AO DET AJ N";
        List<String> tagsList = Arrays.asList(tags.split("\\ "));
        SemanticPreprocessingData semanticPreprocessingData = semanticPreprocessor.preprocess(tokensList, tagsList);
        SemanticExtractionData semanticExtractionData = semanticExtractor.extract(semanticPreprocessingData);
        assertEquals("drone", semanticExtractionData.getAtomicSubject());
        assertEquals("", semanticExtractionData.getExtendedSubject());
        assertEquals("looks", semanticExtractionData.getAtomicVerbPredicate());
        assertEquals("", semanticExtractionData.getAtomicNounPredicate());
        assertEquals("like conflation of giant insect ", semanticExtractionData.getExtendedNounPredicate());
    }

    @Test
    public void test4() {
        String sentence = "Its head was so bright that as it flew through the air it made the lightning";
        List<String> tokensList = Arrays.asList(sentence.split("\\ "));
        String tags = "PRPS N IA CJ AJ WP PR PRP Ved CJ DET N PRP Ved DET N";
        List<String> tagsList = Arrays.asList(tags.split("\\ "));
        SemanticPreprocessingData semanticPreprocessingData = semanticPreprocessor.preprocess(tokensList, tagsList);
        SemanticExtractionData semanticExtractionData = semanticExtractor.extract(semanticPreprocessingData);
        assertEquals("head", semanticExtractionData.getAtomicSubject());
        assertEquals("", semanticExtractionData.getExtendedSubject());
        assertEquals("was", semanticExtractionData.getAtomicVerbPredicate());
        assertEquals("", semanticExtractionData.getAtomicNounPredicate());
        assertEquals("so bright ", semanticExtractionData.getExtendedNounPredicate());
    }

    @Test
    public void test5() {
        String sentence = "The sorrow of all living things at his death meant the gloom of northern countries when winter comes";
        List<String> tokensList = Arrays.asList(sentence.split("\\ "));
        String tags = "DET N PR Q Ving N PR PRPS N V DET N PR AJ N WAV N V";
        List<String> tagsList = Arrays.asList(tags.split("\\ "));
        SemanticPreprocessingData semanticPreprocessingData = semanticPreprocessor.preprocess(tokensList, tagsList);
        SemanticExtractionData semanticExtractionData = semanticExtractor.extract(semanticPreprocessingData);
        assertEquals("", semanticExtractionData.getAtomicSubject());
        assertEquals("sorrow of all living things at death ", semanticExtractionData.getExtendedSubject());
        assertEquals("meant", semanticExtractionData.getAtomicVerbPredicate());
        assertEquals("gloom", semanticExtractionData.getAtomicNounPredicate());
        assertEquals("gloom of northern countries ", semanticExtractionData.getExtendedNounPredicate());
    }

    @Test
    public void test6() {
        String sentence = "The earth will be shaken as when there is a great earthquake the waves of the sea will roar and the highest mountains will totter and fall";
        List<String> tokensList = Arrays.asList(sentence.split("\\ "));
        String tags = "DET N MV IA Ved PR WAV T IA DET AJ N DET N PR DET N MV N AO DET AJ N MV V AO V";
        List<String> tagsList = Arrays.asList(tags.split("\\ "));
        SemanticPreprocessingData semanticPreprocessingData = semanticPreprocessor.preprocess(tokensList, tagsList);
        SemanticExtractionData semanticExtractionData = semanticExtractor.extract(semanticPreprocessingData);
        assertEquals("earth", semanticExtractionData.getAtomicSubject());
        assertEquals("", semanticExtractionData.getExtendedSubject());
        assertEquals("will be shaken ", semanticExtractionData.getAtomicVerbPredicate());
        assertEquals("will be shaken ", semanticExtractionData.getExtendedVerbPredicate());
        assertEquals("shaken", semanticExtractionData.getAtomicNounPredicate());
        assertEquals("shaken ", semanticExtractionData.getExtendedNounPredicate());
    }

    @Test
    public void test7() {
        String sentence = "The earth has been shaken as when there was a great earthquake the waves of the sea will roar and the highest mountains will totter and fall";
        List<String> tokensList = Arrays.asList(sentence.split("\\ "));
        String tags = "DET N H IA Ved PR WAV T IA DET AJ N DET N PR DET N MV N AO DET AJ N MV V AO V";
        List<String> tagsList = Arrays.asList(tags.split("\\ "));
        SemanticPreprocessingData semanticPreprocessingData = semanticPreprocessor.preprocess(tokensList, tagsList);
        SemanticExtractionData semanticExtractionData = semanticExtractor.extract(semanticPreprocessingData);
        assertEquals("earth", semanticExtractionData.getAtomicSubject());
        assertEquals("", semanticExtractionData.getExtendedSubject());
        assertEquals("has been ", semanticExtractionData.getAtomicVerbPredicate());
        assertEquals("has been ", semanticExtractionData.getExtendedVerbPredicate());
        assertEquals("shaken", semanticExtractionData.getAtomicNounPredicate());
        assertEquals("", semanticExtractionData.getExtendedNounPredicate());
    }

    @Test
    public void test8() {
        String sentence = "Mary could have sung nice songs";
        List<String> tokensList = Arrays.asList(sentence.split("\\ "));
        String tags = "N MV H Ved AJ N";
        List<String> tagsList = Arrays.asList(tags.split("\\ "));
        SemanticPreprocessingData semanticPreprocessingData = semanticPreprocessor.preprocess(tokensList, tagsList);
        SemanticExtractionData semanticExtractionData = semanticExtractor.extract(semanticPreprocessingData);
        assertEquals("Mary", semanticExtractionData.getAtomicSubject());
        assertEquals("", semanticExtractionData.getExtendedSubject());
        assertEquals("could have sung ", semanticExtractionData.getAtomicVerbPredicate());
        assertEquals("could have sung ", semanticExtractionData.getExtendedVerbPredicate());
        assertEquals("songs", semanticExtractionData.getAtomicNounPredicate());
        assertEquals("nice songs ", semanticExtractionData.getExtendedNounPredicate());
    }

    @Test
    public void test9() {
        String sentence = "Mary could not have sung nice songs";
        List<String> tokensList = Arrays.asList(sentence.split("\\ "));
        String tags = "N MV NOT H Ved AJ N";
        List<String> tagsList = Arrays.asList(tags.split("\\ "));
        SemanticPreprocessingData semanticPreprocessingData = semanticPreprocessor.preprocess(tokensList, tagsList);
        SemanticExtractionData semanticExtractionData = semanticExtractor.extract(semanticPreprocessingData);
        assertEquals("Mary", semanticExtractionData.getAtomicSubject());
        assertEquals("", semanticExtractionData.getExtendedSubject());
        assertEquals("could not have sung ", semanticExtractionData.getAtomicVerbPredicate());
        assertEquals("could not have sung ", semanticExtractionData.getExtendedVerbPredicate());
        assertEquals("songs", semanticExtractionData.getAtomicNounPredicate());
        assertEquals("nice songs ", semanticExtractionData.getExtendedNounPredicate());
        assertTrue(semanticExtractionData.isNegativeVerbPredicate());
    }

    @Test
    public void test10() {
        String sentence = "Mary couldn't have sung nice songs";
        List<String> tokensList = Arrays.asList(sentence.split("\\ "));
        String tags = "N MV_NOT H Ved AJ N";
        List<String> tagsList = Arrays.asList(tags.split("\\ "));
        SemanticPreprocessingData semanticPreprocessingData = semanticPreprocessor.preprocess(tokensList, tagsList);
        SemanticExtractionData semanticExtractionData = semanticExtractor.extract(semanticPreprocessingData);
        assertEquals("Mary", semanticExtractionData.getAtomicSubject());
//        assertEquals("", semanticExtractionData.getExtendedSubject());
        assertEquals("couldn't have sung ", semanticExtractionData.getAtomicVerbPredicate());
        assertEquals("couldn't have sung ", semanticExtractionData.getExtendedVerbPredicate());
        assertEquals("songs", semanticExtractionData.getAtomicNounPredicate());
        assertEquals("nice songs ", semanticExtractionData.getExtendedNounPredicate());
    }

    @Test
    public void test11() {
        String sentence = "Mary didn't go to school";
        List<String> tokensList = Arrays.asList(sentence.split("\\ "));
        String tags = "N DO_NOT V TO N";
        List<String> tagsList = Arrays.asList(tags.split("\\ "));
        SemanticPreprocessingData semanticPreprocessingData = semanticPreprocessor.preprocess(tokensList, tagsList);
        SemanticExtractionData semanticExtractionData = semanticExtractor.extract(semanticPreprocessingData);
        assertEquals("Mary", semanticExtractionData.getAtomicSubject());
        assertEquals("Mary ", semanticExtractionData.getExtendedSubject());
        assertEquals("didn't go ", semanticExtractionData.getAtomicVerbPredicate());
        assertEquals("didn't go ", semanticExtractionData.getExtendedVerbPredicate());
        assertEquals("", semanticExtractionData.getAtomicNounPredicate());
        assertEquals("to school ", semanticExtractionData.getExtendedNounPredicate());
    }

    @Test
    public void test12() {
        String sentence = "Mary didn't buy milk";
        List<String> tokensList = Arrays.asList(sentence.split("\\ "));
        String tags = "N DO_NOT V N";
        List<String> tagsList = Arrays.asList(tags.split("\\ "));
        SemanticPreprocessingData semanticPreprocessingData = semanticPreprocessor.preprocess(tokensList, tagsList);
        SemanticExtractionData semanticExtractionData = semanticExtractor.extract(semanticPreprocessingData);
        assertEquals("Mary", semanticExtractionData.getAtomicSubject());
        assertEquals("Mary ", semanticExtractionData.getExtendedSubject());
        assertEquals("didn't buy ", semanticExtractionData.getAtomicVerbPredicate());
        assertEquals("didn't buy ", semanticExtractionData.getExtendedVerbPredicate());
        assertEquals("milk", semanticExtractionData.getAtomicNounPredicate());
        assertEquals("milk ", semanticExtractionData.getExtendedNounPredicate());
    }

    @Test
    public void test13() {
        String sentence = "Ronaldo runs furiously towards goalie";
        List<String> tokensList = Arrays.asList(sentence.split("\\ "));
        String tags = "N V AV PR N";
        List<String> tagsList = Arrays.asList(tags.split("\\ "));
        SemanticPreprocessingData semanticPreprocessingData = semanticPreprocessor.preprocess(tokensList, tagsList);
        SemanticExtractionData semanticExtractionData = semanticExtractor.extract(semanticPreprocessingData);
        assertEquals("Ronaldo", semanticExtractionData.getAtomicSubject());
        assertEquals("", semanticExtractionData.getExtendedSubject());
        assertEquals("runs", semanticExtractionData.getAtomicVerbPredicate());
        assertEquals("runs furiously ", semanticExtractionData.getExtendedVerbPredicate());
        assertEquals("", semanticExtractionData.getAtomicNounPredicate());
        assertEquals("furiously towards goalie ", semanticExtractionData.getExtendedNounPredicate());
    }

    @Test
    public void test14() {
        String sentence = "Ronaldo couldn't have run furiously towards goalie";
        List<String> tokensList = Arrays.asList(sentence.split("\\ "));
        String tags = "N MV_NOT H V AV PR N";
        List<String> tagsList = Arrays.asList(tags.split("\\ "));
        SemanticPreprocessingData semanticPreprocessingData = semanticPreprocessor.preprocess(tokensList, tagsList);
        SemanticExtractionData semanticExtractionData = semanticExtractor.extract(semanticPreprocessingData);
        assertEquals("Ronaldo", semanticExtractionData.getAtomicSubject());
        assertEquals("", semanticExtractionData.getExtendedSubject());
        assertEquals("couldn't have run ", semanticExtractionData.getAtomicVerbPredicate());
        assertEquals("couldn't have run furiously ", semanticExtractionData.getExtendedVerbPredicate());
        assertEquals("", semanticExtractionData.getAtomicNounPredicate());
        assertEquals("furiously towards goalie ", semanticExtractionData.getExtendedNounPredicate());
    }

    @Test
    public void test15() {
        String sentence = "On the 11th ten more heavy shells fell about Belfort";
        List<String> tokensList = Arrays.asList(sentence.split("\\ "));
        String tags = "PR DET NR NR Q AJ N Ved PR N";
        List<String> tagsList = Arrays.asList(tags.split("\\ "));
        SemanticPreprocessingData semanticPreprocessingData = semanticPreprocessor.preprocess(tokensList, tagsList);
        SemanticExtractionData semanticExtractionData = semanticExtractor.extract(semanticPreprocessingData);
        assertEquals("", semanticExtractionData.getAtomicSubject());
        assertEquals("On 11th ten more heavy shells ", semanticExtractionData.getExtendedSubject());
        assertEquals("fell", semanticExtractionData.getAtomicVerbPredicate());
        assertEquals("fell ", semanticExtractionData.getExtendedVerbPredicate());
        assertEquals("", semanticExtractionData.getAtomicNounPredicate());
        assertEquals("about Belfort ", semanticExtractionData.getExtendedNounPredicate());
    }

    @Test
    public void test16() {
        String sentence = "Field Marshal Sir John French was relieved at his own instance and appointed to the command of the home forces";
        List<String> tokensList = Arrays.asList(sentence.split("\\ "));
        String tags = "N N N N N IA Ved PR PRPS AJ N AO Ved TO DET N PR DET N N";
        List<String> tagsList = Arrays.asList(tags.split("\\ "));
        SemanticPreprocessingData semanticPreprocessingData = semanticPreprocessor.preprocess(tokensList, tagsList);
        SemanticExtractionData semanticExtractionData = semanticExtractor.extract(semanticPreprocessingData);
        assertEquals("French", semanticExtractionData.getAtomicSubject());
        assertEquals("Field Marshal Sir John French ", semanticExtractionData.getExtendedSubject());
        assertEquals("was", semanticExtractionData.getAtomicVerbPredicate());
        assertEquals("was ", semanticExtractionData.getExtendedVerbPredicate());
        assertEquals("relieved", semanticExtractionData.getAtomicNounPredicate());
        assertEquals("relieved at own instance ", semanticExtractionData.getExtendedNounPredicate());
    }

    @Test
    public void test17() {
        String sentence = "the guns vomited destruction and murder against the Germans";
        List<String> tokensList = Arrays.asList(sentence.split("\\ "));
        String tags = "DET N Ved N AO N PR DET N";
        List<String> tagsList = Arrays.asList(tags.split("\\ "));
        SemanticPreprocessingData semanticPreprocessingData = semanticPreprocessor.preprocess(tokensList, tagsList);
        SemanticExtractionData semanticExtractionData = semanticExtractor.extract(semanticPreprocessingData);
        assertEquals("guns", semanticExtractionData.getAtomicSubject());
        assertEquals("", semanticExtractionData.getExtendedSubject());
        assertEquals("vomited", semanticExtractionData.getAtomicVerbPredicate());
        assertEquals("vomited ", semanticExtractionData.getExtendedVerbPredicate());
        assertEquals("destruction", semanticExtractionData.getAtomicNounPredicate());
        assertEquals("destruction ", semanticExtractionData.getExtendedNounPredicate());
    }

    @Test
    public void test18() {
        String sentence = "During the foundation of the German Empire in 1871 a large military facility called Albertstadt was built";
        List<String> tokensList = Arrays.asList(sentence.split("\\ "));
        String tags = "PR DET N PR DET N N PR NR DET AJ N N Ved N IA Ved";
        List<String> tagsList = Arrays.asList(tags.split("\\ "));
        SemanticPreprocessingData semanticPreprocessingData = semanticPreprocessor.preprocess(tokensList, tagsList);
        SemanticExtractionData semanticExtractionData = semanticExtractor.extract(semanticPreprocessingData);
        assertEquals("", semanticExtractionData.getAtomicSubject());
        assertEquals("During foundation of German Empire in 1871 large military facility called Albertstadt ", semanticExtractionData.getExtendedSubject());
        assertEquals("was", semanticExtractionData.getAtomicVerbPredicate());
        assertEquals("was ", semanticExtractionData.getExtendedVerbPredicate());
        assertEquals("built", semanticExtractionData.getAtomicNounPredicate());
        assertEquals("built ", semanticExtractionData.getExtendedNounPredicate());
    }
}