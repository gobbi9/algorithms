package algoutil;

import java.util.ArrayList;
import java.util.List;

public class Util {
	
	public static int[][] listofListToMatrix(List<List<Integer>> list){
		int lines = list.size();
		int columns = list.get(0).size();
		int[][] matrix = new int[lines][columns];
		for (int i = 0; i < lines; i++)
			for (int j = 0; j < columns; j++)
				matrix[i][j] = list.get(i).get(j);
		return matrix;
	}
	
	public static List<Integer> parseStringToInteger(String[] arr){
		List<Integer> output = new ArrayList<Integer>();
		for (int i = 0; i < arr.length; i++)
			output.add(Integer.parseInt(arr[i]));
		return output;			
	}
	
	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++)
				System.out.print(matrix[i][j] + " ");
			System.out.println();
		}
	}
	
	public static void printListOfInts(List<int[]> list){
		for (int[] v : list){
			for (int i = 0; i<v.length; i++)
				System.out.print(v[i]+" ");
			System.out.println();
		}
	}
}
