package com.haikuMaster.composer;

import com.haikuMaster.data.Word2VecTokenTagData;
import com.haikuMaster.tokenizer.Tokenizer;

import java.util.List;
import java.util.Random;

/**
 * Created by Oliver on 2/24/2017.
 */
public class HaikuSentenceCreatorImpl implements HaikuSentenceCreator {

    private Tokenizer tokenizer;

    private Random randomGenerator;


    public HaikuSentenceCreatorImpl(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
        randomGenerator = new Random();
    }

    @Override
    public String create(String haikuSentencePattern, Word2VecTokenTagData word2VecTokenTagData, boolean isFirstSentence) {
        StringBuilder stringBuilder = new StringBuilder();
        List<String> tokens = tokenizer.getTokens(haikuSentencePattern);
        for (String token : tokens) {
            if ("N".equals(token)) {
                if (isFirstSentence) {
                    stringBuilder.append(word2VecTokenTagData.getToken());
                } else {
                    String noun = getRandomWord(word2VecTokenTagData.getNouns());
                    stringBuilder.append(noun);
                }
                stringBuilder.append(" ");
            } else if ("AJ".equals(token)) {
                String adjective = getRandomWord(word2VecTokenTagData.getAdjectives());
                stringBuilder.append(adjective);
                stringBuilder.append(" ");
            } else if ("V".equals(token)) {
                String verb = getRandomWord(word2VecTokenTagData.getVerbs());
                stringBuilder.append(verb);
                stringBuilder.append(" ");
            } else if ("AV".equals(token)) {
                String adverb = getRandomWord(word2VecTokenTagData.getAdverbs());
                stringBuilder.append(adverb);
                stringBuilder.append(" ");
            } else {
                stringBuilder.append(token);
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }

    private String getRandomWord(List<String> list) {
        int index = randomGenerator.nextInt(list.size());
        return list.get(index);
    }
}
