package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;

import java.util.Date;
import java.util.List;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;

public class LocacaoService {

	public Locacao alugarFilme(Usuario usuario, List<Filme> listaFilme) throws FilmeSemEstoqueException, LocadoraException {
		if(usuario == null) {
			throw new LocadoraException("Usuario vazio");
		}

		if(listaFilme == null) {
			throw new LocadoraException("Filme vazio");
		}

		isVerificaEstoqueFilme(listaFilme);

		Locacao locacao = new Locacao();
		locacao.setFilme(listaFilme);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());

		double totalValue;
		totalValue = listaFilme.stream().mapToDouble(Filme::getPrecoLocacao).sum();

		locacao.setValor(totalValue);

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);

		//Salvando a locacao...
		//TODO adicionar método para salvar

		return locacao;
	}

	private void isVerificaEstoqueFilme(List<Filme> listfilme) throws FilmeSemEstoqueException{
		for (Filme filme : listfilme) {
			if (filme.getEstoque() == 0) {
				throw new FilmeSemEstoqueException();
			}
		}
	}

}