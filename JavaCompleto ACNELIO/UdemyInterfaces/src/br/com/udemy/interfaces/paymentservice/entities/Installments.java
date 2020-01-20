package br.com.udemy.interfaces.paymentservice.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
public class Installments {

	public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private Date dueDate;
	private Double amount;

	public Installments(Date dueDate, double amount) {
		this.dueDate = dueDate;
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return sdf.format(dueDate) + " - " + String.format("%.2f", amount);
	}
}
