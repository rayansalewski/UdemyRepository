package br.com.udemy.interfaces.paymentservice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import br.com.udemy.interfaces.paymentservice.entities.Contract;
import br.com.udemy.interfaces.paymentservice.entities.Installments;
import br.com.udemy.interfaces.paymentservice.service.ContractService;
import br.com.udemy.interfaces.paymentservice.service.PaypalService;

public class Application {

	public static void main(String[] args) throws ParseException {
		
		Locale.setDefault(Locale.US);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter contract data");
		System.out.print("Number: ");
		
		long numberContract = sc.nextLong();
		
		System.out.print("Date (dd/MM/yyyy): ");
		sc.nextLine();
		String date = sc.nextLine();
		
		Date dateContract = new Date();
		
		dateContract = sdf.parse(date);
		
		System.out.print("Contract value: ");
		double amountTotal = sc.nextDouble();
		
		System.out.print("Enter number of installments: ");
		int installments = sc.nextInt();
		
		Contract contract = new Contract(numberContract, dateContract, amountTotal);
		
		ContractService contractService = new ContractService(new PaypalService());
		
		contractService.processContract(contract, installments);
		
		System.out.println("INSTALLMENTS: ");
		
		for (Installments installments2: contract.getInstallment()) {
			System.out.println(installments2.toString());
		}
		
		sc.close();
	}
}
