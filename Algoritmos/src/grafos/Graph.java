package grafos;

import java.util.Arrays;
import java.util.List;

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
	
/*
	public void teste() {
		Vertex a = new Vertex("a");
		Vertex b = new Vertex("b");
		Vertex c = new Vertex("c");
		Vertex d = new Vertex("d");
		Vertex e = new Vertex("e");

		vertices = Arrays.asList(a, b, c, d, e);
		
		Edge e1 = new Edge(a,b);
		Edge e2 = new Edge(a,c);
		Edge e3 = new Edge(a,e);
		Edge e4 = new Edge(b,c);
		Edge e5 = new Edge(c,d);
		Edge e6 = new Edge(e,d);
		
		edges = Arrays.asList(e1,e2,e3,e4,e5,e6);
		
	}
	
	public void teste2(){
		Vertex a = new Vertex();
		Vertex b = new Vertex();
		Vertex c = new Vertex();
		Vertex d = new Vertex();
		Vertex e = new Vertex();
		
		a.setSiblings(Arrays.asList(b,c,e));
		b.setSiblings(Arrays.asList(a,c));
		c.setSiblings(Arrays.asList(b,a,d));
		d.setSiblings(Arrays.asList(b,c,e));
		e.setSiblings(Arrays.asList(a,d));
		
		System.out.println(a.get(d));
		System.out.println(a.get(b).get(c).get(d).get(e).siblingsToString());
		System.out.println(a.siblingsToString());
		
	}
	*/
	
	
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