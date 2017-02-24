package com.haikuMaster.composer;

import com.haikuMaster.data.Word2VecTokenTagData;
import com.haikuMaster.database.TrainingDataDatabaseAccessor;

import java.util.List;

/**
 * Created by Oliver on 2/24/2017.
 */
public class HaikuComposerImpl implements HaikuComposer {

    private TrainingDataDatabaseAccessor trainingDataDatabaseAccessor;

    private HaikuSentenceCreator haikuSentenceCreator;

    private List<String> haikuPatterns;

    public HaikuComposerImpl(TrainingDataDatabaseAccessor trainingDataDatabaseAccessor, HaikuSentenceCreator haikuSentenceCreator) {
        this.trainingDataDatabaseAccessor = trainingDataDatabaseAccessor;
        this.haikuSentenceCreator = haikuSentenceCreator;
        haikuPatterns = trainingDataDatabaseAccessor.getHaikuPatterns();
    }

    @Override
    public String compose(String seedWord) {
        StringBuilder stringBuilder = new StringBuilder();
        List<String> word2VecDataForToken = trainingDataDatabaseAccessor.getWord2VecDataForToken(seedWord);
        Word2VecTokenTagData word2VecTokenTagData = trainingDataDatabaseAccessor.getWord2VecTokenTagData(seedWord, word2VecDataForToken);
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

}
