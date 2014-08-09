package graphs;

import graphs.abstracts.AbstractGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import algoutil.Index;
import algoutil.Util;

public class DirectedGraph extends AbstractGraph<Vertex, DirectedEdge> {

	public DirectedGraph() {
		super();
	}

	public boolean containsUniversalSink() {
		boolean result = true;
		double[][] m = getMatrix();
		int size = m.length;
		int index = -1;
		for (int i = 0; i < size; i++) {
			result = true;
			for (int j = 0; j < size; j++) {
				result &= (m[i][j] == 0);
				if (!result)
					break;
			}
			if (result)
				index = i;
		}
		if (index == -1)
			return false;

		result = true;
		for (int i = 0; i < size; i++)
			if (i != index) {
				result &= (m[i][index] != 0);
				if (!result)
					return false;
			}

		return result;
	}

	public boolean containsUniversalSinkUsingAdjacencyList() {
		if (!linked)
			link();
		Vertex vertex;
		try {
			vertex = vertices.stream().filter(v -> v.getDegree() == 0).findAny().get();
		}
		catch (NoSuchElementException e) {
			return false;
		}
		long n = vertices.stream().filter(v -> !v.equals(vertex)).filter(v -> v.getNeighbors().contains(vertex)).count();

		return n == vertices.size() - 1;
	}

	public void squareByList() {
		if (linked) {
			List<DirectedEdge> edgesToBeAdded = new ArrayList<DirectedEdge>();
			vertices.forEach(a -> {
				Vertex u = a;
				a.getNeighbors().forEach(b -> {
					b.getNeighbors().forEach(c -> {
						if (!c.equals(u)) {
							edgesToBeAdded.add(new DirectedEdge(u, c));
						}
					});
				});
			});
			edgesToBeAdded.forEach(e -> addEdge(e));
		}
	}

	public void squareByMatrix() {
		linked = false;
		List<Index> indexesToBeChanged = new ArrayList<Index>();
		double[][] m = getMatrix();
		int size = m.length;
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				if (i != j && m[i][j] != 0)
					for (int k = 0; k < m[j].length; k++)
						if (m[j][k] != 0 && i != k)
							indexesToBeChanged.add(new Index(i, k));

		for (Index index : indexesToBeChanged)
			if (m[index.i][index.j] == 0)
				m[index.i][index.j] = 1;
		loadFromMatrix(m);
		link();
	}

	public void loadFromIncidenceMatrix(String fileName) {
		loadFromIncidenceMatrix(Util.loadMatrixFromFile(fileName));
	}

	public void loadFromIncidenceMatrix(double[][] matrix) {
		reset();
		int numOfVertexes = matrix.length;
		int numOfEdges = matrix[0].length;

		for (int i = 0; i < numOfVertexes; i++)
			vertices.add(new Vertex());

		Vertex a = null, b = null;
		for (int j = 0; j < numOfEdges; j++) {
			for (int i = 0; i < numOfVertexes; i++) {
				if (matrix[i][j] == -1)
					a = vertices.get(i);
				if (matrix[i][j] == 1)
					b = vertices.get(i);
			}
			if (a == null || b == null)
				return;

			edges.add(new DirectedEdge(a, b));
		}

	}

	public int[][] getIncidenceMatrix() {
		int numOfVertexes = vertices.size();
		int numOfEdges = edges.size();

		int[][] matrix = new int[numOfVertexes][numOfEdges];
		for (int i = 0; i < numOfVertexes; i++)
			for (int j = 0; j < numOfEdges; j++)
				matrix[i][j] = 0;

		DirectedEdge e = null;
		int posA, posB;
		for (int i = 0; i < numOfEdges; i++) {
			e = edges.get(i);
			posA = vertices.indexOf(e.getA());
			posB = vertices.indexOf(e.getB());
			matrix[posA][i] = -1;
			matrix[posB][i] = 1;
		}
		return matrix;
	}

	public void addEdge(DirectedEdge newEdge) {
		for (DirectedEdge e : edges) {
			if (e.equals(newEdge)) {
				// debug
				System.out.printf("Edge %s already exists.\n", newEdge);
				return;
			}
		}

		if (!vertices.contains(newEdge.getA()))
			vertices.add(newEdge.getA());
		if (!vertices.contains(newEdge.getB()))
			vertices.add(newEdge.getB());

		if (linked)
			newEdge.getA().add(newEdge.getB());

		edges.add(newEdge);

	}

	public void removeEdge(DirectedEdge e) {
		if (linked) {
			e.getA().getNeighbors().remove(e.getB());
		}
		edges.remove(e);
	}

	public void link() {
		if (!linked) {
			for (DirectedEdge edge : edges) {
				edge.getA().add(edge.getB());
			}
			linked = true;
		}
	}

	public void loadFromMatrix(double[][] matrix) {
		reset();
		for (int i = 0; i < matrix.length; i++)
			vertices.add(new Vertex());
		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix[i].length; j++)
				if (matrix[i][j] != 0)
					edges.add(new DirectedEdge(vertices.get(i), vertices.get(j), matrix[i][j]));
	}

	public double[][] getMatrix() {
		int size = vertices.size();
		double[][] matrix = new double[size][size];

		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				matrix[i][j] = 0;

		edges.forEach(edge -> {
			int i = vertices.indexOf(edge.getA());
			int j = vertices.indexOf(edge.getB());
			double w = edge.getWeight();
			matrix[i][j] = w;
		});

		return matrix;
	}

	public DirectedEdge getEdge(Vertex a, Vertex b) {
		try {
			return edges.parallelStream().filter(edge -> (edge.getA().equals(a) && edge.getB().equals(b))).findAny().get();
		}
		catch (NoSuchElementException e) {
			return null;
		}
	}

}
