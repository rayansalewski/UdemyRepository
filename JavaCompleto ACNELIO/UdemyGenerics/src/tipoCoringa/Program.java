package tipoCoringa;

import java.util.Arrays;
import java.util.List;

public class Program {
	
	public static void main(String[] args) {
		List<Integer> myInts = Arrays.asList(1,2,3);
		printLists(myInts);
		
		List<String> myStrings = Arrays.asList("Maria", "Pedro", "Jose");
		printLists(myStrings);
	}

	private static void printLists(List<?> list) {
		for (Object object : list) {
			System.out.println(object);
		}
		
	}
	
}
