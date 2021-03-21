package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static br.ce.wcaquino.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LocacaoServiceTest {

	static int contador = 0;

	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Rule
	public ExpectedException exception = ExpectedException.none();

	private LocacaoService service;

	private Usuario usuario;

	private List<Filme> filme;

	private List<Filme> filmeSemEstoque;

	@Before
	public void init() {
		//cenario
		this.service = new LocacaoService();
		this.usuario = new Usuario("Usuario 1");
		this.filme = new ArrayList<>();

		this.filme.add(new Filme("Filme 1", 1, 5.0));
		this.filme.add(new Filme("Filme 2", 2, 6.0));

		this.filmeSemEstoque = new ArrayList<>();
		this.filmeSemEstoque.add(new Filme("Filme 1", 0, 5.0));

	}

	@After
	public void incrementAfterTest() {
		contador++;
	}

	@AfterClass
	public static void printContador() {
		System.out.println(contador);
	}

	@Test
	public void testeLocacao() throws Exception {

		//acao
		Locacao locacao = service.alugarFilme(usuario, filme);
			
		//verificacao
		error.checkThat(locacao.getValor(), is(equalTo(11.0)));
		error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		error.checkThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
	}
	
	@Test(expected = FilmeSemEstoqueException.class)
	public void testLocacao_filmeSemEstoque() throws Exception{
		//acao
		service.alugarFilme(usuario, filmeSemEstoque);
	}
	
	@Test
	public void testLocacao_usuarioVazio() throws FilmeSemEstoqueException{

		//acao
		try {
			service.alugarFilme(null, filme);
			Assert.fail();
		} catch (LocadoraException e) {
			assertThat(e.getMessage(), is("Usuario vazio"));
		}
	}
	

	@Test
	public void testLocacao_FilmeVazio() throws FilmeSemEstoqueException, LocadoraException{

		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");
		
		//acao
		service.alugarFilme(usuario, null);
	}
}
