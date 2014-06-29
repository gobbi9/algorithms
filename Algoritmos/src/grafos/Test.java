package grafos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;



import algoutil.Reader;
import algoutil.Reader1337;
import algoutil.Util;

public class Test {

	private static Graph g;
	private static int[][] input;

	public static void main(String... args) {

		// g = new Graph();
		// g.loadFromSimpleInput("files/simpleinput.txt");
		// g.link();
		// g.printIdAdjacencyList();
		// percorre(g.getVertices().get(0));
		// g.loadFromSimpleInput("files/simpleinput2.txt");
		// g.link();
		// g.printIdAdjacencyList();
		// percorre(g.getVertices().get(0));
		t5();
		//tShortestPath();

	}

	public static void tABC() {

		Vertex a = new Vertex(0, 0);
		Vertex b = new Vertex(0, 1);
		Vertex c = new Vertex(1, 0);

		g.setVertices(Arrays.asList(a, b, c));

		Edge e1 = new Edge(a, b);
		Edge e2 = new Edge(b, c);
		Edge e3 = new Edge(a, c);

		g.setEdges(Arrays.asList(e1, e2, e3));

		System.out.println(g.toString());

		// System.out.println("Soma dos graus:");
		// char ch = 'A';
		// for (Vertex v : g.vertices) {
		// System.out.println(ch +": " + v.getNeighborsCount());
		// ch++;
		// }

	}

	// testar equals do Edge
	public static void t3() {
		Vertex a = new Vertex(0, 0);
		Vertex b = new Vertex(0, 1);
		Vertex c = new Vertex(1, 0);

		Edge ab = new Edge(a, b);
		Edge ba = new Edge(b, a);
		Edge ac = new Edge(a, c);

		System.out.println(ab.equals(ba));
		System.out.println(ab.equals(ab));
		System.out.println(ac.equals(ba));
	}

	// TODO montar grafo a partir de uma matriz 4x4 de exemplo
	/*
	 * 0 -> nada 1 -> parede 2 -> inicio 3 -> fim
	 */
	public static void t4() {

		Reader reader = new Reader("files/input.txt");
		String[] dimensions = reader.iterator().next().split("x");

		int lines = Integer.parseInt(dimensions[0]);
		int columns = Integer.parseInt(dimensions[1]);

		input = new int[lines][columns];

		int i = 0;
		for (String line : reader) {
			if (!line.contains("x")) {
				String[] values = line.split("[\t ,;]");
				for (int j = 0; j < input[i].length; j++) {
					input[i][j] = Integer.parseInt(values[j]);
				}
				i++;
			}
		}
		Util.printMatrix(input);
	}

