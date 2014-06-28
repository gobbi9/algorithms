package algoutil;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Reader1337 {

	private Stream<String> lines;
	private Path path;
	
	public Reader1337(String fileName){
		path = FileSystems.getDefault().getPath(fileName);
		try {
			lines = Files.lines(path);
			k = 0;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Stream<String> getStream(){
		return lines;
	}
	
	public List<String> getLines(){
		try{
			return lines.collect(Collectors.toList());
		}
		catch(IllegalStateException e){
			try {
				return Files.lines(path).collect(Collectors.toList());
			} 
			catch (IOException e1) {
				return null;
			}
		}
	}

	private int[] parseStringToInteger(String[] arr){
		int[] output = new int[arr.length];
		for (int i = 0; i < arr.length; i++)
			output[i] = Integer.parseInt(arr[i]);
		return output;
			
	}
	
	private Function<String, int[]> splitInteger = line -> parseStringToInteger(line.split("[\t ,;x]"));
	
	private int[][] m;
	
	private int k = 0;
	
	private void f(int c){
		m[k / m[0].length][k % m[0].length] = c - 48;
		k++;
	}
	 
	public int[][] getMatrix(){
		int[]lc = getLines().stream().findFirst().map(splitInteger).get();
		m = new int[lc[0]][lc[1]];
		getLines().stream().skip(1).forEach(line -> line.chars().filter(c -> c != 9 && c != 32).forEach(c -> f(c)));
		return m;
	}
	
}
