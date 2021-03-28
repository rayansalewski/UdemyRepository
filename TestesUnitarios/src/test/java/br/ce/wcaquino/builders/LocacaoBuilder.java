package br.ce.wcaquino.builders;

import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;

import java.util.Date;

import static br.ce.wcaquino.utils.DataUtils.*;

public class LocacaoBuilder {

    private Locacao locacao;

    public LocacaoBuilder() {
    }

    public static LocacaoBuilder umaLocacao() {
        LocacaoBuilder locacaoBuilder = new LocacaoBuilder();
        locacaoBuilder.locacao = new Locacao();
        locacaoBuilder.locacao.setDataLocacao(new Date());
        locacaoBuilder.locacao.setDataRetorno(obterDataComDiferencaDias(2));
        locacaoBuilder.locacao.setUsuario(new Usuario("usuario"));
        locacaoBuilder.locacao.setValor(5.0);

        return locacaoBuilder;
    }

    public LocacaoBuilder comUsuario(Usuario usuario){
        this.locacao.setUsuario(usuario);
        return this;
    }

    public LocacaoBuilder comDataRetorno(Date data){
        this.locacao.setDataRetorno(data);
        return this;
    }

    public LocacaoBuilder comAtraso(){
        this.locacao.setDataLocacao(obterDataComDiferencaDias(-4));
        this.locacao.setDataRetorno(obterDataComDiferencaDias(-2));
        return this;
    }

    public Locacao agora() {
        return this.locacao;
    }
}
