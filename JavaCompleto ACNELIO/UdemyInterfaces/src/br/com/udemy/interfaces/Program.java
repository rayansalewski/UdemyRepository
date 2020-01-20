package br.com.udemy.interfaces;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Scanner;

import br.com.udemy.interfaces.entities.CarRental;
import br.com.udemy.interfaces.entities.Vehicle;
import br.com.udemy.interfaces.service.BrazilTaxService;
import br.com.udemy.interfaces.service.RentalService;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Locale.setDefault(Locale.US);
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter rental date:");
		System.out.print("Car model: ");
		
		String modelCar = sc.nextLine();
		
		System.out.print("Pickup (dd/MM/yyyy hh:mm): ");
		String startDate = sc.nextLine();
		
		System.out.print("Return (dd/MM/yyyy hh:mm): ");
		String returnDate = sc.nextLine();
		
		System.out.print("Enter price per hour: ");
		Double pricePerHour = sc.nextDouble();
		
		System.out.print("Enter price per day: ");
		Double pricePerDay = sc.nextDouble();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Vehicle vehicle = new Vehicle(modelCar);
		CarRental carRental = new CarRental(sdf.parse(startDate), sdf.parse(returnDate), vehicle);
		
		RentalService rentalService = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());
		
		rentalService.processInvoice(carRental);
		
		System.out.println("INVOICE: ");
		System.out.println("Basic payment: " + carRental.getInvoice().getBasicPayment());
		System.out.println("Tax: " + carRental.getInvoice().getTax());
		System.out.println("Total Payment: " + carRental.getInvoice().getTotalPayment());
		
		
		sc.close();
	}
}
