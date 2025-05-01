package org.example.qaTransactionTeam;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import java.lang.reflect.Method;

abstract public class BaseTest {
    protected static Logger logger = Logger.getLogger("Test");

    @BeforeMethod
    public void startMethod(Method method){
        logger.info("================         "+method.getName()+"         =================");
    }

    protected static void logStartTest(String testCase){
        logger.info("Test case - "+testCase);
    }

    protected static void logFinishTest(String nameTest){}
}
