package com.wikipediaSemanticAnalyser;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertTrue;

/**
 * Created by oled on 6/12/2017.
 */
public class SpringConfigurationTest {

    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        assertTrue(context != null);
    }

}
