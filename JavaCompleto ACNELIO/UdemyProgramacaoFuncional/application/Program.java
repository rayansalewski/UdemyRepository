package application;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import entities.Product;
import util.ProductService;

public class Program {
	public static void main(String[] args) {
		
		List<Product> list = new ArrayList<>();
		
		list.add(new Product("TV", 900.00));
		list.add(new Product("Notebook", 1200.00));
		list.add(new Product("Tablet", 450.00));
		list.add(new Product("HD", 80.00));
		list.add(new Product("Mouse", 45.00));
		
		System.out.println("Expressão Lambda");
		//Expressão lambda
		list.sort((p1, p2) -> p1.getName().toUpperCase().compareTo(p2.getName().toUpperCase()));
		
		list.forEach(System.out::println);
		
		System.out.println("\nConsumer");
		
		//Predicado ou Predicate
		//list.removeIf(p -> p.getValue() >= 100);
		
		//Consumer com reference methods
		//list.forEach(Product::priceUpdate);
		list.forEach(p -> p.setValue(p.getValue() * 1.1));
		
		list.forEach(System.out::println);
		
		System.out.println("\nPredicate");
		
		//Predicate com reference methods
		list.removeIf(Product::staticProductPredicate);
		
		list.forEach(System.out::println);
		
		System.out.println("\nFunction");
		
		List<String> names = list.stream().map(Product::upperCaseListProduct).collect(Collectors.toList());
		
		names.forEach(System.out::println);
		
		System.out.println("\nFunction with function");
		
		ProductService ps = new ProductService();
		
		double sum = ps.filtredSum(list, p -> p.getValue() <= 100);
		
		System.out.println("Sum: " + sum);

	}
}
