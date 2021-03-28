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
import static br.ce.wcaquino.utils.DataUtils.obterDataComDiferencaDias;

public class LocacaoService {

	private LocacaoDAO dao;
	private SPCService spc;
	private EmailService emailService;

	public LocacaoService() {
	}

	public Locacao alugarFilme(Usuario usuario, List<Filme> listaFilme) throws FilmeSemEstoqueException, LocadoraException {
		if(usuario == null) {
			throw new LocadoraException("Usuario vazio");
		}

		if(listaFilme == null) {
			throw new LocadoraException("Filme vazio");
		}

		boolean usuarioNegativado;

		try {
			usuarioNegativado = spc.isUsuarioNegativado(usuario);
		} catch (Exception e) {
			throw new LocadoraException("Problemas com o SPC, tente novamente");
		}

		if (usuarioNegativado) {
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

		if(DataUtils.verificarDiaSemana(dataEntrega, Calendar.SUNDAY)){
			dataEntrega = adicionarDias(dataEntrega, 1);
		}

		locacao.setDataRetorno(dataEntrega);

		//Salvando a locacao...
		//TODO adicionar método para salvar
		dao.salvar(locacao);

		return locacao;
	}

	public void notificarAtrasos() {
		List<Locacao> locacoes = dao.getLocacoesEmAtraso();
		for (Locacao locacao : locacoes) {
			if (locacao.getDataRetorno().before(new Date())) {
				emailService.notificaAtrasosUsuario(locacao.getUsuario());
			}
		}
	}

	public void  prorrogarDataLocacao(Locacao locacao, int dias){
		Locacao novaLocacao = new Locacao();

		novaLocacao.setUsuario(locacao.getUsuario());
		novaLocacao.setFilme(locacao.getFilme());
		novaLocacao.setDataLocacao(new Date());
		novaLocacao.setDataRetorno(obterDataComDiferencaDias(dias));
		novaLocacao.setValor(locacao.getValor() * dias);

		dao.salvar(novaLocacao);
	}
}