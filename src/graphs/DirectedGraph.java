package graphs;

import java.util.NoSuchElementException;

import graphs.abstracts.AbstractGraph;
import algoutil.Util;

public class DirectedGraph extends AbstractGraph<Vertex, DirectedEdge> {
		
	public DirectedGraph(){
		super();
	}
	
	public void addEdge(DirectedEdge newEdge) {
		for (DirectedEdge e : edges) {
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

	public void loadFromMatrix(int[][] matrix){
		reset();
		for (int i = 0; i < matrix.length; i++)
			vertices.add(new Vertex());
		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix[i].length; j++)
				if (matrix[i][j] != 0)
					edges.add(new DirectedEdge(vertices.get(i), vertices.get(j), matrix[i][j]));
	}

	public void loadFromMatrix(String fileName) {
		loadFromMatrix(Util.loadMatrixFromFile(fileName));
	}
	
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
		});

		return matrix;
	}
	
	public DirectedEdge getEdge(Vertex a, Vertex b){
		try{
			return edges.parallelStream().filter(edge -> (edge.getA().equals(a) && edge.getB().equals(b))).findAny().get();
		}
		catch (NoSuchElementException e){
			return null;
		}
	}
	
}
