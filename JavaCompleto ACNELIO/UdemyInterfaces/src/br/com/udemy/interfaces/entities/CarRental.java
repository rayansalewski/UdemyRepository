package br.com.udemy.interfaces.entities;

import java.util.Date;

import lombok.Data;

@Data
public class CarRental {

	private Date start;
	private Date finish;
	
	private Vehicle vehicle;
	private Invoice invoice;
	
	public CarRental(Date start, Date finish, Vehicle vehicle) {
		this.start = start;
		this.finish = finish;
		this.vehicle = vehicle;
	}	
}
