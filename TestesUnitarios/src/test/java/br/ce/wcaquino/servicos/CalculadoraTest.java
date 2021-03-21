package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculadoraTest {

    private Calculadora calc;
    private int a;
    private int b;

    @Before
    public void initValues(){
        this.a = 6;
        this.b = 3;
        this.calc = new Calculadora();
    }

    @Test
    public void deveSomarDoisValores(){
        int resultado = calc.somar(a,b);
        Assert.assertEquals(9, resultado);
    }

    @Test
    public void deveSubtrairDoisValores(){
        int resultado = calc.subtrai(a,b);
        Assert.assertEquals(3, resultado);
    }

    @Test
    public void deveDividirDoisValores(){
        int resultado = calc.dividir(a,b);
        Assert.assertEquals(2, resultado);
    }

    @Test(expected = ArithmeticException.class)
    public void deveLancarExecaoAoDividrPorZero(){
        b = 0;
        calc.dividir(a,b);
    }
}

