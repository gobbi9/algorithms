package algoutil;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
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

	private Function<String, List<Integer>> splitInteger = line -> Util.parseStringToInteger(line.split("[\t ,;x]"));

	public int[][] getMatrix(){
		List<List<Integer>> matriz = new ArrayList<List<Integer>>();
		getLines().stream().map(splitInteger).forEach(result -> matriz.add(result));
		return Util.listofListToMatrix(matriz);
	}
	
}
