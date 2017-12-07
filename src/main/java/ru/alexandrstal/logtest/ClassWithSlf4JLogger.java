package ru.alexandrstal.logtest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassWithSlf4JLogger {

    private static final Logger logger = LoggerFactory.getLogger(ClassWithSlf4JLogger.class);

    public void doSomethingWithInt(int i) {

        logger.info(" - метод doSomethingWithInt вызван с параметром i = {}", i);
        if (i > 0) {
            logger.info(" - параметр i больше нуля");
        } else {
            logger.info(" - параметр i больше или равен нулю");
        }
    }
}
