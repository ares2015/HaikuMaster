package com.haikuMaster.composer;

import com.haikuMaster.data.Word2VecTokenTagData;
import com.haikuMaster.factory.Word2VecTokenTagDataFactory;
import com.haikuMaster.reader.TokenTagDataModelReader;
import com.haikuMaster.reader.Word2VecModelReader;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Oliver on 2/24/2017.
 */
public class HaikuComposerImpl implements HaikuComposer {

    private TokenTagDataModelReader tokenTagDataModelReader;

    private Word2VecModelReader word2VecModelReader;

    private Word2VecTokenTagDataFactory word2VecTokenTagDataFactory;

    private HaikuSentenceCreator haikuSentenceCreator;

    private Map<String, Set<String>> tokenTagDataModel;

    private Map<String, List<String>> word2VecModel;

    private List<String> haikuPatterns;

    public HaikuComposerImpl(TokenTagDataModelReader tokenTagDataModelReader, Word2VecModelReader word2VecModelReader,
                             Word2VecTokenTagDataFactory word2VecTokenTagDataFactory,
                             HaikuSentenceCreator haikuSentenceCreator) throws IOException {
        this.tokenTagDataModelReader = tokenTagDataModelReader;
        this.word2VecModelReader = word2VecModelReader;
        this.word2VecTokenTagDataFactory = word2VecTokenTagDataFactory;
        this.haikuSentenceCreator = haikuSentenceCreator;
        tokenTagDataModel = tokenTagDataModelReader.read();
        word2VecModel = word2VecModelReader.read();
    }

    @Override
    public String compose(String seedWord) {
        StringBuilder stringBuilder = new StringBuilder();
        List<String> word2VecDataForToken = word2VecModel.get(seedWord);
        if (word2VecDataForToken != null) {
            Word2VecTokenTagData word2VecTokenTagData = word2VecTokenTagDataFactory.create(seedWord, word2VecDataForToken, tokenTagDataModel);
//        String haikuPattern = haikuPatterns.get(0);
            String haikuPattern = "@A N V @the V N ; on the N @a N V and V . @";
            String[] haikuPatternSentences = haikuPattern.split("@");
            int haikuIndex = 0;
            for (String haikuPatternSentence : haikuPatternSentences) {
                if (!"".equals(haikuPatternSentence)) {
                    boolean isFirstSentence = haikuIndex == 0;
                    String haikuSentence = haikuSentenceCreator.create(haikuPatternSentence, word2VecTokenTagData, isFirstSentence);
                    stringBuilder.append(haikuSentence);
                    stringBuilder.append(" ");

//                stringBuilder.append("\n");
                    haikuIndex++;
                }
            }
            return stringBuilder.toString();
        }
        return "no haiku could be created";
    }


}
