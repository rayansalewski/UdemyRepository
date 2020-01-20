package br.com.udemyFile.entities;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Produto {

	private String name;
	private BigDecimal value;
	
}
