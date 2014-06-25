package grafos;

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
	
	//adicionar metodo addVertex que deve verificar se o vertex jah existe 
	//adicionar metodo addEdge que deve verificar se o edge jah existe
	public Graph() {
		
	}
	
	
	
	
	public void printAjacencyList() {
		// Funciona somente com a suposição de que todos os VERTEXES serão 
		// inicializados antes de todas as EDGES !!
		String output ="";
		char vtx = 'A';
		
		for(Vertex v : vertices) {
			// A,B,C..
			output += vtx++;						
			
			if (v.siblings == null) {
				// no siblings for this one :(
				output += " -> " + "0\n";
				continue;
			}				
				
			for (VertexAbstract va : v.siblings) {
				// caution: gambi ahead				
				output += " -> " + (char)('A' + va.getId() -1);
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


	
	// -------------- getters and setters --------------------//
	
	public void setVertices(List<Vertex> vertices) {
		this.vertices = vertices;
	}


	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}	
	
	
	
	
	
	
}
