package set;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import genericsDelimitados.entities.Product;

public class TreeSetProgram {
	public static void main(String[] args) {
		
		Set<String> set = new TreeSet<>();
		set.add("Tv");
		set.add("Tablet");
		set.add("Notebook");
		
		System.out.println(set.contains("Notebook"));
		
		for (String p : set) {
			System.out.println(p);
		}
		
		Set<Integer> a = new TreeSet<>(Arrays.asList(0,2,4,5,6,8,10));
		Set<Integer> b = new TreeSet<>(Arrays.asList(5,6,7,8,9,10));
		//union
		Set<Integer> c = new TreeSet<>(a);
		c.addAll(b);
		System.out.println(c);
		//intersection
		Set<Integer> d = new TreeSet<>(a);
		d.retainAll(b);
		System.out.println(d);
		//difference
		Set<Integer> e = new TreeSet<>(a);
		e.removeAll(b);
		System.out.println(e);
		
		
		Set<Product> set2 = new TreeSet<>();
		set2.add(new Product("TV", 900.0));
		set2.add(new Product("Notebook", 1200.0));
		set2.add(new Product("Tablet", 400.0));
		Product prod = new Product("Notebook", 1200.0);
		System.out.println(set2.contains(prod));
	}
}
