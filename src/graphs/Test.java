package graphs;

import java.util.Arrays;

import algoutil.Util;

public class Test {

	public static void main(String... args) {
		t14();
		
//		Node a = new Node(1);
//		Node b = new Node(2);
//		Node c = new Node(3);
//		Node d = new Node(4);
//		Node e = new Node(5);
//		Tree tree = new Tree();
//		tree.addNode(a);
//		tree.addNode(b);
//		tree.addNode(c);
//		tree.addNode(d);
//		tree.addNode(e);
//		a.addChild(b);
//		b.addChild(c);
//		a.addChild(d);
//		d.addChild(e);
//		tree.toHtml();
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
	
	//universal sink test
	public static void t10(){
		DirectedGraph g = new DirectedGraph();
		g.loadFromMatrix("files/universalSink.txt");
		g.link();
		g.toHtml();
		//System.out.println(g.containsUniversalSink());
		System.out.println(g.containsUniversalSinkUsingAdjacencyList());
	}
	
	public static void t11(){
		DirectedGraph g = new DirectedGraph();
		g.loadFromIncidenceMatrix("files/incidenceMatrix.txt");
		g.link();
		g.toHtml();
		Util.printMatrix(g.getIncidenceMatrix());
	}
	
	public static void t12(){
		DirectedGraph g = new DirectedGraph();
		g.loadFromMatrix("files/inputAdj3.txt");
		g.link();
		//g.toHtml();
		//g.bfsByMatrix(2);
		//g.bfs(g.getVertex(2)).toHtml();
		//g.bfs(g.getVertex(0)).toHtml();
		System.out.println("Max dist: " + g.getDiameter());
		
		/*
		SimpleGraph h = new SimpleGraph();
		h.loadFromMatrix(g.getMatrix());
		h.link();
		h.toHtml();
		h.bfs().toHtml();*/	
	}
	
	public static void t13(){
		MazeGraph m = new MazeGraph();
		m.loadFromMatrix("files/input.txt");
		m.link();
//		m.bfs(m.getVertex(8)).to(m.getVertices().get(100)).toHtml();
//		m.getTree().pathToHtml();
//		m.toHtml();
//		m.bfs(m.getVertex(3)).to(m.getVertices().get(75)).toHtml();
//		m.toHtml();
//		m.getTree().pathToHtml();
		
		//m.getTree().getVertices().forEach(v -> System.out.println(v.getId()+" -> "+v.getDistance()));
		System.out.println(m.getDiameter());
		//System.out.println("Max dist: " + m.getTree().getDiameter());
		
	}

	public static void tRandom(){
		SimpleGraph g = new SimpleGraph();
		g.loadRandomGraph(15);
		g.link();
		g.toHtml();
		g.bfs().toHtml();
	}
	
	public static void t14(){
		SimpleGraph g = new SimpleGraph();
		g.loadRandomGraph(8);
		g.link();
		g.toHtml();
		g.dfs().toHtml();
	}
}
