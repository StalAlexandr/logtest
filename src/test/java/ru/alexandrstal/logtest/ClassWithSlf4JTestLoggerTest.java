package ru.alexandrstal.logtest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import uk.org.lidalia.slf4jtest.TestLogger;
import uk.org.lidalia.slf4jtest.TestLoggerFactory;

public class ClassWithSlf4JTestLoggerTest {


    //получаем тестовый логгер
    TestLogger logger = TestLoggerFactory.getTestLogger(ClassWithSlf4JLogger.class);

    @Test
    public void doLoggingTest() {

        ClassWithSlf4JLogger classWithSlf4JLogger = new ClassWithSlf4JLogger();
        classWithSlf4JLogger.doSomethingWithInt(1);

        //проверяем сообщения передаваемые в логгер
        Assert.assertEquals(" - метод doSomethingWithInt вызван с параметром i = {}", logger.getLoggingEvents().asList().get(0).getMessage());
        Assert.assertEquals(1, logger.getLoggingEvents().asList().get(0).getArguments().get(0));
        Assert.assertEquals(" - параметр i больше нуля", logger.getLoggingEvents().asList().get(1).getMessage());
        Assert.assertEquals(2, logger.getLoggingEvents().asList().size());
    }

    @After
    public void clearLoggers() {
        TestLoggerFactory.clear();
    }

}
