package algoutil;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.la4j.matrix.Matrix;
import org.la4j.matrix.sparse.CRSMatrix;
import org.la4j.vector.Vector;
import org.la4j.vector.sparse.CompressedVector;

public class Util {

	public static double[][] listofListToMatrix(List<List<Double>> list) {
		int lines = list.size();
		int columns = list.get(0).size();
		double[][] matrix = new double[lines][columns];
		for (int i = 0; i < lines; i++)
			for (int j = 0; j < columns; j++)
				matrix[i][j] = list.get(i).get(j);
		return matrix;
	}

	public static List<Double> parseStringToDouble(String[] arr) {
		List<Double> output = new ArrayList<Double>();
		for (String s : arr)
			output.add(Double.parseDouble(s));
		return output;
	}
	
	public static void printMatrix(int[][] matrix) {
		for (int[] line : matrix) {
			for (int e : line)
				System.out.print(e + " ");
			System.out.println();
		}
	}
	
	public static void printMatrix(double[][] matrix) {
		for (double[] line : matrix) {
			for (double e : line)
				System.out.printf("%.2f ", e);
			System.out.println();
		}
	}

	public static void printListOfDoubles(List<double[]> list) {
		for (double[] v : list) {
			for (double e : v)
				System.out.printf("%.2f ", e);
			System.out.println();
		}
	}

	public static List<double[]> checkArea(double[][] m, int i, int j) {
		int lines = m.length;// i
		int columns = m[0].length;// j
		List<double[]> area = new ArrayList<double[]>();

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
				area.add(new double[] { x, y });
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

	private static Function<String, List<Double>> splitDouble = line -> Util.parseStringToDouble(line.split("[\t ,;x]"));
	
	

	public static double[][] loadMatrixFromFile(String fileName) {
		List<List<Double>> matriz = new ArrayList<List<Double>>();
		Util.getLinesFromFile(fileName).stream().map(splitDouble).forEach(result -> matriz.add(result));
		return Util.listofListToMatrix(matriz);
	}

	//TODO add compatibility to windows
	public static void runInFirefox(String relativePath) {
		try {
			Runtime.getRuntime().exec("firefox "+ relativePath);
		}
		catch (IOException e) {
		}
	}
}
