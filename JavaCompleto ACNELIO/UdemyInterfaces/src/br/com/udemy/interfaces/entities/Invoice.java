package br.com.udemy.interfaces.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Invoice {
	private Double basicPayment;
	private Double tax;

	public Double getTotalPayment() {
		return getBasicPayment() + getTax();
	}
}
