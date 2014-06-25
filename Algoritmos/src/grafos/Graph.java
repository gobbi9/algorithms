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
	 10x11
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

	
	public void printAsList() {
		String output ="";
		char vtx = 'A';
		
		for(Vertex v : vertices) {
			output += vtx;
			for (VertexAbstract va : v.siblings) {
				output += " -> " + va.toString();				
			}
			output += '\n';
			vtx++;			
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