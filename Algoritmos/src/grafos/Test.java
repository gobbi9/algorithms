package grafos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import algoutil.Reader;
import algoutil.Util;

public class Test {

	private static SimpleGraph g;
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
		t7();
		// tShortestPath();

	}

	public static void tABC() {

		Vertex a = new Vertex();
		Vertex b = new Vertex();
		Vertex c = new Vertex();

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
		Vertex a = new Vertex();
		Vertex b = new Vertex();
		Vertex c = new Vertex();

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

	public static void t5() {
		MazeGraph g = new MazeGraph();
		g.loadFromMatrix("files/input.txt");
		g.link();
		g.printIdAdjacencyList();
		g.visit(g.getVertex(0));
		Util.printMatrix(g.getMatrix());
	}

	public static void t6() {
		SimpleGraph g = new SimpleGraph();
		g.loadFromMatrix("files/inputAdjacency.txt");
		System.out.println(g);
		g.link();
		g.printIdAdjacencyList();
		g.visit(g.getVertex(0));
		Util.printMatrix(g.getMatrix());
	}
	
	public static void t7(){
		
		SimpleGraph h = new SimpleGraph();
		h.loadFromMatrix("files/inputAdjacency.txt");
		System.out.println(h);
		h.link();
		h.printIdAdjacencyList();
		h.visit();
		Util.printMatrix(h.getMatrix());
		//simula a conversão de SimpleGraph para DirectedGraph e vice-versa
		DirectedGraph g = new DirectedGraph();
		g.loadFromMatrix(h.getMatrix());
		System.out.println(g);
		g.link();
		g.printIdAdjacencyList();
		g.visit();
		Util.printMatrix(g.getMatrix());

	}

	public static void tABCDE() {

		Vertex a = new Vertex();
		Vertex b = new Vertex();
		Vertex c = new Vertex();
		Vertex d = new Vertex();
		Vertex e = new Vertex();

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
		g = new SimpleGraph();
		g.loadFromSimpleInput("files/simpleinput.txt");
		g.link();
		g.printIdAdjacencyList();

		Vertex s = g.getVertex(0);
		Vertex e = g.getVertex(8);
		BFS(g, s);
		printPath(s, e);

		// printQueue();
		// System.out.println("path: ");
		// printPath();
		// System.out.println();

	}

	static void printPath(Vertex s, Vertex e) {

		if (s.equals(e)) {
			System.out.printf(s.toString());
		}
		else if (e.getParent() == null) {
			System.out.printf("Não existe caminho de %s para %s.\n", s.toString(), e.toString());
		}
		else {
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
		// printQueue();
		return v;
	}

	public static void printQueue() {
		System.out.printf("Queue: ");
		for (Vertex v : simpleQueue)
			System.out.printf("%s ", v.toString());
		System.out.println();
	}

	public static void BFS(SimpleGraph g, Vertex s) {

		s.visit();
		s.setParent(null);
		s.setDepth(0);
		enqueue(s);

		Vertex v;
		while ((v = dequeue()) != null) {
			for (Vertex nv : v.getNeighbors()) {
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
