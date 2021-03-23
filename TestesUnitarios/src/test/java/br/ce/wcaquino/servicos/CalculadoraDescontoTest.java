package br.ce.wcaquino.servicos;

import br.ce.wcaquino.daos.LocacaoDAO;
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class CalculadoraDescontoTest {

    private LocacaoService service;

    @Parameter
    public List<Filme> listFilme;

    @Parameter(value = 1)
    public Double valorLocacao;

    @Parameter(value = 2)
    public String cenario;

    @Before
    public void init() {
        //cenario
        this.service = new LocacaoService();
        service.setDao(Mockito.mock(LocacaoDAO.class));
        service.setSPCService(Mockito.mock(SPCService.class));
    }

    private static Filme filme1 = new Filme("Filme 1", 1, 5.0);
    private static Filme filme2 = new Filme("Filme 2", 1, 5.0);
    private static Filme filme3 = new Filme("Filme 3", 1, 5.0);
    private static Filme filme4 = new Filme("Filme 4", 1, 5.0);
    private static Filme filme5 = new Filme("Filme 5", 1, 5.0);
    private static Filme filme6 = new Filme("Filme 6", 1, 5.0);

    @Parameters(name = "{2}")
    public static Collection<Object[]> getParameters(){
        return Arrays.asList(new Object[][] {
                {Arrays.asList(filme1, filme2, filme3), 13.75, "25% de desconto no terceiro Filme"},
                {Arrays.asList(filme1, filme2, filme3, filme4), 16.25, "50% de desconto no quarto Filme"},
                {Arrays.asList(filme1, filme2, filme3, filme4, filme5), 17.50, "75% de desconto no quinto Filme"},
                {Arrays.asList(filme1, filme2, filme3, filme4, filme5, filme6), 17.50, "100% de desconto no sexto Filme"}
        });
    }

    @Test
    public void locacaoFilmeComDesconto() throws Exception {

        //acao
        Locacao locacao = service.alugarFilmeComDesconto(new Usuario("Usuario 1"), listFilme);

        //verificacao
        assertThat(locacao.getValor(), is(equalTo(valorLocacao)));
    }
}
