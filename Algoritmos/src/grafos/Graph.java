package grafos;

import java.util.ArrayList;
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
		vertices = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();		
	}
	
	public void addVertex(Vertex newVertex) {
		// verifica se já existe
		for (Vertex v : vertices)
			if (v.equals(newVertex)) {
				// debug
				System.out.printf("Vertex %s já existe.", newVertex.toString());
				return;
			}
		// adiciona
		vertices.add(newVertex);
	}	
	
	public void addEdge(Edge newEdge) {
		// inicializa
//		if (edges == null) 
//			edges = new ArrayList<Edge>();		

		// verifica se a edge já existe
		for (Edge e : edges) {
			if (e.equals(newEdge)) {
				// debug
				System.out.printf("Edge %s já existe.\n", newEdge.toString());
				return;				
			}
		}

//		 'linkar' somente quando a edge for efetivamente incluida no grafo.
//		 Da outra maneira, os vertexes eram associados como siblings na init da Egde. Se a 
//		 Edge fosse duplicada, ela nao seria add a este grafo por addEdge() -- mas ainda seria possivel
//		 navegar no grafo imaginario atraves dos siblings destes vertexes.

		//XXX terrible way
		newEdge.getA().add(newEdge.getB());
		newEdge.getB().add(newEdge.getA());

		// adiciona		
		edges.add(newEdge);		
	}
	
	public void printIdAdjacencyList() {
		
		String output ="";
		
		for(Vertex v : vertices) {
			// A,B,C..
			output += v.getId();						
			
			if (v.siblings == null) {
				// no siblings for this one :(
				//XXX id 0 ambiguo
				output += " -> " + "0\n";
				continue;
			}				
				
			for (Vertex va : v.siblings) {
				// caution: gambi ahead				
				output += " -> " + va.getId();
			}
			output += '\n';						
		}		
		
		System.out.println(output);		
	}
	
	public void printAdjacencyList() {
// 		Imprime os vertices como A,B,C,D... de acordo com a ordem de seus ids.
//		Funciona somente com a suposição de que todos os VERTEXES serão 
//		inicializados antes de todas as EDGES, e o primeiro vertex tendo id=0.
		
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
				
			for (Vertex va : v.siblings) {
				// converte o id numero em char			
				output += " -> " + (char)('A' + va.getId());
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
	
	
	public List<Vertex> getVertices(){
		return vertices;
	}
	
	public List<Edge> getEdges(){
		return edges;
	}
	
}
