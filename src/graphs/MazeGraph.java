package graphs;

import graphs.abstracts.AbstractGraph;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import algoutil.Util;

public class MazeGraph extends AbstractGraph<MazeVertex, MazeEdge> {

	private int width, height;

	public void loadFromMatrix(double[][] matrix) {
		reset();

		width = matrix.length;
		height = matrix[0].length;

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				VertexType t;
				int n = (int) matrix[i][j];
				switch (n) {
					case 0:
						t = VertexType.FLOOR;
						break;
					case 1:
						t = VertexType.WALL;
						break;
					case 2:
						t = VertexType.START;
						break;
					case 3:
						t = VertexType.END;
						break;
					default:
						t = VertexType.FLOOR;
						break;
				}
				vertices.add(new MazeVertex(i, j, t));
			}
		}

		List<double[]> possibleConnections;
		MazeVertex center, connection;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				final int I = i, J = j; 
				possibleConnections = Util.checkArea(matrix, i, j);
				possibleConnections.removeIf(v -> matrix[(int) v[0]][(int) v[1]] == VertexType.WALL.getType());
				try {
					center = vertices
							.parallelStream()
							.filter(v -> v.getX() == I && v.getY() == J
									&& v.getType().getType() != VertexType.WALL.getType()).findAny().get();
				}
				catch (NoSuchElementException e) {
					continue;
				}
				for (double[] vConnect : possibleConnections) {
					connection = vertices.parallelStream().filter(v -> v.getX() == vConnect[0] && v.getY() == vConnect[1])
							.findAny().get();
					edges.add(new MazeEdge(center, connection));
				}
			}
		}

		edges = edges.stream().distinct().collect(Collectors.toList());
	}

	public double[][] getMazeMatrix() {
		double[][] matrix = new double[width][height];

		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++) {
				final int I = i, J = j;
				matrix[i][j] = vertices.parallelStream().filter(v -> v.getX() == I && v.getY() == J).findAny().get()
						.getType().getType();
			}
		return matrix;

	}

}
