package br.ce.wcaquino.servicos;

import br.ce.wcaquino.builders.FilmeBuilder;
import br.ce.wcaquino.builders.LocacaoBuilder;
import br.ce.wcaquino.builders.UsuarioBuilder;
import br.ce.wcaquino.daos.LocacaoDAO;
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.utils.DataUtils;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static br.ce.wcaquino.builders.LocacaoBuilder.umaLocacao;
import static br.ce.wcaquino.matchers.MatchersProprios.*;
import static br.ce.wcaquino.utils.DataUtils.obterData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({LocacaoService.class, DataUtils.class})
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
	private List<Filme> filmes;

	@Before
	public void init() {
		//cenario
		this.service = new LocacaoService();

		MockitoAnnotations.initMocks(this);

		this.usuario = UsuarioBuilder.umUsuario().agora();

		this.service = PowerMockito.spy(service);

		this.filmes = new ArrayList<>();
		this.filmes.add(FilmeBuilder.umFilme().agora());
		this.filmes.add(FilmeBuilder.umFilme().agora());
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
		//assumeFalse(verificarDiaSemana(new Date(), Calendar.SATURDAY));

		PowerMockito.whenNew(Date.class).withNoArguments().thenReturn(obterData(26, 3, 2021));

		//acao
		Locacao locacao = service.alugarFilme(usuario, filmes);
			
		//verificacao
		error.checkThat(locacao.getValor(), is(equalTo(10.0)));
		error.checkThat(locacao.getDataLocacao(), ehHoje());
		error.checkThat(locacao.getDataRetorno(), ehHojeComDiferenca(1));
	}
	
	@Test(expected = FilmeSemEstoqueException.class)
	public void deveLancarExcecaofilmeSemEstoque() throws Exception{
		this.filmes.add(FilmeBuilder.umFilmeSemEstoque().agora());
		//acao
		service.alugarFilme(usuario, filmes);
	}
	
	@Test
	public void naoDeveAlugarFilmeSemusuario() throws FilmeSemEstoqueException{

		//acao
		try {
			service.alugarFilme(null, filmes);
			fail();
		} catch (LocadoraException e) {
			assertThat(e.getMessage(), is("Usuario vazio"));
		}
	}


	@Test
	public void naoDeveAlugarFilmeSemFilme() throws FilmeSemEstoqueException, LocadoraException {

		//Novo modo devido a depreciação do ExpectedException.none();

		//Como era e como deve ser
		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");
		//acao
		service.alugarFilme(usuario, null);

		/* assertThrows("Filme vazio",
				LocadoraException.class,
				() -> {
					service.alugarFilme(usuario, null);
				});
		 */
	}

	@Test
	public void locacaoFilmeComDesconto() throws Exception {

		this.filmes.add(new Filme("Filme 3", 1, 5.0));
		this.filmes.add(new Filme("Filme 4", 2, 5.0));
		this.filmes.add(new Filme("Filme 5", 1, 5.0));
		this.filmes.add(new Filme("Filme 6", 2, 5.0));

		//acao
		Locacao locacao = service.alugarFilme(usuario, filmes);

		//verificacao
		error.checkThat(locacao.getValor(), is(equalTo(17.5)));
	}

	@Test
	public void deveDevolverFilmeAlugadoNoSabadoNaSegunda() throws Exception {
		//assumeTrue(verificarDiaSemana(new Date(), Calendar.SATURDAY));
		PowerMockito.whenNew(Date.class).withNoArguments().thenReturn(obterData(27, 3, 2021));

		//PowerMock para metodos staticos
/*		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 27);
		calendar.set(Calendar.MONTH, 3);
		calendar.set(Calendar.YEAR, 2012);
		PowerMockito.mockStatic(Calendar.class);
		PowerMockito.when(Calendar.getInstance()).thenReturn(calendar);*/

		//acao
		Locacao locacao = service.alugarFilme(usuario, filmes);

		assertThat(locacao.getDataRetorno(), caiEmUmaSegunda());
		PowerMockito.verifyNew(Date.class, Mockito.times(2)).withNoArguments();

		//Verificacao de metodo estatico
		/*
		PowerMockito.verifyStatic(Mockito.times(2);
		Calendar.getInstance();
		*/
	}

	@Test
	public void naoDeveAlugarFilmeUsuariosNegativados() throws Exception {

		when(spc.isUsuarioNegativado(any(Usuario.class))).thenReturn(true);

		//acao
		try {
			service.alugarFilme(usuario, filmes);
			Assert.fail();
		} catch (LocadoraException e) {
			assertThat(e.getMessage(), is("Usuario negativado"));
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

	@Test
	public void deveTratarErroNoSPC() throws Exception {

		when(spc.isUsuarioNegativado(usuario)).thenThrow(new Exception("Erro de integração"));

		exception.expect(LocadoraException.class);
		exception.expectMessage("Problemas com o SPC, tente novamente");

		service.alugarFilme(usuario, filmes);
	}

	@Test
	public void deveProrrogarLocacao() {

		Locacao locacao = LocacaoBuilder.umaLocacao().agora();
		ArgumentCaptor<Locacao> argCpt = ArgumentCaptor.forClass(Locacao.class);

		service.prorrogarDataLocacao(locacao, 3);

		verify(locacaoDAO).salvar(argCpt.capture());
		Locacao locacaoCapturada = argCpt.getValue();

		error.checkThat(locacaoCapturada.getValor(), is(15.0));
		error.checkThat(locacaoCapturada.getDataLocacao(), ehHoje());
		error.checkThat(locacaoCapturada.getDataRetorno(), ehHojeComDiferenca(3));
	}

	@Test
	public void naoDeveCalcularValor() throws Exception {

		//Se o metodo fosse privado no service
		PowerMockito.doReturn(1.0).when(service, "calculaValorTotal", filmes);

		Locacao locacao = service.alugarFilme(usuario, filmes);

		Assert.assertThat(locacao.getValor(), is(1.0));
		PowerMockito.verifyPrivate(service).invoke("calculaValorTotal", filmes);

	}

}
