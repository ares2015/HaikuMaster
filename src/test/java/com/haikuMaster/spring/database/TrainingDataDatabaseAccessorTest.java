package com.haikuMaster.spring.database;

import com.haikuMaster.database.TrainingDataDatabaseAccessor;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by Oliver on 2/21/2017.
 */
public class TrainingDataDatabaseAccessorTest {

    @Test
    public void testGetWord2vecDataForToken() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        TrainingDataDatabaseAccessor trainingDataDatabaseAccessor = (TrainingDataDatabaseAccessor) context.getBean("trainingDataDatabaseAccessor");
        List<String> word2vecList = trainingDataDatabaseAccessor.getWord2VecDataForToken("dog");
        assertTrue(word2vecList.size() > 0);
    }
}
