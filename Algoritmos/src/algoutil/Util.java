package algoutil;

import grafos.SimpleGraph;
import grafos.abstracts.AbstractEdge;
import grafos.abstracts.AbstractGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
	
	//TODO usar um nome melhor
	public static void toVis(AbstractGraph<?,?> g) throws FileNotFoundException {

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

		// criar a estrutura de texto do graph em dot language
		StringBuffer dotBuf = new StringBuffer();		
		
		dotBuf.append(HEAD);
		for (AbstractEdge<?> edge : g.getEdges()) {			
			dotBuf.append(edge.toHtml());
		}
		dotBuf.append(TAIL);
		
		
		Scanner scan = new Scanner(new File(TEMPL_PATH));
		// buffer para o arquivo final
		StringBuffer htmlBuf = new StringBuffer();
		String line;
		
		// substituir as flags do template pelo buf dot lang
		while (scan.hasNextLine()) {
			line = scan.nextLine();
			if (line.contains("$data")) {
				htmlBuf.append(dotBuf);
				continue;								
			}
			htmlBuf.append(line+'\n');			
		}		
		
		scan.close();		

		try {			
			Files.write(Paths.get("output/t.html"), htmlBuf.toString().getBytes());			
		}
		catch (IOException e) {			
			e.printStackTrace();
		}

		Util.runInFirefox("output/t.html");
		
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		
		SimpleGraph g = new SimpleGraph();
		g.loadFromSimpleInput("files/simpleinput.txt");
		toVis(g);				
			
	}
}
