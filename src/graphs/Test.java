package graphs;

import java.util.Arrays;

import algoutil.Util;

public class Test {

	public static void main(String... args) {
		t6();
	}

	public static void tABC() {
		SimpleGraph g = new SimpleGraph();
		
		Vertex a = new Vertex();
		Vertex b = new Vertex();
		Vertex c = new Vertex();

		g.setVertices(Arrays.asList(a, b, c));

		Edge e1 = new Edge(a, b);
		Edge e2 = new Edge(b, c);
		Edge e3 = new Edge(a, c);

		g.setEdges(Arrays.asList(e1, e2, e3));
		g.link();
		g.toHtml();

		// System.out.println("Sum of degrees:");
		// char ch = 'A';
		// for (Vertex v : g.vertices) {
		// System.out.println(ch +": " + v.getNeighborsCount());
		// ch++;
		// }

	}

	public static void tABCDE() {
		SimpleGraph g = new SimpleGraph();
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
		g.link();
		System.out.println(g.toString());
		g.printAdjacencyList();
		g.toHtml();
	}
	
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

	public static void t5() {
		MazeGraph g = new MazeGraph();
		g.loadFromMatrix("files/input.txt");
		g.link();
		g.printIdAdjacencyList();
		/*g.visit(g.getVertex(0), new Action<MazeVertex>(){
			public void action(MazeVertex v){
				System.out.println(v.getId());
			}
		});*/
		//same as
		//TODO adapt to the BFS
		g.visit();
		//g.visit(v -> System.out.println(v.getId()));
		
		Util.printMatrix(g.getMatrix());
		Util.printMatrix(g.getMazeMatrix());
		System.out.println();
	}

	public static void t6() {
		DirectedGraph g = new DirectedGraph();
		g.loadFromMatrix("files/inputAdjacency2.txt");
		g.link();
		g.squareByMatrix();//g.squareByList();
		g.printIdAdjacencyList();
		g.visit();
		Util.printMatrix(g.getMatrix());
		g.toHtml();
//		g.invertEdges();
//		g.link();
//		g.printIdAdjacencyList();
//		g.visit();
//		Util.printMatrix(g.getMatrix());
	}
	
	public static void t7(){
		
		SimpleGraph h = new SimpleGraph();
		h.loadFromMatrix("files/inputAdjacency.txt");
		System.out.println(h);
		h.link();
		h.printIdAdjacencyList();
		h.visit();
		Util.printMatrix(h.getMatrix());
		//simulates the conversion between SimpleGraph and DirectedGraph
		DirectedGraph g = new DirectedGraph();
		g.loadFromMatrix(h.getMatrix());
		g.link();
		System.out.println(g);
		g.printIdAdjacencyList();
		g.visit();
		Util.printMatrix(g.getMatrix());

	}
	
	public static void t8(){
//		SimpleGraph g = new SimpleGraph();
//		g.loadFromSimpleInput("files/simpleinput2.txt");
//		g.link();
//		g.removeVertex(g.getVertex(0));
//		g.addEdge(new Edge(g.getVertices().get(7), g.getVertices().get(10)));
//		g.addEdge(new Edge(g.getVertices().get(6), g.getVertices().get(10)));
//		g.invertEdges();
//		g.toHtml();
//		g.link();
//		System.out.println(g.getConnectedComponents());
		
		/*DirectedGraph h = new DirectedGraph();
		h.loadFromMatrix("files/inputAdjacencyW.txt");
		h.invertEdges();
		h.toHtml();*/
		
		//lag ahead
		MazeGraph i = new MazeGraph();
		i.loadFromMatrix("files/input.txt");
		i.toHtml();
	}

	public static void t9(){
		tShortestPath();
	}

	// ---------------------------------------------------------------------------- //

	public static void tShortestPath() {
		SimpleGraph g = new SimpleGraph();
		g.loadFromSimpleInput("files/simpleinput.txt");
		g.link();
		g.printIdAdjacencyList();

		Vertex s = g.getVertex(0);
		Vertex e = g.getVertex(8);
		g.BFS(s);
		g.printPath(s, e);
		
		g.toHtml();

		// printQueue();
		// System.out.println("path: ");
		// printPath();
		// System.out.println();

	}
	
	public static void testShortestPath(){
		MazeGraph g = new MazeGraph();
		g.loadFromMatrix("files/input.txt");
		g.link();
		g.printIdAdjacencyList();
		//g.removeEdge(g.getEdge(g.getVertex(41), g.getVertex(51)));
		MazeVertex s = g.getVertex(8);
		MazeVertex e = g.getVertex(100);
		g.BFS(s);
		g.printPath(s, e);
		
		g.toHtml();
	}

}
