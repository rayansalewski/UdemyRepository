package br.com.udemy.interfaces.service;

import br.com.udemy.interfaces.entities.CarRental;
import br.com.udemy.interfaces.entities.Invoice;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RentalService {

	public Double pricePerHour;
	public Double pricePerDay;

	public TaxService taxService;

	public void processInvoice(CarRental carRental) {
		long t1 = carRental.getStart().getTime();
		long t2 = carRental.getFinish().getTime();
		
		double hours = (double)(t2 - t1) / 1000 / 60 / 60;
		double basicPayment;
		
		if (hours <= 12) {
			basicPayment = Math.ceil(hours) * pricePerHour;
		}
		else {
			basicPayment = Math.ceil(hours/24) * pricePerDay;
		}
		
		double tax = taxService.tax(basicPayment);
		
		carRental.setInvoice(new Invoice(basicPayment, tax));
	}

}
