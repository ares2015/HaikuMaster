package com.haikuMaster.database;

import com.haikuMaster.data.Word2VecTokenTagData;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by Oliver on 2/21/2017.
 */
public class TrainingDataDatabaseAccessorTest {

    private TrainingDataDatabaseAccessor trainingDataDatabaseAccessor;

    @Before
    public void setup() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        trainingDataDatabaseAccessor = (TrainingDataDatabaseAccessor) context.getBean("trainingDataDatabaseAccessor");
    }

    @Test
    public void testGetWord2vecDataForToken() {
        List<String> word2vecList = trainingDataDatabaseAccessor.getWord2VecDataForToken("dog");
        assertTrue(word2vecList.size() > 0);
    }

    @Test
    public void testGetWord2VecTokenTagData() {
        String token = "dog";
        List<String> word2vecList = trainingDataDatabaseAccessor.getWord2VecDataForToken(token);
        Word2VecTokenTagData word2VecTokenTagData = trainingDataDatabaseAccessor.getWord2VecTokenTagData(token, word2vecList);
        assertTrue(word2VecTokenTagData.getNouns().size() > 0);
        assertTrue(word2VecTokenTagData.getAdjectives().size() > 0);
        assertTrue(word2VecTokenTagData.getVerbs().size() > 0);
        assertTrue(word2VecTokenTagData.getAdverbs().size() > 0);
    }

    @Test
    public void getHaikuPatterns() {
        assertTrue(trainingDataDatabaseAccessor.getHaikuPatterns().size() > 0);
    }
}
