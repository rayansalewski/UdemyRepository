package br.com.udemyFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

import br.com.udemyFile.entities.Balanco;
import br.com.udemyFile.entities.Produto;

public class Program {

	public static void main(String[] args) throws IOException {
		
		String primaryFolder = "C://javaFile";
				
		String sourcePath = "C://javaFile//source";
		String outputPath = "C://javaFile//output";
		
		File primaryPath = new File(primaryFolder);
		
		File srcPath = new File(sourcePath);
		File outPath = new File(outputPath);
		
		File srcFile = new File(srcPath + "//source.csv");
		File outFile = new File(outPath + "//output.csv");

		if (!primaryPath.exists()) {
			primaryPath.mkdir();
		}
		
		if (!srcPath.exists()) {
			srcPath.mkdir();
			srcFile.createNewFile();
		}
		
		if (!outPath.exists()) {
			outPath.mkdir();
			outFile.createNewFile();
		}
		
		if (!new File(srcPath + "//source.csv").exists()) {
			srcFile.createNewFile();
		}
		
		if (!new File(outPath + "//output.csv").exists()) {
			outFile.createNewFile();
		}
		
		BufferedReader br = null;
		BufferedWriter bw = null;
				
		try{
			
			br = new BufferedReader(new FileReader(srcFile));
			bw = new BufferedWriter(new FileWriter(outFile, true));
			
			String readLine = br.readLine();
			
			while(readLine != null) {
				String[] splitedLines = readLine.split(",");
				
				Produto produto = new Produto(splitedLines[0], BigDecimal.valueOf(Double.parseDouble(splitedLines[1])));
				Balanco balanco = new Balanco(produto, Integer.parseInt(splitedLines[2].trim()));
				
				bw.write(splitedLines[0] + ", " + String.format("%.2f",balanco.valorTotal(produto)));
				bw.newLine();
				
				readLine = br.readLine();
				
			}
		} catch(IOException e) {
			
		} finally {
			bw.close();
			br.close();
		}
		
	}
}
