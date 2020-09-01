package application;

import java.util.ArrayList;
import java.util.List;

import entities.Product;

public class Program {
	public static void main(String[] args) {
		
		List<Product> list = new ArrayList<>();
		
		list.add(new Product("TV", 900.00));
		list.add(new Product("Notebook", 1200.00));
		list.add(new Product("Tablet", 450.00));
		list.add(new Product("HD", 80.00));
		list.add(new Product("Mouse", 45.00));
		
		System.out.println("Express�o Lambda");
		//Express�o lambda
		list.sort((p1, p2) -> p1.getName().toUpperCase().compareTo(p2.getName().toUpperCase()));
		
		list.forEach(System.out::println);
		
		
		System.out.println("\nPredicate");
		//Predicado ou Predicate
		list.removeIf(p -> p.getValue() >= 100);
		
		list.forEach(System.out::println);
	}
}
