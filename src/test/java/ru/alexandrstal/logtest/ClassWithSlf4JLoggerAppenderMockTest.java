package ru.alexandrstal.logtest;


import org.apache.log4j.Appender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

// тест управляется Mockito который создаст и инициализирует мок-объекты
@RunWith(MockitoJUnitRunner.class)
public class ClassWithSlf4JLoggerAppenderMockTest {

    @Mock
    Appender mockAppender;

    @Test
    public void doLoggingTest() {


        // Получаем логгер для нашего класса - причем это не slf4j логгер а все тот
        Logger logger = Logger.getLogger(ClassWithSlf4JLogger.class);
        logger.addAppender(mockAppender);

        ClassWithSlf4JLogger classWithSlf4JLogger = new ClassWithSlf4JLogger();
        classWithSlf4JLogger.doSomethingWithInt(1);

        ArgumentCaptor<LoggingEvent> eventArgumentCaptor = ArgumentCaptor.forClass(LoggingEvent.class);

        verify(mockAppender, times(2)).doAppend(eventArgumentCaptor.capture());
        Assert.assertEquals(" - метод doSomethingWithInt вызван с параметром i = 1", eventArgumentCaptor.getAllValues().get(0).getMessage());
        Assert.assertEquals(" - параметр i больше нуля", eventArgumentCaptor.getAllValues().get(1).getMessage());

        Assert.assertEquals(Level.INFO, eventArgumentCaptor.getAllValues().get(0).getLevel());
        Assert.assertEquals(Level.INFO, eventArgumentCaptor.getAllValues().get(1).getLevel());
    }

}

