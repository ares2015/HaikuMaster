package com.haikuMaster.factory;

import com.haikuMaster.data.Word2VecTokenTagData;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Oliver on 3/1/2017.
 */
public class Word2VecTokenTagDataFactoryImpl implements Word2VecTokenTagDataFactory {


    @Override
    public Word2VecTokenTagData create(String token, List<String> word2vecList, Map<String, Set<String>> tokenTagDataModel) {
        Word2VecTokenTagData word2VecTokenTagData = new Word2VecTokenTagData();
        word2VecTokenTagData.setToken(token);
        for (String word2vecToken : word2vecList) {
            if (tokenTagDataModel.containsKey(word2vecToken)) {
                Set<String> word2VecWordTags = tokenTagDataModel.get(word2vecToken);
                if (word2VecWordTags.contains("N")) {
                    word2VecTokenTagData.getNouns().add(word2vecToken);
                    System.out.println(word2vecToken + "-> NOUN");
                }
                if (word2VecWordTags.contains("AJ")) {
                    word2VecTokenTagData.getAdjectives().add(word2vecToken);
                    System.out.println(word2vecToken + "-> ADJECTIVE");
                }
                if (word2VecWordTags.contains("V")) {
                    word2VecTokenTagData.getVerbs().add(word2vecToken);
                    System.out.println(word2vecToken + "-> VERB");
                }
                if (word2VecWordTags.contains("AV")) {
                    word2VecTokenTagData.getAdverbs().add(word2vecToken);
                    System.out.println(word2vecToken + "-> ADVERB");
                }
            }
        }

        return word2VecTokenTagData;
    }
}
