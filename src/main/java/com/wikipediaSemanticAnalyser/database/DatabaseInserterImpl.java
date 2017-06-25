package com.wikipediaSemanticAnalyser.database;

import com.wikipediaSemanticAnalyser.data.SemanticExtractionData;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by oled on 6/12/2017.
 */
public class DatabaseInserterImpl implements DatabaseInserter {

    private JdbcTemplate jdbcTemplate;

    public DatabaseInserterImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final static Logger LOGGER = Logger.getLogger(DatabaseInserterImpl.class.getName());

    @Override
    public void insert(SemanticExtractionData semanticExtractionData) {
        final String sql = "insert into jos_nlp_semantic_data (atomic_subject,extended_subject,atomic_verb_predicate,extended_verb_predicate," +
                "atomic_noun_predicate,extended_noun_predicate) values (?,?,?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{semanticExtractionData.getAtomicSubject(), semanticExtractionData.getExtendedSubject(),
                semanticExtractionData.getAtomicVerbPredicate(),
                semanticExtractionData.getExtendedVerbPredicate(), semanticExtractionData.getAtomicNounPredicate(),
                semanticExtractionData.getExtendedNounPredicate()});
        LOGGER.info("Inserting semantic extraction data: " + semanticExtractionData.getAtomicSubject() + " | " + semanticExtractionData.getExtendedSubject()
                + "|" + semanticExtractionData.getAtomicVerbPredicate() + "|" + semanticExtractionData.getExtendedVerbPredicate() + "|" +
                semanticExtractionData.getAtomicNounPredicate() + "|" + semanticExtractionData.getExtendedNounPredicate());
    }

}
