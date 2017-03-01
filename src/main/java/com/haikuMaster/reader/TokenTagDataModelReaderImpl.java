package com.haikuMaster.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Oliver on 3/1/2017.
 */
public class TokenTagDataModelReaderImpl implements TokenTagDataModelReader {

    private String inputFilePath = "C:\\Users\\Oliver\\Documents\\NlpTrainingData\\HaikuMasterTrainingData\\HaikuMasterMergedTokenTagData.txt";

    @Override
    public Map<String, Set<String>> read() throws IOException {
        Map<String, Set<String>> tokenTagDataModel = new HashMap<String, Set<String>>();
        BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
        String trainingDataRowAsString = br.readLine();
        while (trainingDataRowAsString != null) {
            System.out.println("Reading: " + trainingDataRowAsString);
            String[] split = trainingDataRowAsString.split("#");
            String keyToken = split[0];
            if (tokenTagDataModel.containsKey(keyToken)) {
                populateTagsSet(keyToken, tokenTagDataModel, split);
            } else {
                Set<String> tags = new HashSet<>();
                tokenTagDataModel.put(keyToken, tags);
                populateTagsSet(keyToken, tokenTagDataModel, split);
            }
            trainingDataRowAsString = br.readLine();
        }
        return tokenTagDataModel;
    }

    private void populateTagsSet(String keyToken, Map<String, Set<String>> tokenTagDataModel, String[] split) {
        for (int i = 1; i < split.length; i++) {
            if (!"".equals(split[i]) && !" ".equals(split[i])) {
                tokenTagDataModel.get(keyToken).add(split[i]);
            }
        }
    }
}
