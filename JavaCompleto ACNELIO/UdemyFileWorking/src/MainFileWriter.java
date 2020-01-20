import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MainFileWriter {

	public static void main(String[] args) {
		
		String[] lines = {"Hey", "Ho", "Let's Go"};
		
		String path = "c:\\temp\\out.txt";
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))){
			for (String string : lines) {
				bw.write(string);
				bw.newLine();
			}
		} catch (IOException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}
}
