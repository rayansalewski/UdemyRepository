package br.ce.wcaquino.servicos;

public class Calculadora {

    public int somar(int a, int b) {
        System.out.println("Somando");
        return a+b;
    }

    public int subtrai(int a, int b) {
        return a-b;
    }

    public int dividir(int a, int b) {
        return a/b;
    }

    public void imprime() {
        System.out.println("Imprimindo");
    }
}
