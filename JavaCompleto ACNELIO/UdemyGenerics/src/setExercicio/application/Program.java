package setExercicio.application;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Program {

	public static void main(String[] args) {

		Set<Integer> a = new HashSet<Integer>();
		Set<Integer> b = new HashSet<Integer>();
		Set<Integer> c = new HashSet<Integer>();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("How many students for course A?");
		int n = sc.nextInt();
		
		for (int i = 0; i < n; i++) {
			a.add(sc.nextInt());
		}
		
		System.out.println("How many students for course B?");
		n = sc.nextInt();
		
		for (int i = 0; i < n; i++) {
			b.add(sc.nextInt());
		}
		
		System.out.println("How many students for course C?");
		n = sc.nextInt();
		
		for (int i = 0; i < n; i++) {
			c.add(sc.nextInt());
		}
		
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		
		Set<Integer> total = new HashSet<Integer>(a);
		total.addAll(b);
		total.addAll(c);
		
		System.out.println("Total students: " + total.size());
		
		sc.close();
	}
}
