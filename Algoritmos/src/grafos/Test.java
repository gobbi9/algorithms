package grafos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Test {
	
	private static Graph g;
	private static int[][] input;
	
	public static void main(String... args) {
		
		g = new Graph();
//		g.teste2();
//		g.tABC();
//		g.tABCDE();
		tReadFromFile();
//		System.out.println(g);
	}

	public static void tABC() {

		Vertex a = new Vertex(0,0);
		Vertex b = new Vertex(0,1);
		Vertex c = new Vertex(1,0);

		g.setVertices(Arrays.asList(a, b, c));

		Edge e1 = new Edge(a,b);
		Edge e2 = new Edge(b,c);
		Edge e3 = new Edge(a,c);

		g.setEdges(Arrays.asList(e1,e2,e3));

		System.out.println(g.toString());		
	
//		System.out.println("Soma dos graus:");
//		char ch = 'A';
//		for (Vertex v : g.vertices) {
//			System.out.println(ch +": " + v.getSiblingsCount());
//			ch++;
//		}

	}

	//testar equals do Edge
	public static void t3(){
		Vertex a = new Vertex(0,0);
		Vertex b = new Vertex(0,1);
		Vertex c = new Vertex(1,0);

		Edge ab = new Edge(a,b);
		Edge ba = new Edge(b,a);
		Edge ac = new Edge(a,c);

		System.out.println(ab.equals(ba));
		System.out.println(ab.equals(ab));
		System.out.println(ac.equals(ba));
	}

	//TODO montar grafo a partir de uma matriz 4x4 de exemplo 
	/*
	 * 0 -> nada
	 * 1 -> parede
	 * 2 -> inicio
	 * 3 -> fim
	 */
	public static void t4(){
		
		input = new int[][]{{0,0,2,1},{0,0,1,1},{0,0,0,0},{0,0,1,3}};
		printMatrix(input);


	}

	public static void printMatrix(int[][] matrix){
		for (int i = 0; i<input.length; i++){
			for (int j = 0; j<input[i].length; j++)
				System.out.print(input[i][j]+" ");
			System.out.println();
		}
	}

	public static void tABCDE() {

		Vertex a = new Vertex(0,0);
		Vertex b = new Vertex(0,1);
		Vertex c = new Vertex(2,0);
		Vertex d = new Vertex(2,2);
		Vertex e = new Vertex(1,1);

		g.setVertices(Arrays.asList(a, b, c, d, e));

		Edge e1 = new Edge(a,b);
		Edge e2 = new Edge(b,d);
		Edge e3 = new Edge(d,c);
		Edge e4 = new Edge(c,a);
		Edge e5 = new Edge(d,e);

		g.setEdges(Arrays.asList(e1,e2,e3,e4,e5));

		System.out.println(g.toString());
		g.printAdjacencyList();

	}	

	public static void tReadFromFile() {
		// Le um arquivo no formato:
		// V E		(numero de vertexes/numero de edges)
		// V1 V2	(edge1 = (V1,V2)) 	
		// Vn Vm	(edge2 = (Vn,Vm))
		// ... etc	('E' vezes)

		File input;
		Scanner scan = null;

		int iVertex, iEdges;

		try {
			// carregar o arquivo de entrada
			input = new File("files/simpleinput.txt");
			scan = new Scanner(input);

		} catch (FileNotFoundException e) {			
			e.printStackTrace();
			System.exit(-1);
		} 		

		// ler o numero de v/e 
		iVertex = scan.nextInt();
		iEdges = scan.nextInt();

		// inicializar todos os vertexes
		List<Vertex> vertices = new ArrayList<Vertex>();
		
		for (int i = 0; i < iVertex; i++) {
			// todas em i,0 por enquanto
			vertices.add(new Vertex(i, 0));			
		}
		// adicionar ao graph
		g.setVertices(vertices);		

		
		// indices de dois vertices A e B
		int v1, v2;
		// inicializar as edges
//		List<Edge> edges = new ArrayList<Edge>();				

		for (int i = 0; i < iEdges; i++) {
			// recebe os indices dos vertexes v1 e v2
			v1 = scan.nextInt();
			v2 = scan.nextInt();
			// inclui a edge
			Vertex a = vertices.get(v1);
			Vertex b = vertices.get(v2);
//			edges.add(new Edge(a,b));
			// teste addEdge
			g.addEdge(new Edge(a,b));
		}
		// adicionar ao graph
//		g.setEdges(edges);
		
		// encerra o scanner
		scan.close();
		// imprime
		System.out.println("Lido do arquivo:");
		g.printAdjacencyList();	

	}

}
