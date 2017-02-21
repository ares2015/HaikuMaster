package com.haikuMaster.database;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Oliver on 2/21/2017.
 */
public class TrainingDataDatabaseAccessorImpl implements TrainingDataDatabaseAccessor {

    private List<String> word2vecDatabaseColums = new ArrayList<>();

    private JdbcTemplate jdbcTemplate;

    public TrainingDataDatabaseAccessorImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        initWord2vecDatabaseColumnsList();
    }

    private void initWord2vecDatabaseColumnsList() {
        for (int i = 1; i <= 309; i++) {
            this.word2vecDatabaseColums.add("neighbour" + i);
        }
    }

    @Override
    public List<String> getWord2VecDataForToken(String token) {
        List<String> word2vecList = new ArrayList<>();
        final String sql = "select * from jos_haiku_master_word2vec_model where token = ?";
        final List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, new Object[]{token});
        for (final Map row : rows) {
            for (String columnName : this.word2vecDatabaseColums) {
                String vectorWord = (String) row.get(columnName);
                word2vecList.add(vectorWord);
            }
        }
        return word2vecList;
    }


}
