package ru.alexandrstal.logtest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyVararg;
import static org.powermock.api.mockito.PowerMockito.mock;


@RunWith(PowerMockRunner.class)
// информируем PowerMock что придется вносить изменения в класс LoggerFactory
@PrepareForTest({LoggerFactory.class})
public class PowerMockitoLoggingTest {

    // наш фейковый логгер
    private static Logger logger = mock(Logger.class);;

    // перееопределяем работу метода LoggerFactory.getLogger - теперь при вызове всегда вернет наш логгер
    static{
        PowerMockito.spy(LoggerFactory.class);
        try {
            PowerMockito.doReturn(logger).when(LoggerFactory.class, "getLogger",
                    any());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void doLoggingTest() {

        ClassWithSlf4JLogger classWithSlf4JLogger = new ClassWithSlf4JLogger();
        classWithSlf4JLogger.doSomethingWithInt(1);

        //протестируем вызовы нашего логгера.
        // ради разнообразия - убедимся заодно в том, что методы логгера вызывались не просто с верными параметрами но и в нужном порядке
        InOrder inOrd = Mockito.inOrder(logger);
        inOrd.verify(logger).info(" - метод doSomethingWithInt вызван с параметром i = {}",1);
        inOrd.verify(logger).info(" - параметр i больше нуля");
        Mockito.verify(logger, Mockito.times(1)).info(anyString());
        Mockito.verify(logger, Mockito.times(1)).info(anyString(), anyVararg());
    }

}
