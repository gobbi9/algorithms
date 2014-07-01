package grafos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;

import algoutil.Util;

/**
 * 
 * Pratical Use: @link http://www.graph-magics.com/practic_use.php Tentarei
 * fazer o "shortest path"
 */

// TODO metodo equals
// TODO metodo clone deve fazer um "deepclone"
public class SimpleGraph extends AbstractGraph<Vertex, Edge>{

	public SimpleGraph() {
		super();
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
			//JAVA 8 testar para o caso de nao achar 
			// edges.removeIf(edge -> edge.contains(v));
			Iterator<Edge> iterator = edges.iterator();
			while(iterator.hasNext()){
				Edge edge = iterator.next();
				if (edge.contains(v))
					iterator.remove();
			}
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
				vertices.add(new Vertex(i,j,t));
			}
		}
		
		List<int[]> possibleConnections;
		Vertex center, connection;
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
					edges.add(new Edge(center, connection));
				}
			}
		}
		
		edges = edges.stream().distinct().collect(Collectors.toList());
	}
	
	public void loadFromSimpleInput(String fileName){
		reset();
		// Le um arquivo no formato:
		// V E (numero de vertexes/numero de edges)
		// V1 V2 (edge1 = (V1,V2))
		// Vn Vm (edge2 = (Vn,Vm))
		// ... etc ('E' vezes)

		File input;
		Scanner scan = null;

		int iVertex, iEdges;

		try {
			// carregar o arquivo de entrada
			input = new File(fileName);
			scan = new Scanner(input);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		// le o numero de v/e
		iVertex = scan.nextInt();
		iEdges = scan.nextInt();

		// inclui #iVertex's vertices no graph
		for (int i = 0; i < iVertex; i++)
			// todos em i,0 por enquanto
			addVertex(new Vertex(i,0));		
	
		// indices de dois vertices v1 e v2
		int v1, v2;
		for (int i = 0; i < iEdges; i++) {
			// recebe os indices dos vertexes v1 e v2
			v1 = scan.nextInt();
			v2 = scan.nextInt();
			// recebe os respectivos vetices do graph
			Vertex a = getVertex(v1);
			Vertex b = getVertex(v2);
			// inclui a edge
			addEdge(new Edge(a,b));
		}
		
		// encerra o scanner
		scan.close();
		

	}
	
	//TODO
	public void loadFromAdjacencyMatrix(String fileName){
		
	}

	@Override
	public GraphElement clone() {
		// TODO Auto-generated method stub
		return null;
	}

	// -------------- getters and setters --------------------//


}
