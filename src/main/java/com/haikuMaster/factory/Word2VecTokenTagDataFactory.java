package com.haikuMaster.factory;

import com.haikuMaster.data.Word2VecTokenTagData;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Oliver on 3/1/2017.
 */
public interface Word2VecTokenTagDataFactory {

    Word2VecTokenTagData create(String token, List<String> word2VecDataForToken, Map<String, Set<String>> tokenTagDataModel);
}
