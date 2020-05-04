package mapExercise.application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Program {

	public static void main(String[] args) {

		Map<String, Integer> votes = new HashMap<String, Integer>();

		String path = System.getProperty("user.dir") + "/candidates.txt";

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();

			while (line != null) {
				String[] fields = line.split(",");
				String name = fields[0];
				Integer nVotes = (votes.get(name) == null) ? Integer.parseInt(fields[1])
						: (votes.get(name) + Integer.parseInt(fields[1]));

				line = br.readLine();

				votes.put(name, nVotes);
			}

			for (String name : votes.keySet()) {
				System.out.println(name + ": " + votes.get(name));
			}

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
