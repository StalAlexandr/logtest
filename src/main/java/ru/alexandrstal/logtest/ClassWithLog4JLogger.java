package ru.alexandrstal.logtest;

import org.apache.log4j.Logger;

public class ClassWithLog4JLogger {

    private static final Logger logger = Logger.getLogger(ClassWithLog4JLogger.class);

    public void doSomethingWithInt(int i) {

        logger.info(" - метод doSomethingWithInt вызван с параметром i = " + i);
        if (i > 0) {
            logger.info(" - параметр i больше нуля");
        } else {
            logger.info(" - параметр i меньше или равен нулю");
        }
    }
}


