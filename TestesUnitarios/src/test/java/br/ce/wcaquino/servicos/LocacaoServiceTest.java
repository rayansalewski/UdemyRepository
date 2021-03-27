package br.ce.wcaquino.servicos;

import br.ce.wcaquino.builders.FilmeBuilder;
import br.ce.wcaquino.builders.UsuarioBuilder;
import br.ce.wcaquino.daos.LocacaoDAO;
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static br.ce.wcaquino.builders.LocacaoBuilder.*;
import static br.ce.wcaquino.matchers.MatchersProprios.*;
import static br.ce.wcaquino.utils.DataUtils.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeTrue;
import static org.mockito.Mockito.*;

public class LocacaoServiceTest {

	static int contador = 0;

	@Rule
	public ErrorCollector error = new ErrorCollector();
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@InjectMocks
	private LocacaoService service;

	@Mock
	private SPCService spc;
	@Mock
	private EmailService emailService;
	@Mock
	private LocacaoDAO locacaoDAO;

	private Usuario usuario;
	private List<Filme> filme;

	@Before
	public void init() {
		//cenario
		this.service = new LocacaoService();

		MockitoAnnotations.initMocks(this);

		this.usuario = UsuarioBuilder.umUsuario().agora();

		this.filme = new ArrayList<>();
		this.filme.add(FilmeBuilder.umFilme().agora());
		this.filme.add(FilmeBuilder.umFilme().agora());
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
		assumeFalse(verificarDiaSemana(new Date(), Calendar.SATURDAY));

		//acao
		Locacao locacao = service.alugarFilme(usuario, filme);
			
		//verificacao
		error.checkThat(locacao.getValor(), is(equalTo(10.0)));
		error.checkThat(locacao.getDataLocacao(), ehHoje());
		error.checkThat(locacao.getDataRetorno(), ehHojeComDiferenca(1));
	}
	
	@Test(expected = FilmeSemEstoqueException.class)
	public void deveLancarExcecaofilmeSemEstoque() throws Exception{
		this.filme.add(FilmeBuilder.umFilmeSemEstoque().agora());
		//acao
		service.alugarFilme(usuario, filme);
	}
	
	@Test
	public void naoDeveAlugarFilmeSemusuario() throws FilmeSemEstoqueException{

		//acao
		try {
			service.alugarFilme(null, filme);
			fail();
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
		assumeTrue(verificarDiaSemana(new Date(), Calendar.SATURDAY));

		//acao
		Locacao locacao = service.alugarFilme(usuario, filme);

		assertThat(locacao.getDataRetorno(), caiEmUmaSegunda());
	}

	@Test
	public void naoDeveAlugarFilmeUsuariosNegativados() throws FilmeSemEstoqueException {

		when(spc.isUsuarioNegativado(any(Usuario.class))).thenReturn(true);

		//acao
		try {
			service.alugarFilme(usuario, filme);
			Assert.fail();
		} catch (LocadoraException e) {
			Assert.assertThat(e.getMessage(), is("Usuario negativado"));
		}

		verify(spc).isUsuarioNegativado(usuario);
	}

	@Test
	public void deveNotificarLocacoesEmAtraso() {

		Usuario usuario2 = new Usuario("Sem Atraso");
		Usuario usuario3 = new Usuario("Outro Atrasado");

		List<Locacao> locacoes = Arrays.asList(
				umaLocacao().comUsuario(usuario).comAtraso().agora(),
				umaLocacao().comUsuario(usuario2).agora(),
				umaLocacao().comUsuario(usuario3).comAtraso().agora());

		when(locacaoDAO.getLocacoesEmAtraso()).thenReturn(locacoes);

		service.notificarAtrasos();

		verify(emailService, times(2)).notificaAtrasosUsuario(any(Usuario.class));
		verify(emailService).notificaAtrasosUsuario(usuario);
		verify(emailService, atLeastOnce()).notificaAtrasosUsuario(usuario3);
		verify(emailService, never()).notificaAtrasosUsuario(usuario2);
		verifyNoMoreInteractions(emailService);
	}

}
