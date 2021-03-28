package br.ce.wcaquino.servicos;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CalculadoraMockTest {

    @Mock
    Calculadora calcMock;

    @Spy
    Calculadora calcSpy;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void diferencasEntreMockESpy() {
        when(calcMock.somar(1, 2)).thenReturn(4);
        //when(calcSpy.somar(1, 2)).thenReturn(4);
        doReturn(5).when(calcSpy).somar(1, 2);
        doNothing().when(calcSpy).imprime();

        System.out.println(calcMock.somar(1, 2));
        System.out.println(calcSpy.somar(1, 2));
    }

    @Test
    public void calculadoraMockTest() {
        Calculadora calculadora = mock(Calculadora.class);

        ArgumentCaptor<Integer> argCpt = ArgumentCaptor.forClass(Integer.class);
        when(calculadora.somar(argCpt.capture(), argCpt.capture())).thenReturn(4);

        assertEquals(4, calculadora.somar(1, 3));
        System.out.println(argCpt.getAllValues());

    }
}
