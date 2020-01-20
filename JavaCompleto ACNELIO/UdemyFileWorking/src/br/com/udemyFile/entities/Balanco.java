package br.com.udemyFile.entities;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Balanco {

	private Produto produto;
	private int quantidade;
	
	public BigDecimal valorTotal(Produto produto) {
		return produto.getValue().multiply(BigDecimal.valueOf(this.quantidade));
	}
	
	
}
