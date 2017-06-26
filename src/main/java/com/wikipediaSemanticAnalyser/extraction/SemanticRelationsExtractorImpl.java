package com.wikipediaSemanticAnalyser.extraction;


import com.wikipediaSemanticAnalyser.data.semantics.SemanticExtractionData;
import com.wikipediaSemanticAnalyser.data.semantics.SemanticPreprocessingData;
import com.wikipediaSemanticAnalyser.extraction.predicate.noun.NounPredicateExtractor;
import com.wikipediaSemanticAnalyser.extraction.predicate.preposition.PrepositionPredicateExtractor;
import com.wikipediaSemanticAnalyser.extraction.predicate.verb.VerbPredicateExtractor;
import com.wikipediaSemanticAnalyser.extraction.subject.SubjectExtractor;

/**
 * Created by Oliver on 2/17/2017.
 */
public class SemanticRelationsExtractorImpl implements SemanticRelationsExtractor {

    private SubjectExtractor subjectExtractor;

    private VerbPredicateExtractor verbPredicateExtractor;

    private NounPredicateExtractor nounPredicateExtractor;

    private PrepositionPredicateExtractor prepositionPredicateExtractor;

    public SemanticRelationsExtractorImpl(SubjectExtractor subjectExtractor, VerbPredicateExtractor verbPredicateExtractor,
                                          NounPredicateExtractor nounPredicateExtractor,
                                          PrepositionPredicateExtractor prepositionPredicateExtractor) {
        this.subjectExtractor = subjectExtractor;
        this.verbPredicateExtractor = verbPredicateExtractor;
        this.nounPredicateExtractor = nounPredicateExtractor;
        this.prepositionPredicateExtractor = prepositionPredicateExtractor;
    }

    @Override
    public SemanticExtractionData extract(SemanticPreprocessingData semanticPreprocessingData) {
        SemanticExtractionData semanticExtractionData = new SemanticExtractionData();
        subjectExtractor.extract(semanticExtractionData, semanticPreprocessingData);
        verbPredicateExtractor.extract(semanticExtractionData, semanticPreprocessingData);
        nounPredicateExtractor.extract(semanticExtractionData, semanticPreprocessingData);
        prepositionPredicateExtractor.extract(semanticExtractionData, semanticPreprocessingData);
        return semanticExtractionData;
    }

}
