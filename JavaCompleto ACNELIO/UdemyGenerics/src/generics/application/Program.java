package generics.application;

import java.util.Scanner;

import generics.service.PrintService;

public class Program {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		PrintService<Integer> ps = new PrintService<>();
		
		System.out.println("How many values?");
		int n = sc.nextInt();
		
		for (int i = 0; i < n; i++) {
			int value = sc.nextInt();
			ps.addValue(value);
		}
		
		ps.print();
		
		System.out.println("\nFirst: " + ps.first());
		
		sc.close();
	}
}
