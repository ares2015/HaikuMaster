package com.haikuMaster.database;

import java.util.List;

/**
 * Created by Oliver on 2/21/2017.
 */
public interface TrainingDataDatabaseAccessor {

    List<String> getWord2VecDataForToken(String token);

}
