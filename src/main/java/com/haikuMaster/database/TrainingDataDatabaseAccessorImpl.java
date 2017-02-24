package com.haikuMaster.database;

import com.haikuMaster.data.Word2VecTokenTagData;
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
                System.out.println("Vector word: " + vectorWord);
                word2vecList.add(vectorWord);
            }
        }
        return word2vecList;
    }

    @Override
    public Word2VecTokenTagData getWord2VecTokenTagData(String token, List<String> word2vecList) {
        Word2VecTokenTagData word2VecTokenTagData = new Word2VecTokenTagData();
        word2VecTokenTagData.setToken(token);
        for (String word2vecToken : word2vecList) {
            String sql = "select * from jos_haiku_master_token_tag_data where token = ?";
            final List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, new Object[]{word2vecToken});
            for (final Map row : rows) {
                if ((Integer) row.get("is_noun") == 1) {
                    word2VecTokenTagData.getNouns().add(word2vecToken);
                    System.out.println(word2vecToken + "-> NOUN");
                }
                if ((Integer) row.get("is_adjective") == 1) {
                    word2VecTokenTagData.getAdjectives().add(word2vecToken);
                    System.out.println(word2vecToken + "-> ADJECTIVE");
                }
                if ((Integer) row.get("is_verb") == 1) {
                    word2VecTokenTagData.getVerbs().add(word2vecToken);
                    System.out.println(word2vecToken + "-> VERB");
                }
                if ((Integer) row.get("is_adverb") == 1) {
                    word2VecTokenTagData.getAdverbs().add(word2vecToken);
                    System.out.println(word2vecToken + "-> ADVERB");
                }
            }
        }
        return word2VecTokenTagData;
    }

    @Override
    public List<String> getHaikuPatterns() {
        List<String> haikuPatterns = new ArrayList<>();
        final String sql = "select * from jos_haiku_master_patterns";
        final List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (final Map row : rows) {
            haikuPatterns.add((String) row.get("pattern"));
        }
        return haikuPatterns;
    }

}