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
import org.powermock.reflect.Whitebox;

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
public class LocacaoServiceTest_PowerMock {

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
	public void naoDeveCalcularValor() throws Exception {

		//Se o metodo fosse privado no service
		PowerMockito.doReturn(1.0).when(service, "calculaValorTotal", filmes);

		Locacao locacao = service.alugarFilme(usuario, filmes);

		Assert.assertThat(locacao.getValor(), is(1.0));
		PowerMockito.verifyPrivate(service).invoke("calculaValorTotal", filmes);

	}

}
