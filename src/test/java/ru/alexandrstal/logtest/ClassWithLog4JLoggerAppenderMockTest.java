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
public class ClassWithLog4JLoggerAppenderMockTest {

    @Mock
    Appender mockAppender;

    @Test
    public void doLoggingTest() {

        // Получаем логгер для нашего класса
        Logger logger = Logger.getLogger(ClassWithLog4JLogger.class);
        // Передаем логгеру mock-аппендер
        logger.addAppender(mockAppender);

        // вызываем тестируемый метод
        ClassWithLog4JLogger classWithLog4JLogger = new ClassWithLog4JLogger();
        classWithLog4JLogger.doSomethingWithInt(1);

        // 'перехватчик' передаваемых в mock параметров
        ArgumentCaptor<LoggingEvent> eventArgumentCaptor = ArgumentCaptor.forClass(LoggingEvent.class);

        //проверяем, что аппендер вызывался два раза
        verify(mockAppender, times(2)).doAppend(eventArgumentCaptor.capture());
        //проверяем с какими параметрами вызывался аппендер (выходные сообщения)
        Assert.assertEquals(" - метод doSomethingWithInt вызван с параметром i = 1", eventArgumentCaptor.getAllValues().get(0).getMessage());
        Assert.assertEquals(" - параметр i больше нуля", eventArgumentCaptor.getAllValues().get(1).getMessage());

        //проверяем с какими параметрами вызывался аппендер (уровень логгирования)
        Assert.assertEquals(Level.INFO, eventArgumentCaptor.getAllValues().get(0).getLevel());
        Assert.assertEquals(Level.INFO, eventArgumentCaptor.getAllValues().get(1).getLevel());

    }
}