	// usando o Reader1337
	public static void t5() {

		Reader1337 reader = new Reader1337("files/input.txt");

		int[][] matrix = reader.getMatrix();

		List<Vertex> vertexes = new ArrayList<Vertex>();
		List<Edge> edges = new ArrayList<Edge>();

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
				vertexes.add(new Vertex(i,j,t));
			}
		}
		
		List<int[]> possibleConnections;
		Vertex center, connection;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				final int I = i, J = j;
				possibleConnections = checkArea(matrix, i, j);
				possibleConnections.removeIf(v -> matrix[v[0]][v[1]] == VertexType.WALL.getType());
				try{
					center = vertexes.stream().parallel().filter(v -> v.getX() == I && v.getY() == J && 
							v.getType().getType() != VertexType.WALL.getType()).findAny().get();
				}
				catch(NoSuchElementException e){
					continue;
				}
				for (int[] vConnect : possibleConnections){
					connection = vertexes.stream().parallel().
							filter(v -> v.getX() == vConnect[0] && v.getY() == vConnect[1]).findAny().get();
					edges.add(new Edge(center, connection));
				}
			}
		}
		
		edges = edges.stream().distinct().collect(Collectors.toList());

		/*vertexes.forEach(System.out::println);
		System.out.println("----------------------");
		edges.forEach(e -> System.out.println(e + " "+ e.getA().getType().getType() + " " + e.getB().getType().getType()));
		*/
		
		Graph g = new Graph();
		g.setEdges(edges);
		g.setVertices(vertexes);
		g.link();
		//g.printIdAdjacencyList();
		percorre(g.getVertex(0));
	}
	
	public static List<int[]> checkArea(int[][] m, int i, int j) {
		int lines = m.length;// i
		int columns = m[0].length;// j
		List<int[]> area = new ArrayList<int[]>();
	
		if (i - 1 >= 0 && j - 1 >= 0)
			area.add(new int[] { i - 1, j - 1 });

		if (i - 1 >= 0)
			area.add(new int[] { i - 1, j });

		if (i - 1 >= 0 && j + 1 < columns)
			area.add(new int[] { i - 1, j + 1 });

		if (j - 1 >= 0)
			area.add(new int[] { i, j - 1 });

		if (j + 1 < columns)
			area.add(new int[] { i, j + 1 });

		if (i + 1 < lines && j - 1 >= 0)
			area.add(new int[] { i + 1, j - 1 });

		if (i + 1 < lines)
			area.add(new int[] { i + 1, j});

		if (i + 1 < lines && j + 1 < columns)
			area.add(new int[] { i + 1, j + 1 });
		
		return area;
	}

	public static void tABCDE() {

		Vertex a = new Vertex(0, 0);
		Vertex b = new Vertex(0, 1);
		Vertex c = new Vertex(2, 0);
		Vertex d = new Vertex(2, 2);
		Vertex e = new Vertex(1, 1);

		g.setVertices(Arrays.asList(a, b, c, d, e));

		Edge e1 = new Edge(a, b);
		Edge e2 = new Edge(b, d);
		Edge e3 = new Edge(d, c);
		Edge e4 = new Edge(c, a);
		Edge e5 = new Edge(d, e);

		g.setEdges(Arrays.asList(e1, e2, e3, e4, e5));

		System.out.println(g.toString());
		g.printAdjacencyList();
	}

	// tentativa de função que percorre o Grafo. V1.0
	// ela deve imprimir todos os nós sem repetição
	// aparentemente funciona
	public static void percorre(Vertex origem) {// tem que funcionar para
												// qualquer vértice de origem
		for (int i = 0; i < origem.getNeighbors().size(); i++) {
			while (!origem.getNeighbors().get(i).isVisited()) {
				System.out.println(origem.getNeighbors().get(i).getId());
				origem.getNeighbors().get(i).setVisited(true);
				percorre(origem.getNeighbors().get(i));
			}

		}
	}
	
	// ---------------------------------------------------------------------------- //
	
	public static void tShortestPath() {
		g = new Graph();
		g.loadFromSimpleInput("files/simpleinput.txt");
		g.link();
		g.printIdAdjacencyList();
		
		Vertex s = g.getVertex(0);
		Vertex e = g.getVertex(8);
		BFS(g, s);
		printPath(s, e);
				
//		printQueue();
//		System.out.println("path: ");
//		printPath();
//		System.out.println();
		
	
	}
	
	static void printPath(Vertex s, Vertex e) {
		
		if (s.equals(e)) {
			System.out.printf(s.toString());
		} else if (e.getParent() == null) {
			System.out.printf("Não existe caminho de %s para %s.\n",
					s.toString(), e.toString());			
		} else {
			printPath(s, e.getParent());
			System.out.printf(" -> " + e.toString());			
		}		
	}
	
	// TODO usar linkedList como queue...
	private static List<Vertex> simpleQueue;
	// controle da queue
	private static int qPos;
	
	private static void enqueue(Vertex v) {
		if (simpleQueue == null) {
			simpleQueue = new ArrayList<Vertex>();	
			qPos = 0;			
		}		
		simpleQueue.add(v);		
	}
	
	private static Vertex dequeue() {
		// queue is empty 
		if (simpleQueue.size() == qPos)
			return null;
		
		Vertex v = simpleQueue.get(qPos);				
		qPos++;
//		printQueue();
		return v;		
	}
	
	public static void printQueue() {
		System.out.printf("Queue: ");
		for (Vertex v : simpleQueue)
			System.out.printf("%s ", v.toString());
		System.out.println();
	}	
	
	public static void BFS(Graph g, Vertex s) {
		
		s.visit();
		s.setParent(null);
		s.setDepth(0);		
		enqueue(s);
		
		Vertex v;		
		while ((v = dequeue()) != null) {
			for (Vertex nv: v.getNeighbors()) {
				if (!nv.isVisited()) {					
					nv.visit();
					nv.setParent(v);
					nv.setDepth(nv.getDepth() + 1);
					enqueue(nv);
				}
			}
		}
		
	}

}
