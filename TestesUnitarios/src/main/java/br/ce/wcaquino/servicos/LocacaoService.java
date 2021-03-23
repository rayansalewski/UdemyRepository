package br.ce.wcaquino.servicos;

import br.ce.wcaquino.daos.LocacaoDAO;
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.utils.DataUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;

public class LocacaoService {

	private LocacaoDAO dao;
	private SPCService spc;

	public LocacaoService() {
	}

	public Locacao alugarFilme(Usuario usuario, List<Filme> listaFilme) throws FilmeSemEstoqueException, LocadoraException {
		if(usuario == null) {
			throw new LocadoraException("Usuario vazio");
		}

		if(listaFilme == null) {
			throw new LocadoraException("Filme vazio");
		}

		if (spc.isUsuarioNegativado(usuario)) {
			throw new LocadoraException("Usuario negativado");
		}

		Locacao locacao = new Locacao();
		locacao.setFilme(listaFilme);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());

		locacao.isVerificaEstoqueFilme();
		locacao.calculaValorTotal();

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);

		//Salvando a locacao...
		//TODO adicionar método para salvar

		return locacao;
	}

	public Locacao alugarFilmeComDesconto(Usuario usuario, List<Filme> listaFilme) throws FilmeSemEstoqueException, LocadoraException {
		if(usuario == null) {
			throw new LocadoraException("Usuario vazio");
		}

		if(listaFilme == null) {
			throw new LocadoraException("Filme vazio");
		}

		Locacao locacao = new Locacao();
		locacao.setFilme(listaFilme);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());

		locacao.isVerificaEstoqueFilme();
		locacao.calculaValorTotalComDesconto();

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		if(DataUtils.verificarDiaSemana(dataEntrega, Calendar.SATURDAY)){
			dataEntrega = adicionarDias(dataEntrega, 1);
		}
		locacao.setDataRetorno(dataEntrega);

		//Salvando a locacao...
		//TODO adicionar método para salvar
		dao.salvar();

		return locacao;
	}

	public void setDao(LocacaoDAO dao) {
		this.dao = dao;
	}

	public void setSPCService(SPCService spc) {
		this.spc = spc;
	}
}