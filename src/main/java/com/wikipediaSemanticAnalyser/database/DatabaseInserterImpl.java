package com.wikipediaSemanticAnalyser.database;

import com.wikipediaSemanticAnalyser.data.SemanticExtractionData;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by oled on 6/12/2017.
 */
public class DatabaseInserterImpl implements DatabaseInserter {

    private JdbcTemplate jdbcTemplate;

    public DatabaseInserterImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insert(SemanticExtractionData semanticExtractionData) {

    }

}
