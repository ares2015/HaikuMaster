package com.haikuMaster.composer;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Oliver on 2/24/2017.
 */
public class HaikuComposerTest {

    private HaikuComposer haikuComposer;

    @Before
    public void setup() {
        final ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        haikuComposer = (HaikuComposer) context.getBean("haikuComposer");
    }

    @Test
    public void test() {
        String haiku = haikuComposer.compose("dog");
        System.out.println(haiku);

    }
}
