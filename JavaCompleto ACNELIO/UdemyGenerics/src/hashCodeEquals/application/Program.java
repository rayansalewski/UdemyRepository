package hashCodeEquals.application;

import hashCodeEquals.entities.Client;

public class Program {

	public static void main(String[] args) {
		Client c1 = new Client("Rayan Salewski", "rayansalewski@hotmail.com");
		Client c2 = new Client("Caroline Shimada", "caroline_shimada@hotmail.com");
		Client c3 = new Client("Rayan Salewski", "rayansalewski@hotmail.com");
		
		System.out.println(c1.hashCode());
		System.out.println(c2.hashCode());
		System.out.println(c3.hashCode());
		
		System.out.println(c1.equals(c2));
		System.out.println(c2.equals(c3));
		System.out.println(c3.equals(c1));
		
	}
}
