package br.ce.wcaquino.builders;

import br.ce.wcaquino.entidades.Filme;

public class FilmeBuilder {

    private Filme filme;

    private FilmeBuilder() {
    }

    public static FilmeBuilder umFilme() {
        FilmeBuilder builder = new FilmeBuilder();
        builder.filme = new Filme("Filme 1", 1, 5.0);

        return builder;
    }

    public static FilmeBuilder umFilmeSemEstoque() {
        FilmeBuilder builder = new FilmeBuilder();
        builder.filme = new Filme("Filme 1", 0, 5.0);

        return builder;
    }

    public FilmeBuilder comValor(double valor){
        filme.setPrecoLocacao(valor);
        return this;
    }

    public Filme agora() {
        return filme;
    }
}
