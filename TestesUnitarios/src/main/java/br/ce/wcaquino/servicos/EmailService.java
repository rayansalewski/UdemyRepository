package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;

public interface EmailService {

    void notificaAtrasosUsuario(Usuario usuario);
}
