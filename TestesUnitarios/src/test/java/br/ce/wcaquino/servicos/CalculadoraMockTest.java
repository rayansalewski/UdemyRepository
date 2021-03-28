package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class CalculadoraMockTest {

    @Test
    public void calculadoraMockTest() {
        Calculadora calculadora = Mockito.mock(Calculadora.class);

        ArgumentCaptor<Integer> argCpt = ArgumentCaptor.forClass(Integer.class);
        Mockito.when(calculadora.somar(argCpt.capture(), argCpt.capture())).thenReturn(4);

        Assert.assertEquals(4, calculadora.somar(1, 3));
        System.out.println(argCpt.getAllValues());
    }
}
