package grafos;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import algoutil.Util;

public class MazeGraph extends AbstractGraph<MazeVertex, MazeEdge>{

	public void addVertex(MazeVertex v) {
		// TODO Auto-generated method stub
		
	}

	public void removeVertex(MazeVertex v) {
		// TODO Auto-generated method stub
		
	}

	public void addEdge(MazeEdge e) {
		// TODO Auto-generated method stub
		
	}

	public void removeEdge(MazeEdge e) {
		// TODO Auto-generated method stub
		
	}


	public void unlink() {
		// TODO Auto-generated method stub
		
	}

	public void loadFromAdjacencyMatrix(String fileName) {
		// TODO Auto-generated method stub
		
	}

	public GraphElement clone() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void loadFromMazeInput(String fileName){
		super.reset();

		int[][] matrix = Util.loadMatrixFromFile(fileName);

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				VertexType t;
				switch(matrix[i][j]){
					case 0: t = VertexType.FLOOR;
						break;
					case 1: t = VertexType.WALL;
						break;
					case 2: t = VertexType.START;
						break;
					case 3: t = VertexType.END;
						break;
					default: t = VertexType.FLOOR;
						break;
				}
				vertices.add(new MazeVertex(i,j,t));
			}
		}
		
		List<int[]> possibleConnections;
		MazeVertex center, connection;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				final int I = i, J = j;
				possibleConnections = Util.checkArea(matrix, i, j);
				possibleConnections.removeIf(v -> matrix[v[0]][v[1]] == VertexType.WALL.getType());
				try{
					center = vertices.parallelStream().filter(v -> v.getX() == I && v.getY() == J && 
							v.getType().getType() != VertexType.WALL.getType()).findAny().get();
				}
				catch(NoSuchElementException e){
					continue;
				}
				for (int[] vConnect : possibleConnections){
					connection = vertices.parallelStream().
							filter(v -> v.getX() == vConnect[0] && v.getY() == vConnect[1]).findAny().get();
					edges.add(new MazeEdge(center, connection));
				}
			}
		}
		
		edges = edges.stream().distinct().collect(Collectors.toList());
	}

}
