package algoutil;

import grafos.Edge;
import grafos.SimpleGraph;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.la4j.matrix.Matrix;
import org.la4j.matrix.sparse.CRSMatrix;
import org.la4j.vector.Vector;
import org.la4j.vector.sparse.CompressedVector;

public class Util {

	public static int[][] listofListToMatrix(List<List<Integer>> list) {
		int lines = list.size();
		int columns = list.get(0).size();
		int[][] matrix = new int[lines][columns];
		for (int i = 0; i < lines; i++)
			for (int j = 0; j < columns; j++)
				matrix[i][j] = list.get(i).get(j);
		return matrix;
	}

	public static List<Integer> parseStringToInteger(String[] arr) {
		List<Integer> output = new ArrayList<Integer>();
		for (String s : arr)
			output.add(Integer.parseInt(s));
		return output;
	}

	public static void printMatrix(int[][] matrix) {
		for (int[] line : matrix) {
			for (int e : line)
				System.out.print(e + " ");
			System.out.println();
		}
	}

	public static void printListOfInts(List<int[]> list) {
		for (int[] v : list) {
			for (int e : v)
				System.out.print(e + " ");
			System.out.println();
		}
	}

	public static List<int[]> checkArea(int[][] m, int i, int j) {
		int lines = m.length;// i
		int columns = m[0].length;// j
		List<int[]> area = new ArrayList<int[]>();

		double angle = Math.PI / 4;
		Matrix rotate = new CRSMatrix(new double[][] { { Math.cos(angle), -Math.sin(angle) },
				{ Math.sin(angle), Math.cos(angle) } });

		Vector u = new CompressedVector(new double[] { 1, 0 });
		Vector s = new CompressedVector(new double[] { i, j });

		Vector r;
		for (int k = 0; k < 8; k++) {
			u = rotate.multiply(u);
			r = u.add(s).transform((l, v) -> Math.round(v));
			int x = (int) r.get(0);
			int y = (int) r.get(1);
			if (x >= 0 && x < lines && y >= 0 && y < columns)
				area.add(new int[] { x, y });
		}

		return area;
	}

	@Deprecated
	public static List<int[]> oldCheckArea(int[][] m, int i, int j) {
		int lines = m.length;// i
		int columns = m[0].length;// j
		List<int[]> area = new ArrayList<int[]>();

		if (i - 1 >= 0 && j - 1 >= 0)
			area.add(new int[] { i - 1, j - 1 });

		if (i - 1 >= 0)
			area.add(new int[] { i - 1, j });

		if (i - 1 >= 0 && j + 1 < columns)
			area.add(new int[] { i - 1, j + 1 });

		if (j - 1 >= 0)
			area.add(new int[] { i, j - 1 });

		if (j + 1 < columns)
			area.add(new int[] { i, j + 1 });

		if (i + 1 < lines && j - 1 >= 0)
			area.add(new int[] { i + 1, j - 1 });

		if (i + 1 < lines)
			area.add(new int[] { i + 1, j });

		if (i + 1 < lines && j + 1 < columns)
			area.add(new int[] { i + 1, j + 1 });

		return area;
	}

	public static List<String> getLinesFromFile(String fileName) {
		try {
			return Files.lines(FileSystems.getDefault().getPath(fileName)).collect(Collectors.toList());
		}
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static Function<String, List<Integer>> splitInteger = line -> Util.parseStringToInteger(line.split("[\t ,;x]"));

	public static int[][] loadMatrixFromFile(String fileName) {
		List<List<Integer>> matriz = new ArrayList<List<Integer>>();
		Util.getLinesFromFile(fileName).stream().map(splitInteger).forEach(result -> matriz.add(result));
		return Util.listofListToMatrix(matriz);
	}

	public static void runInFirefox(String relativePath) {
		try {
			Runtime.getRuntime().exec("firefox "+ relativePath);
		}
		catch (IOException e) {
		}
	}
	
	//TODO nome melhor
	public static void toVis(SimpleGraph g) {

		// cabeçalho
		final String HEAD = "digraph {\n"
				+ "node [shape=circle fontSize=16]\n"
				+ "edge [length=100, color=gray, fontColor=black]\n";
		// rodapé -- vazio por enquanto
		final String TAIL = "}\n";
		
		// titulo da pagina - eyecandy
		final String TITLE = "Hello Simple Graph!";
		// arquivo de template 
		final String TEMPL_PATH = "files/template.html";

		
		StringBuffer gbuf = new StringBuffer();
		gbuf.append(HEAD);
		for (Edge edge : g.getEdges()) {
			// formato não direcionado por enquanto
			String s = String.format("%c -- %c;\n", 
					edge.getA().toChar(), edge.getB().toChar());	
			gbuf.append(s);
		}
		gbuf.append(TAIL);


		// carregar o template da página: ex14 - dot language
		List<String> lines = Util.getLinesFromFile(TEMPL_PATH);
		
		// substituir as flags no arquivo pela struct do graph
		for (int i = 0; i<lines.size(); i++){
			String line = lines.get(i);
			if (line.contains("$title")){
				lines.set(i, line.replace("$title", TITLE));				
			} else if (line.contains("$data")) {
				lines.set(i, line.replace("$data", gbuf.toString()));
			}
		}
		// debug
		lines.forEach(System.out::println);

		//TODO transformar em funcao
		StringBuffer fileBuffer = new StringBuffer();
		for (String line : lines){
			fileBuffer.append(line);
			fileBuffer.append('\n');
		}

		try {
			// TODO melhorar para caso o diretório não existir.
			// acho melhor incluir o diretório no projeto, mas dizer para o git
			// ignorar o conteúdo
			Files.write(Paths.get("output/t.html"), fileBuffer.toString().getBytes());			
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Util.runInFirefox("output/t.html");
		
	}
	
	public static void main(String[] args) {
		
		
		// 
		SimpleGraph g = new SimpleGraph();
		g.loadFromSimpleInput("files/simpleinput.txt");
		toVis(g);		
		
		
		if (true) return;
		
		String filePath = "files/template.html";
		List<String> lines = Util.getLinesFromFile(filePath);
		
		//TODO deixar buffer de template sempre carregado em Util 
		StringBuffer buffer = new StringBuffer();
		for (String lineData : Util.getLinesFromFile("files/data.txt")){
			buffer.append(lineData);
			buffer.append('\n');
		}
		
		for (int i = 0; i<lines.size(); i++){
			String line = lines.get(i);
			if (line.contains("$title")){
				lines.set(i, line.replace("$title", "My title"));				
			} else if (line.contains("$data")) {
				lines.set(i, line.replace("$data", buffer.toString()));
			}
		}
		// debug
		lines.forEach(System.out::println);
		
		//TODO transformar em funcao
		buffer = new StringBuffer();
		for (String line : lines){
			buffer.append(line);
			buffer.append('\n');
		}
		
		try {
			// TODO melhorar para caso o diretório não existir.
			// acho melhor incluir o diretório no projeto, mas dizer para o git
			// ignorar o conteúdo
			Files.write(Paths.get("output/t.html"), buffer.toString().getBytes());			
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Util.runInFirefox("output/t.html");
			
	}
}
