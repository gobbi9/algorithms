package graphs;

import graphs.abstracts.AbstractGraph;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import algoutil.Util;

public class MazeGraph extends AbstractGraph<MazeVertex, MazeEdge> {

	private int width, height;

	public void loadFromMatrix(int[][] matrix) {
		reset();

		width = matrix.length;
		height = matrix[0].length;

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				VertexType t;
				switch (matrix[i][j]) {
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

		List<int[]> possibleConnections;
		MazeVertex center, connection;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				final int I = i, J = j;
				possibleConnections = Util.checkArea(matrix, i, j);
				possibleConnections.removeIf(v -> matrix[v[0]][v[1]] == VertexType.WALL.getType());
				try {
					center = vertices
							.parallelStream()
							.filter(v -> v.getX() == I && v.getY() == J
									&& v.getType().getType() != VertexType.WALL.getType()).findAny().get();
				}
				catch (NoSuchElementException e) {
					continue;
				}
				for (int[] vConnect : possibleConnections) {
					connection = vertices.parallelStream().filter(v -> v.getX() == vConnect[0] && v.getY() == vConnect[1])
							.findAny().get();
					edges.add(new MazeEdge(center, connection));
				}
			}
		}

		edges = edges.stream().distinct().collect(Collectors.toList());
	}

	public void loadFromMatrix(String fileName) {
		loadFromMatrix(Util.loadMatrixFromFile(fileName));
	}

	public int[][] getMazeMatrix() {
		int[][] matrix = new int[width][height];

		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++) {
				final int I = i, J = j;
				matrix[i][j] = vertices.parallelStream().filter(v -> v.getX() == I && v.getY() == J).findAny().get()
						.getType().getType();
			}
		return matrix;

	}

}
