package grafos;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Pratical Use: @link http://www.graph-magics.com/practic_use.php Tentarei
 * fazer o "shortest path"
 */

// TODO metodo equals
// TODO metodo clone deve fazer um "deepclone"
public class Graph {

	private List<Vertex> vertices;
	private List<Edge> edges;

	private boolean linked;

	public Graph() {
		vertices = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
		linked = false;
	}

	public void addVertex(Vertex newVertex) {
		if (linked) {
			// TODO
		} else {
			for (Vertex v : vertices)
				if (v.equals(newVertex)) {
					// debug
					System.out.printf("Vertex %s já existe.", newVertex);
					return;
				}
			vertices.add(newVertex);
		}
	}

	public void addEdge(Edge newEdge) {
		if (linked) {
			//TODO
		} 
		else {
			for (Edge e : edges) {
				if (e.equals(newEdge)) {
					// debug
					System.out.printf("Edge %s já existe.\n",newEdge);
					return;
				}
			}
		}

		edges.add(newEdge);
	}

	public void removeVertex(Vertex v) {
		if (linked) {
			// TODO mais complicado
		} else {
			// só preciso retirar todas as arestas que tenham v e o v
			for (Edge edge : edges)
				if (edge.contains(v))
					edges.remove(edge);
			vertices.remove(v);
		}
	}

	public void removeEdge(Edge edge) {
		if (linked) {
			// TODO mais complicado ainda :/
		} else {
			edges.remove(edge);
		}
	}

	// método que interliga os vértices
	public void link() {
		if (!linked) {
			for (Edge edge : edges) {
				edge.getA().add(edge.getB());
				edge.getB().add(edge.getA());
			}
			linked = true;
		}
	}

	public void unlink() {
		//TODO
	}

	public void printIdAdjacencyList() {

		String output = "";

		for (Vertex v : vertices) {
			// A,B,C..
			output += v.getId();

			if (v.siblings == null) {
				// no siblings for this one :(
				// XXX id 0 ambiguo
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
		// Imprime os vertices como A,B,C,D... de acordo com a ordem de seus
		// ids.
		// Funciona somente com a suposição de que todos os VERTEXES serão
		// inicializados antes de todas as EDGES, e o primeiro vertex tendo
		// id=0.

		String output = "";
		char vtx = 'A';

		for (Vertex v : vertices) {
			// A,B,C..
			output += vtx++;

			if (v.siblings == null) {
				// no siblings for this one :(
				output += " -> " + "0\n";
				continue;
			}

			for (Vertex va : v.siblings) {
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

	// -------------- getters and setters --------------------//

	public void setVertices(List<Vertex> vertices) {
		this.vertices = vertices;
	}

	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}

	public List<Vertex> getVertices() {
		return vertices;
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public Vertex getVertex(int index) {
		return vertices.get(index);

	}
	
	public boolean isLinked(){
		return linked;
	}

}
