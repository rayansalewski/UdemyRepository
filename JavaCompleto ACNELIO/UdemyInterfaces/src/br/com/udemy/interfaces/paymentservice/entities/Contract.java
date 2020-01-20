package br.com.udemy.interfaces.paymentservice.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Contract {

	private Long number;
	private Date date;
	private Double totalValue;
	private List<Installments> installment = new ArrayList<>();
	
	public Contract(Long number, Date date, Double totalValue) {
		this.number = number;
		this.date = date;
		this.totalValue = totalValue;
	}
	
	public void addInstallment (Installments installment) {
		this.installment.add(installment);
	}
	
}
