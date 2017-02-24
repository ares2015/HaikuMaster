package com.haikuMaster.database;

import com.haikuMaster.data.Word2VecTokenTagData;

import java.util.List;

/**
 * Created by Oliver on 2/21/2017.
 */
public interface TrainingDataDatabaseAccessor {

    List<String> getWord2VecDataForToken(String token);

    Word2VecTokenTagData getWord2VecTokenTagData(String token, List<String> word2vecList);

    List<String> getHaikuPatterns();

}
