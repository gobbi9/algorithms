package grafos;

import algoutil.Util;

public class DirectedGraph extends AbstractGraph<Vertex, DirectedEdge> {
		
	public DirectedGraph(){
		super();
	}

	public void link() {
		if (!linked) {
			for (DirectedEdge edge : edges) {
				edge.getA().add(edge.getB());
			}
			linked = true;
		}		
	}


	public void loadFromMatrix(String fileName) {
		reset();
		int[][] matrix = Util.loadMatrixFromFile(fileName);
		for (int i = 0; i < matrix.length; i++)
			vertices.add(new Vertex());
		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix[i].length; j++)
				if (matrix[i][j] != 0)
					edges.add(new DirectedEdge(vertices.get(i), vertices.get(j), matrix[i][j]));		
	}
	
}
