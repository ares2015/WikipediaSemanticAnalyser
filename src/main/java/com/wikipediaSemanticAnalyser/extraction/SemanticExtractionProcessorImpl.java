package com.wikipediaSemanticAnalyser.extraction;

import com.postagger.main.PosTagger;
import com.postagger.main.PosTaggerImpl;
import com.wikipediaSemanticAnalyser.data.crawling.SentenceObjectData;
import com.wikipediaSemanticAnalyser.data.semantics.InputData;
import com.wikipediaSemanticAnalyser.data.semantics.SemanticExtractionData;
import com.wikipediaSemanticAnalyser.data.semantics.SemanticPreprocessingData;
import com.wikipediaSemanticAnalyser.factories.InputDataFactory;
import com.wikipediaSemanticAnalyser.preprocessing.CapitalizedTokensPreprocessor;
import com.wikipediaSemanticAnalyser.preprocessing.SemanticPreprocessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by oled on 6/15/2017.
 */
public class SemanticExtractionProcessorImpl implements SemanticExtractionProcessor {

    private InputDataFactory inputDataFactory;

    private CapitalizedTokensPreprocessor capitalizedTokensPreprocessor;

    private PosTagger posTagger;

    private SemanticPreprocessor semanticPreprocessor;

    private SemanticRelationsExtractor semanticRelationsExtractor;

    public SemanticExtractionProcessorImpl(InputDataFactory inputDataFactory,
                                           CapitalizedTokensPreprocessor capitalizedTokensPreprocessor,
                                           SemanticPreprocessor semanticPreprocessor,
                                           SemanticRelationsExtractor semanticRelationsExtractor) {
        this.inputDataFactory = inputDataFactory;
        this.capitalizedTokensPreprocessor = capitalizedTokensPreprocessor;
        this.posTagger = new PosTaggerImpl();
        this.semanticPreprocessor = semanticPreprocessor;
        this.semanticRelationsExtractor = semanticRelationsExtractor;
    }

    @Override
    public List<SemanticExtractionData> process(List<SentenceObjectData> sentencesList) throws InterruptedException {
        List<SemanticExtractionData> semanticExtractionDataList = new ArrayList<>();
        for (SentenceObjectData sentenceObjectData : sentencesList) {
            try {
                List<List<String>> tagSequencesMultiList = posTagger.tag(sentenceObjectData.getSentence());
                InputData inputData = inputDataFactory.create(sentenceObjectData.getSentence(), tagSequencesMultiList);
                capitalizedTokensPreprocessor.process(inputData);
                if (inputData.containsSubSentences()) {
                    for (int i = 0; i <= inputData.getTokensMultiList().size() - 1; i++) {
                        List<String> tokensList = inputData.getTokensMultiList().get(i);
                        List<String> tagsList = inputData.getTagsMultiList().get(i);
                        Optional<SemanticExtractionData> semanticExtractionDataOptional = processSentence(tokensList, tagsList);
                        if (semanticExtractionDataOptional.isPresent()) {
                            SemanticExtractionData semanticExtractionData = semanticExtractionDataOptional.get();
                            semanticExtractionData.setSentence(sentenceObjectData.getSentence());
                            semanticExtractionDataList.add(semanticExtractionData);
                        }
                    }
                } else {
                    List<String> tagsList = inputData.getTagsList();
                    List<String> tokensList = inputData.getTokensList();
                    Optional<SemanticExtractionData> semanticExtractionDataOptional = processSentence(tokensList, tagsList);
                    if (semanticExtractionDataOptional.isPresent()) {
                        SemanticExtractionData semanticExtractionData = semanticExtractionDataOptional.get();
                        semanticExtractionData.setSentence(sentenceObjectData.getSentence());
                        semanticExtractionData.setObject(sentenceObjectData.getObject());
                        semanticExtractionDataList.add(semanticExtractionData);
                    }
                }
            } catch (Exception e) {
                continue;
            }
        }
        return semanticExtractionDataList;
    }


    private Optional<SemanticExtractionData> processSentence(List<String> tokensList, List<String> tagsList) {
        SemanticPreprocessingData semanticPreprocessingData = semanticPreprocessor.preprocess(tokensList, tagsList);
        if (semanticPreprocessingData.canGoToExtraction()) {
            SemanticExtractionData semanticExtractionData = semanticRelationsExtractor.extract(semanticPreprocessingData);
            return Optional.of(semanticExtractionData);
        }
        return Optional.empty();
    }
}
