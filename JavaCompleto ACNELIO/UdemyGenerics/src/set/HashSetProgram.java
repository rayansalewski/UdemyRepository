package set;

import java.util.HashSet;
import java.util.Set;

import genericsDelimitados.entities.Product;

public class HashSetProgram {
	
	public static void main(String[] args) {
		
		Set<String> set = new HashSet<>();
		set.add("TV");
		set.add("Tablet");
		set.add("Notebook");
		
		System.out.println(set.contains("Notebook"));
		
		for (String p : set) {
			System.out.println(p);
		}
		
		
		Set<Product> set2 = new HashSet<>();
		set2.add(new Product("TV", 900.0));
		set2.add(new Product("Notebook", 1200.0));
		set2.add(new Product("Tablet", 400.0));
		Product prod = new Product("Notebook", 1200.0);
		System.out.println(set2.contains(prod));
	}
}
