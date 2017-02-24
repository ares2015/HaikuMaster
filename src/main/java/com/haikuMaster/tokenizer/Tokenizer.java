package com.haikuMaster.tokenizer;

import java.util.List;

/**
 * Created by Oliver on 2/24/2017.
 */
public interface Tokenizer {

    List<String> getTokens(String haikuSentence);
}
