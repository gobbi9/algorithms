package grafos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author guilherme
 * Pratical Use: @link http://www.graph-magics.com/practic_use.php
 * Tentarei fazer o "shortest path"
 */

/*Exemplo de entrada
	11x10
	0	0	0	0	0	0	0	0	A	0
	0	0	0	0	0	0	0	0	0	0
	0	0	0	0	0	1	1	1	1	1
	0	0	0	0	0	0	0	0	0	0
	1	0	1	1	1	1	1	0	1	1
	1	0	1	1	1	1	0	0	1	1
	0	0	0	1	0	0	0	0	1	1
	0	0	0	0	0	0	1	0	1	1
	1	0	1	0	1	0	1	0	1	1
	0	0	0	0	1	0	1	0	1	1
	B	0	1	0	0	0	0	0	1	1

	0 -> vazio		A -> origem
	1 -> parede		B -> destino 
 */

//TODO metodo equals
//TODO metodo clone deve fazer um "deepclone"
public class Graph {

	private List<Vertex> vertices;
	private List<Edge> edges;
	private int[][] input;
	//adicionar metodo addVertex que deve verificar se o vertex jah existe 
	//adicionar metodo addEdge que deve verificar se o edge jah existe
	public Graph() {
		
	}
	
	public void tABC() {
		
		Vertex a = new Vertex(0,0);
		Vertex b = new Vertex(0,1);
		Vertex c = new Vertex(1,0);
		
		vertices = Arrays.asList(a, b, c);
		
		Edge e1 = new Edge(a,b);
		Edge e2 = new Edge(b,c);
		Edge e3 = new Edge(a,c);
		
		edges = Arrays.asList(e1,e2,e3);
		
		System.out.println(toString());		
		System.out.println("Soma dos graus:");
		char ch = 'A';
		for (Vertex v : vertices) {
			System.out.println(ch +": " + v.getSiblingsCount());
			ch++;
		}
		
	}
	
	//testar equals do Edge
	public void t3(){
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
	public void t4(){
		input = new int[][]{{0,0,2,1},{0,0,1,1},{0,0,0,0},{0,0,1,3}};
		printMatrix(input);
		
		
	}
	
	public void printMatrix(int[][] matrix){
		for (int i = 0; i<input.length; i++){
			for (int j = 0; j<input[i].length; j++)
				System.out.print(input[i][j]+" ");
			System.out.println();
		}
	}
	
	public void tABCDE() {
		
		Vertex a = new Vertex(0,0);
		Vertex b = new Vertex(0,1);
		Vertex c = new Vertex(2,0);
		Vertex d = new Vertex(2,2);
		Vertex e = new Vertex(1,1);
		
		vertices = Arrays.asList(a, b, c, d, e);
		
		
		Edge e1 = new Edge(a,b);
		Edge e2 = new Edge(b,d);
		Edge e3 = new Edge(d,c);
		Edge e4 = new Edge(c,a);
		Edge e5 = new Edge(d,e);
		
		edges = Arrays.asList(e1,e2,e3,e4,e5);
		
		System.out.println(toString());
		printAsList();
		
	}	
	
   public void tReadFromFile() {
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
		vertices = new ArrayList<Vertex>();
		for (int i = 0; i < iVertex; i++) {
			// todas em 0,0 por enquanto
			vertices.add(new Vertex(0, 0));			
		}
		
		// inicializar as edges
		int v1, v2;
		edges = new ArrayList<Edge>();
		
		for (int i = 0; i < iEdges; i++) {
			// recebe os indices dos vertexes v1 e v2
			v1 = scan.nextInt();
			v2 = scan.nextInt();
			// inclui a edge
			Vertex a = vertices.get(v1);
			Vertex b = vertices.get(v2);
			edges.add(new Edge(a,b));
			
		}
		// encerra o scanner
		scan.close();
		// imprime
		System.out.println("Lido do arquivo:");
		printAsList();	
		
	}
	
	

	
	public void printAsList() {
		String output ="";
		char vtx = 'A';
		
		for(Vertex v : vertices) {
			output += vtx;
			vtx++;			
			
			if (v.siblings == null) {
				// no siblings for this one :(
				output += " -> " + "0\n";
				continue;
			}				
				
			for (VertexAbstract va : v.siblings) {
				// caution: gambi ahead				
				output += " -> " + (char)('A' + (Integer.parseInt(va.toString()) -1));				
			}
			output += '\n';						
		}		
		
		System.out.println(output);		
	}
	
	
	
	public String verticesToString(){
		String output = "";
		output += "{";
		if (vertices.size() > 0){
			output += vertices.get(0).toString();
			if (vertices.size() > 1)
				for (int i = 1; i<vertices.size(); i++)
					output += ", " + vertices.get(i).toString();
		}
		output += "}";
		return output;
	}
	
	public String edgesToString(){
		String output = "";
		output += "{";
		if (edges.size() > 0){
			output += edges.get(0).toString();
			if (edges.size() > 1)
				for (int i = 1; i<edges.size(); i++)
					output += ", " + edges.get(i).toString();
		}
		output += "}";
		return output;
	}
	
	public String toString(){
		String output = "";
		output += verticesToString();		
		output += ", ";
		output += edgesToString();
		return output;
	}	
}
