package grafos;

import java.util.Arrays;

import algoutil.Reader;
import algoutil.Reader1337;

public class Test {

	private static Graph g;
	private static int[][] input;

	public static void main(String... args) {

//		g = new Graph();
//		g.loadFromSimpleInput("files/simpleinput.txt");
//		g.link();
//		g.printIdAdjacencyList();
//		percorre(g.getVertices().get(0));
//		g.loadFromSimpleInput("files/simpleinput2.txt");
//		g.link();
//		g.printIdAdjacencyList();
//		percorre(g.getVertices().get(0));
		t5();
		
	}

	public static void tABC() {

		Vertex a = new Vertex(0, 0);
		Vertex b = new Vertex(0, 1);
		Vertex c = new Vertex(1, 0);

		g.setVertices(Arrays.asList(a, b, c));

		Edge e1 = new Edge(a, b);
		Edge e2 = new Edge(b, c);
		Edge e3 = new Edge(a, c);

		g.setEdges(Arrays.asList(e1, e2, e3));

		System.out.println(g.toString());

		// System.out.println("Soma dos graus:");
		// char ch = 'A';
		// for (Vertex v : g.vertices) {
		// System.out.println(ch +": " + v.getNeighborsCount());
		// ch++;
		// }

	}

	// testar equals do Edge
	public static void t3() {
		Vertex a = new Vertex(0, 0);
		Vertex b = new Vertex(0, 1);
		Vertex c = new Vertex(1, 0);

		Edge ab = new Edge(a, b);
		Edge ba = new Edge(b, a);
		Edge ac = new Edge(a, c);

		System.out.println(ab.equals(ba));
		System.out.println(ab.equals(ab));
		System.out.println(ac.equals(ba));
	}

	// TODO montar grafo a partir de uma matriz 4x4 de exemplo
	/*
	 * 0 -> nada 1 -> parede 2 -> inicio 3 -> fim
	 */
	public static void t4() {

		Reader reader = new Reader("files/input.txt");
		String[] dimensions = reader.iterator().next().split("x");
		
		int lines = Integer.parseInt(dimensions[0]);
		int columns = Integer.parseInt(dimensions[1]);
		
		input = new int[lines][columns];
		
		int i = 0;
		for (String line : reader){
			if (!line.contains("x")){
				String[] values = line.split("[\t ,;]");
				for (int j = 0; j<input[i].length; j++){
					input[i][j] = Integer.parseInt(values[j]);
				}
				i++;
			}
		}
		printMatrix(input);
	}
	
	//usando o Reader1337
	public static void t5() {

		Reader1337 reader = new Reader1337("files/input.txt");

		printMatrix(reader.getMatrix());
	}

	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++)
				System.out.print(matrix[i][j] + " ");
			System.out.println();
		}
	}

	public static void tABCDE() {

		Vertex a = new Vertex(0, 0);
		Vertex b = new Vertex(0, 1);
		Vertex c = new Vertex(2, 0);
		Vertex d = new Vertex(2, 2);
		Vertex e = new Vertex(1, 1);

		g.setVertices(Arrays.asList(a, b, c, d, e));

		Edge e1 = new Edge(a, b);
		Edge e2 = new Edge(b, d);
		Edge e3 = new Edge(d, c);
		Edge e4 = new Edge(c, a);
		Edge e5 = new Edge(d, e);

		g.setEdges(Arrays.asList(e1, e2, e3, e4, e5));

		System.out.println(g.toString());
		g.printAdjacencyList();

	}

	
	//tentativa de função que percorre o Grafo. V1.0
	//ela deve imprimir todos os nós sem repetição
	//aparentemente funciona
	public static void percorre (Vertex origem){//tem que funcionar para qualquer vértice de origem
		for (int i = 0; i<origem.getNeighbors().size(); i++){
			while (!origem.getNeighbors().get(i).isVisited()){
				System.out.println(origem.getNeighbors().get(i).getId());
				origem.getNeighbors().get(i).setVisited(true);
				percorre(origem.getNeighbors().get(i));
			}	
			
		}
	}

}
