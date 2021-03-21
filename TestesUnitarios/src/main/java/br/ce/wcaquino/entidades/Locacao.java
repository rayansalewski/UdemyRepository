package br.ce.wcaquino.entidades;

import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Locacao {

	private Usuario usuario;
	private List<Filme> filme;
	private Date dataLocacao;
	private Date dataRetorno;
	private Double valor;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Date getDataLocacao() {
		return dataLocacao;
	}
	public void setDataLocacao(Date dataLocacao) {
		this.dataLocacao = dataLocacao;
	}
	public Date getDataRetorno() {
		return dataRetorno;
	}
	public void setDataRetorno(Date dataRetorno) {
		this.dataRetorno = dataRetorno;
	}
	public Double getValor() {
		return valor;
	}
	private void setValor(Double valor) {
		this.valor = valor;
	}
	public List<Filme> getFilme() {return filme;}
	public void setFilme(List<Filme> filme) {this.filme = filme;}

	public void calculaValorTotal() {
		if(!getFilme().isEmpty()) {
			setValor(getFilme().stream().mapToDouble(Filme::getPrecoLocacao).sum());
		}

	}

	public void calculaValorTotalComDesconto() {
		HashMap<Integer, Double> descontos = new HashMap<>();
		descontos.put(3, 0.75);
		descontos.put(4, 0.5);
		descontos.put(5, 0.25);
		descontos.put(6, 0.0);

		if(!getFilme().isEmpty()) {
			double total = 0;
			for (int i = 0; i < getFilme().size(); i++){
				if(descontos.containsKey(i+1)){
					total += (getFilme().get(i).getPrecoLocacao() * descontos.get(i+1));
				} else {
					total += getFilme().get(i).getPrecoLocacao();
				}
			}

			setValor(total);
		}
	}

	public void isVerificaEstoqueFilme() throws FilmeSemEstoqueException {
		if(!getFilme().isEmpty()) {
			for (Filme filme : getFilme()) {
				if (filme.getEstoque() == 0) {
					throw new FilmeSemEstoqueException();
				}
			}
		}
	}
}