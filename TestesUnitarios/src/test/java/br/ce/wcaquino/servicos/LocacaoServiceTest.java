package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.utils.DataUtils;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Calendar;
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

	@Before
	public void init() {
		//cenario
		this.service = new LocacaoService();
		this.usuario = new Usuario("Usuario 1");
		this.filme = new ArrayList<>();

		this.filme.add(new Filme("Filme 1", 1, 5.0));
		this.filme.add(new Filme("Filme 2", 2, 5.0));
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
	public void deveAlugarFilme() throws Exception {
		Assume.assumeFalse(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));

		//acao
		Locacao locacao = service.alugarFilme(usuario, filme);
			
		//verificacao
		error.checkThat(locacao.getValor(), is(equalTo(10.0)));
		error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		error.checkThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
	}
	
	@Test(expected = FilmeSemEstoqueException.class)
	public void deveLancarExcecaofilmeSemEstoque() throws Exception{
		this.filme.add(new Filme("Filme 3", 0, 5.0));
		//acao
		service.alugarFilme(usuario, filme);
	}
	
	@Test
	public void naoDeveAlugarFilmeSemusuario() throws FilmeSemEstoqueException{

		//acao
		try {
			service.alugarFilme(null, filme);
			Assert.fail();
		} catch (LocadoraException e) {
			assertThat(e.getMessage(), is("Usuario vazio"));
		}
	}
	

	@Test
	public void naoDeveAlugarFilmeSemFilme() throws FilmeSemEstoqueException, LocadoraException{

		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");
		
		//acao
		service.alugarFilme(usuario, null);
	}

	@Test
	public void locacaoFilmeComDesconto() throws Exception {

		this.filme.add(new Filme("Filme 3", 1, 5.0));
		this.filme.add(new Filme("Filme 4", 2, 5.0));
		this.filme.add(new Filme("Filme 5", 1, 5.0));
		this.filme.add(new Filme("Filme 6", 2, 5.0));

		//acao
		Locacao locacao = service.alugarFilmeComDesconto(usuario, filme);

		//verificacao
		error.checkThat(locacao.getValor(), is(equalTo(17.5)));
	}

	@Test
	public void deveDevolverFilmeAlugadoNoSabadoNaSegunda() throws Exception {
		Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));

		//acao
		Locacao locacao = service.alugarFilme(usuario, filme);

		Assert.assertTrue(DataUtils.verificarDiaSemana(locacao.getDataRetorno(), Calendar.MONDAY));
	}
}
