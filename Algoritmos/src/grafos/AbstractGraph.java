package grafos;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGraph<Tv extends AbstractVertex<Tv>, Te extends AbstractEdge<Tv>> extends GraphElement {
	protected List<Tv> vertices;
	protected List<Te> edges;

	protected boolean linked;
	
	public AbstractGraph() {
		reset();	
	}	

	protected void reset(){
		vertices = new ArrayList<Tv>();
		edges = new ArrayList<Te>();
		linked = false;		
	}
	
	public abstract void addVertex(Tv v);
	public abstract void removeVertex(Tv v);
	public abstract void addEdge(Te e);
	public abstract void removeEdge(Te e);
	public void link() {
		if (!linked) {
			for (Te edge : edges) {
				edge.getA().add(edge.getB());
				edge.getB().add(edge.getA());
			}
			linked = true;
		}
	}
	public abstract void unlink();
	
	public abstract void loadFromAdjacencyMatrix(String fileName);
	
	public void printIdAdjacencyList() {

		String output = "";

		for (Tv v : vertices) {
			// A,B,C..
			output += v.getId();

			if (v.neighbors.size() == 0) {
				output += " -> " + "-\n";
				continue;
			}

			for (Tv va : v.neighbors) {
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

		for (Tv v : vertices) {
			// A,B,C..
			output += vtx++;

			if (v.neighbors.size() == 0) {
				// no neighbors for this one :(
				output += " -> " + "0\n";
				continue;
			}

			for (Tv va : v.neighbors) {
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
		return output;
	}
	
	
	public Tv getVertex(int index) {
		return vertices.get(index);
	}
	
	public List<Tv> getVertices() {
		return vertices;
	}

	public void setVertices(List<Tv> vertices) {
		this.vertices = vertices;
	}

	public List<Te> getEdges() {
		return edges;
	}

	public void setEdges(List<Te> edges) {
		this.edges = edges;
	}

	public boolean isLinked() {
		return linked;
	}

	public void setLinked(boolean linked) {
		this.linked = linked;
	}
	
}
