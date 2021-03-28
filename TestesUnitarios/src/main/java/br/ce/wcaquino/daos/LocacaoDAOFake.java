package br.ce.wcaquino.daos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;

import java.util.List;

public class LocacaoDAOFake implements LocacaoDAO{
    @Override
    public void salvar(Locacao locacao) {

    }

    @Override
    public List<Locacao> getLocacoesEmAtraso() {
        return null;
    }
}
