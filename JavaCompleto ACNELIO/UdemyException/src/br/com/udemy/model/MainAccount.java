package br.com.udemy.model;

import java.util.Locale;
import java.util.Scanner;

import br.com.udemy.model.entities.Account;
import br.com.udemy.model.exceptions.AccountException;

public class MainAccount {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the account data: ");

		System.out.print("Number: ");
		int accountNumber = sc.nextInt();
		sc.nextLine();

		System.out.print("Holder: ");
		String holder = sc.nextLine();

		System.out.print("Initial Balance: ");
		double balance = sc.nextDouble();

		System.out.print("Withdraw limit: ");
		double withdrawLimit = sc.nextDouble();

		Account acc = new Account(accountNumber, holder, balance, withdrawLimit);

		System.out.print("\nEnter the amount of withdraw: ");

		Double withdrawAmount = sc.nextDouble();

		try {
			acc.withdraw(withdrawAmount);
			System.out.println("New balance: " + String.format("%.2f", acc.getBalance()));
		} catch (AccountException e) {
			System.out.println("Withdraw error: " + e.getMessage());
		}

		sc.close();
	}
}
