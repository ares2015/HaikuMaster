package com.haikuMaster.tokenizer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oliver on 2/24/2017.
 */
public class TokenizerImpl implements Tokenizer {

    @Override
    public List<String> getTokens(String haikuSentence) {
        String[] tokTmp;
        tokTmp = haikuSentence.split("\\ ");
        final List<String> tokens = removeEmptyStringInSentence(tokTmp);
        return tokens;
    }

    /**
     * Removes empty String that is located in sentences with index > 0
     * that is created by empty space behind each sentence (space between
     * end of the sentence and the start of the new sentence). It would be more
     * efficient to remove only first token (empty string) but this loop guarantees
     * that all accidental empty strings are removed.
     *
     * @param tokens Array of tokens with empty strings.
     * @return List of tokens without empty strings.
     */
    private List<String> removeEmptyStringInSentence(final String[] tokens) {
        final List<String> listTokens = new ArrayList<String>();
        for (final String token : tokens) {
            if (!token.equals("")) {
                listTokens.add(token);
            }
        }
        return listTokens;
    }
}
