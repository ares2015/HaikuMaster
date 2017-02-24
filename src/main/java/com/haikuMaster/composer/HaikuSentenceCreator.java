package com.haikuMaster.composer;

import com.haikuMaster.data.Word2VecTokenTagData;

/**
 * Created by Oliver on 2/24/2017.
 */
public interface HaikuSentenceCreator {

    String create(String haikuSentencePattern, Word2VecTokenTagData word2VecTokenTagData, boolean isFirstSentence);
}
