package grafos.abstracts;

import grafos.interfaces.VertexAction;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

import algoutil.Util;

public abstract class AbstractGraph<V extends AbstractVertex<V>, E extends AbstractEdge<V>>{
	protected List<V> vertices;
	protected List<E> edges;
	protected boolean linked;
	
	private int connectedComponents;
	protected Consumer<V> resetVisits = v -> v.setVisited(false);
	
	public AbstractGraph() {
		reset();	
	}	

	protected void reset(){
		vertices = new ArrayList<V>();
		edges = new ArrayList<E>();
		linked = false;
	}
	
	public void addVertex(V newVertex) {
		for (V v : vertices)
			if (v.equals(newVertex)) {
				// debug
				System.out.printf("Vertex %s já existe.", newVertex);
				return;
			}
		
		vertices.add(newVertex);
		linked = false;
	}

	public void addEdge(E newEdge) {
		for (E e : edges) {
			if (e.equals(newEdge)) {
				// debug
				System.out.printf("Edge %s já existe.\n", newEdge);
				return;
			}
		}
		
		if (!vertices.contains(newEdge.getA()))
			vertices.add(newEdge.getA());
		if (!vertices.contains(newEdge.getB()))
			vertices.add(newEdge.getB());
		
		if (linked){
			newEdge.getA().add(newEdge.getB());
			newEdge.getB().add(newEdge.getA());
		}
		
		edges.add(newEdge);

	}

	public void removeVertex(V v) {
		edges.removeIf(edge -> edge.contains(v));
		vertices.remove(v);

		if (linked) {
			vertices.forEach(vertex -> vertex.neighbors.remove(v));
		}
	}

	public void removeEdge(E e) {
		if (linked) {
			e.getB().neighbors.remove(e.getA());
			e.getA().neighbors.remove(e.getB());
		}
		edges.remove(e);
	}
	
	public void link() {
		if (!linked) {
			for (E edge : edges) {
				edge.getA().add(edge.getB());
				edge.getB().add(edge.getA());
			}
			linked = true;
		}
	}
	
	public void unlink() {
		vertices.forEach(v -> v.neighbors = new ArrayList<V>());
		linked = false;
	}
		
	public abstract void loadFromMatrix(String fileName);
	
	public abstract void loadFromMatrix(int[][] matrix);
	
	public int[][] getMatrix() {
		int size = vertices.size();
		int[][] matrix = new int[size][size];

		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				matrix[i][j] = 0;

		edges.forEach(edge -> {
			int i = vertices.indexOf(edge.getA());
			int j = vertices.indexOf(edge.getB());
			int w = edge.getWeight();
			matrix[i][j] = w;
			matrix[j][i] = w;
		});

		return matrix;
	}
	
	public void printIdAdjacencyList() {

		String output = "";

		for (V v : vertices) {
			// A,B,C..
			output += v.getId();

			if (v.neighbors.size() == 0) {
				output += " -> " + "-\n";
				continue;
			}

			for (V va : v.neighbors) {
				output += " -> " + va.getId();
			}
			output += '\n';
		}

		System.out.println(output);
	}
	
	public void printAdjacencyList() {
		// Imprime os vertices como A,B,C,D... de acordo com a ordem de seus
		// ids.
		// Funciona somente com a suposição de que todos os VERTEXES serão
		// inicializados antes de todas as EDGES, e o primeiro vertex tendo
		// id=0.

		String output = "";
		char vtx = 'A';

		for (V v : vertices) {
			// A,B,C..
			output += vtx++;

			if (v.neighbors.size() == 0) {
				// no neighbors for this one :(
				output += " -> " + "0\n";
				continue;
			}

			for (V va : v.neighbors) {
				// converte o id numero em char
				output += " -> " + (char) ('A' + va.getId());
			}
			output += '\n';
		}

		System.out.println(output);
	}
	
	public String verticesToString() {
		String output = "";
		output += "{";
		if (vertices.size() > 0) {
			output += vertices.get(0).toString();
			if (vertices.size() > 1)
				for (int i = 1; i < vertices.size(); i++)
					output += ", " + vertices.get(i).toString();
		}
		output += "}";
		return output;
	}

	public String edgesToString() {
		String output = "";
		output += "{";
		if (edges.size() > 0) {
			output += edges.get(0).toString();
			if (edges.size() > 1)
				for (int i = 1; i < edges.size(); i++)
					output += ", " + edges.get(i).toString();
		}
		output += "}";
		return output;
	}

	public String toString() {
		String output = "";
		output += verticesToString();
		output += ", ";
		output += edgesToString();
		output += "\nComponentes conexos: " + getConnectedComponents();
		return output;
	}
	private static int idPage = 0; 
	public void toHtml() throws FileNotFoundException {
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
		for (E edge :edges) {			
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
			if (line.contains("$title"))
				line = line.replace("$title", TITLE);
			
			htmlBuf.append(line+'\n');			
		}		
		
		scan.close();		

		String fileName = "output/o"+idPage+".html";
		idPage++;
		
		try {			
			Files.write(Paths.get(fileName), htmlBuf.toString().getBytes());			
		}
		catch (IOException e) {			
			e.printStackTrace();
		}

		Util.runInFirefox(fileName);
		
	}
	
	protected void countComponents(){
		resetVisits();
		connectedComponents = 0;
		vertices.forEach(v -> {
			if (!v.isVisited()){
				connectedComponents++;
				if (v.neighbors.size() == 0)
					v.setVisited(true);
				else
					visit(v);
			}
		});
		resetVisits();
	}
	
	public void invertEdges(){
		int[][] matrix = this.getMatrix(); 
		int size = matrix.length;
		int[][] transpose = new int[size][size];
		
		for (int i = 0; i<size; i++)
			for (int j = 0; j<size; j++)
				transpose[i][j] = matrix[j][i];
		
		this.loadFromMatrix(transpose);
		
	}
	public void visit(){
		if (vertices.size() > 0)
			visit(vertices.get(0), v -> System.out.println(v.getId()));
		else
			System.out.println("There are no vertices in this graph!!");
	}
	
	public void visit(VertexAction<V> a){
		if (vertices.size() > 0)
			visit(vertices.get(0), a);
	}

	private void visit(V origin){
		for (int i = 0; i < origin.getNeighbors().size(); i++) {
			while (!origin.getNeighbors().get(i).isVisited()) {
				origin.getNeighbors().get(i).setVisited(true);
				this.visit(origin.getNeighbors().get(i));
			}
		}
	}
		
	public void visit(V origin, VertexAction<V> a){
		for (int i = 0; i < origin.getNeighbors().size(); i++) {
			while (!origin.getNeighbors().get(i).isVisited()) {
				a.action(origin.getNeighbors().get(i));
				origin.getNeighbors().get(i).setVisited(true);
				this.visit(origin.getNeighbors().get(i), a);
			}
		}
	}
	
	public void resetVisits(){
		vertices.forEach(resetVisits);
	}
	
	
	public V getVertex(int index) {
		return vertices.get(index);
	}
	
	public List<V> getVertices() {
		return vertices;
	}

	public void setVertices(List<V> vertices) {
		this.vertices = vertices;
	}

	public List<E> getEdges() {
		return edges;
	}

	public void setEdges(List<E> edges) {
		this.edges = edges;
	}

	public boolean isLinked() {
		return linked;
	}

	public void setLinked(boolean linked) {
		this.linked = linked;
	}

	public boolean isConnected() {
		return connectedComponents == 1;
	}

	public int getConnectedComponents() {
		countComponents();
		return connectedComponents;
	}
	
}