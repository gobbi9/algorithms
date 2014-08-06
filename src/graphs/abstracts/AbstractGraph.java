package graphs.abstracts;

import graphs.interfaces.Element;
import graphs.interfaces.VertexAction;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Consumer;

import trees.Node;
import trees.Tree;
import algoutil.Util;

public abstract class AbstractGraph<V extends AbstractVertex<V>, E extends AbstractEdge<V>> {
	protected List<V> vertices;
	protected List<E> edges;
	protected boolean linked;
	protected Tree tree;
	private Random random;

	private int connectedComponents;
	protected Consumer<V> resetVertex = v -> {
		v.setVisited(false);
		v.setParent(null);
		v.setOnThePath(false);
	};
	protected Consumer<E> resetEdge = e -> {
		e.setOnThePath(false);
		e.setVisited(false);
	};

	public AbstractGraph() {
		reset();
	}

	protected void reset() {
		vertices = new ArrayList<V>();
		edges = new ArrayList<E>();
		linked = false;
		GraphElement.objCounter = Element.START_INDEX;
		random = new Random();
	}

	public void bfsByMatrix(int i) {
		int index = i;
		int[][] m = getMatrix();
		Queue<V> queue = new ArrayDeque<V>();
		queue.add(getVertex(index));
		while (!queue.isEmpty()) {
			V v = queue.poll();
			System.out.println(v.getId());
			index = vertices.indexOf(v);
			visitIndex(m, index);
			for (int j = 0; j < m.length; j++)
				if (m[index][j] != 0)
					queue.add(getVertex(j));
		}
	}

	private void visitIndex(int[][] matrix, int i) {
		for (int j = 0; j < matrix.length; j++)
			matrix[j][i] = 0;
	}

	public void markPath(){
		List<Integer> ids = tree.getPathIds();
		if (ids.size() > 0){
			int i;
			for (i = 0; i < ids.size() - 1; i++){
				getVertex(ids.get(i) - 1).setOnThePath(true);
				getEdge(getVertex(ids.get(i+1) - 1), getVertex(ids.get(i) - 1)).setOnThePath(true);
			}
			getVertex(ids.get(i) - 1).setOnThePath(true);
		}
	}
	
	public Tree dfs() {
		return dfs(vertices.get(0));
	}
	
	public Tree dfs (int id){
		return dfs(vertices.get(id - Element.START_INDEX));
	}
	
	public Tree dfs(V start) {
		return dfs(start, v -> System.out.println(v));
	}

	public Tree dfs(V start, VertexAction<V> action) {
		vertices.forEach(resetVertex);
		edges.forEach(resetEdge);
		vertices.forEach(v -> {
			v.setDepth(0);
		});
		
		Tree tree = new Tree();
		Deque<V> stack = new ArrayDeque<V>();
		start.setParent(null);
		stack.push(start);
		
		int level = 0;
		dfsR(stack, action, tree, level);

		setTree(tree);
		return getTree();
	}
	
	private void dfsR(Deque<V> stack, VertexAction<V> action, Tree tree, int level) {
		while (!stack.isEmpty()) {
			V vertex = stack.pop();
			Node node;
			if (vertex.isVisited()){
				level -= 1;
				return;
			}
			else{				
				vertex.setVisited(true);
				action.run(vertex);
				node = new Node(vertex.getId(), level);
				tree.addNode(node);
				level += 1;
			}
			
			for (V v : vertex.getNeighbors()){
				if (!v.isVisited()) {
					v.setParent(vertex);
					stack.push(v);
					dfsR(stack,action, tree, level);
					node.addChild(tree.getNodeByValue(v.getId()));
				}
			}			
		}
	}

	public Tree bfs() {
		return bfs(vertices.get(0));
	}
	
	public Tree bfs (int id){
		return bfs(vertices.get(id - Element.START_INDEX));
	}

	public Tree bfs(V start) {
		return bfs(start, v -> System.out.println(v));
	}

	public Tree bfs(V start, VertexAction<V> action) {
		vertices.forEach(resetVertex);
		edges.forEach(resetEdge);
		vertices.forEach(v -> {
			v.setEccentricity(0);
			v.setDistance(0);
		});

		Tree tree = new Tree();
		Queue<V> queue = new ArrayDeque<V>();
		int distance = 0;

		queue.add(start);
		start.setVisited(true);
		start.setDistance(distance);
		start.setEccentricity(distance);
		
		while (!queue.isEmpty()) {
			V vertex = queue.poll();
			Node node = new Node(vertex.getId(), vertex.getDistance());
			tree.addNode(node);
			action.run(vertex);
			for (V neighbor : vertex.getNeighbors()) {
				if (!neighbor.isVisited()) {
					neighbor.setParent(vertex);
					neighbor.setVisited(true);
					distance = neighbor.getParent().getDistance() + 1;
					if (distance > start.getEccentricity()) {
						start.setEccentricity(distance);
						tree.getRoot().setEccentricity(distance);
					}
					neighbor.setDistance(distance);
					queue.add(neighbor);
				}
			}
		}
		vertices.forEach(v -> v.setVisited(false));
		edges.forEach(e -> e.setVisited(false));
		
		queue.clear();
		queue.add(start);
		start.setVisited(true);		
		while (!queue.isEmpty()) {
			V vertex = queue.poll();
			Node node = tree.getNodeByValue(vertex.getId());
			for (V neighbor : vertex.getNeighbors()) {
				if (!neighbor.isVisited()) {
					neighbor.setVisited(true);
					node.addChild(tree.getNodeByValue(neighbor.getId()));
					queue.add(neighbor);
				}
			}
		}
		setTree(tree);
		return getTree();
	}

	public int getDiameter() {
		List<Integer> eccentricities = new ArrayList<Integer>();
		getVertices().forEach(v -> eccentricities.add(bfs(v).getRoot().getEccentricity()));
		return eccentricities.stream().max((a, b) -> {
			return a - b;
		}).get();
	}

	public void addVertex(V newVertex) {
		for (V v : vertices)
			if (v.equals(newVertex)) {
				return;
			}

		vertices.add(newVertex);
		linked = false;
	}

	public void addEdge(E newEdge) {
		for (E e : edges) {
			if (e.equals(newEdge)) {
				return;
			}
		}

		if (!vertices.contains(newEdge.getA()))
			vertices.add(newEdge.getA());
		if (!vertices.contains(newEdge.getB()))
			vertices.add(newEdge.getB());

		if (linked) {
			newEdge.getA().add(newEdge.getB());
			newEdge.getB().add(newEdge.getA());
		}

		edges.add(newEdge);

	}

	public void removeVertex(V v) {
		edges.removeIf(edge -> edge.contains(v));
		vertices.remove(v);

		if (linked) {
			vertices.forEach(vertex -> vertex.neighbors.remove(v));
		}
	}

	public void removeEdge(E e) {
		if (linked) {
			e.getB().neighbors.remove(e.getA());
			e.getA().neighbors.remove(e.getB());
		}
		edges.remove(e);
	}

	public void link() {
		if (!linked) {
			for (E edge : edges) {
				edge.getA().add(edge.getB());
				edge.getB().add(edge.getA());
			}
			linked = true;
		}
	}

	public void unlink() {
		vertices.forEach(v -> v.neighbors = new ArrayList<V>());
		linked = false;
	}

	public void loadFromMatrix(String fileName) {
		loadFromMatrix(Util.loadMatrixFromFile(fileName));
	}

	public abstract void loadFromMatrix(int[][] matrix);

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
			matrix[j][i] = w;
		});

		return matrix;
	}

	public void printIdAdjacencyList() {

		String output = "";

		for (V v : vertices) {
			// A,B,C..
			output += v.getId();

			if (v.neighbors.size() == 0) {
				output += " -> " + "-\n";
				continue;
			}

			for (V va : v.neighbors) {
				output += " -> " + va.getId();
			}
			output += '\n';
		}

		System.out.println(output);
	}

	public void printAdjacencyList() {
		// Print the vertices as A,B,C,D... according with the order of their ids
		// It assumes that all vertices are initialized before the edges
		// and the first vertex has id = 0

		String output = "";
		char vtx = 'A';

		for (V v : vertices) {
			// A,B,C..
			output += vtx++;

			if (v.neighbors.size() == 0) {
				// no neighbors for this one :(
				output += " -> " + "0\n";
				continue;
			}

			for (V va : v.neighbors) {
				// converts the number id to char
				output += " -> " + (char) ('A' + va.getId());
			}
			output += '\n';
		}

		System.out.println(output);
	}

	public String verticesToString() {
		String output = "";
		output += "{";
		if (vertices.size() > 0) {
			output += vertices.get(0).toString();
			if (vertices.size() > 1)
				for (int i = 1; i < vertices.size(); i++)
					output += ", " + vertices.get(i).toString();
		}
		output += "}";
		return output;
	}

	public String edgesToString() {
		String output = "";
		output += "{";
		if (edges.size() > 0) {
			output += edges.get(0).toString();
			if (edges.size() > 1)
				for (int i = 1; i < edges.size(); i++)
					output += ", " + edges.get(i).toString();
		}
		output += "}";
		return output;
	}

	public String toString() {
		String output = "";
		output += verticesToString();
		output += ", ";
		output += edgesToString();
		output += "\nConnected components: " + getConnectedComponents();
		return output;
	}

	private static int idPage = 0;

	public void toHtml() {
		toHtml(this.edges);
	}

	public void toHtml(List<E> edges) {
		// header
		final String HEAD = "digraph {\n" + "node [shape=circle fontSize=16]\n"
				+ "edge [length=100, color=gray, fontColor=black]\n";
		// footer - empty for now
		final String TAIL = "}\n";

		// page title - eyecandy
		final String TITLE = "Hello Simple Graph!";
		// template file
		final String TEMPL_PATH = "files/template.html";

		// creates the text structure in "dot" language
		StringBuffer dotBuf = new StringBuffer();

		dotBuf.append(HEAD);
		for (E edge : edges) {
			dotBuf.append(edge.toHtml());
		}
		dotBuf.append(TAIL);

		Scanner scan;
		try {
			scan = new Scanner(new File(TEMPL_PATH));
		}
		catch (FileNotFoundException e1) {
			scan = null;
			e1.printStackTrace();
		}
		// final file's buffer
		StringBuffer htmlBuf = new StringBuffer();
		String line;

		// replaces the flags
		while (scan.hasNextLine()) {
			line = scan.nextLine();
			if (line.contains("$data")) {
				htmlBuf.append(dotBuf);
				continue;
			}
			if (line.contains("$title"))
				line = line.replace("$title", TITLE);
			else if (line.contains("$options"))
				line = line.replace("$options", "");
			htmlBuf.append(line + '\n');
		}

		scan.close();

		String fileName = "output/o" + idPage + ".html";
		idPage++;

		try {
			Files.write(Paths.get(fileName), htmlBuf.toString().getBytes());
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		Util.runInFirefox(fileName);

	}

	protected void countComponents() {
		resetVisits();
		connectedComponents = 0;
		vertices.forEach(v -> {
			if (!v.isVisited()) {
				connectedComponents++;
				if (v.neighbors.size() == 0)
					v.setVisited(true);
				else
					visit(v);
			}
		});
		resetVisits();
	}

	public boolean isSinglyConnected(){
		for (V vertex : vertices){
			Tree a = bfs(vertex);
			Tree b = dfs(vertex);
			if (!a.equals(b))
				return false;
		}
		return true;
	}
	
	public void invertEdges() {
		int[][] matrix = this.getMatrix();
		int size = matrix.length;
		int[][] transpose = new int[size][size];

		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				transpose[i][j] = matrix[j][i];

		this.loadFromMatrix(transpose);

	}

	public void visit() {
		if (vertices.size() > 0)
			visit(vertices.get(0), v -> System.out.println(v.getId()));
		else
			System.out.println("There are no vertices in this graph!!");
	}

	public void visit(VertexAction<V> a) {
		if (vertices.size() > 0)
			visit(vertices.get(0), a);
	}

	private void visit(V origin) {
		for (int i = 0; i < origin.getNeighbors().size(); i++) {
			while (!origin.getNeighbors().get(i).isVisited()) {
				origin.getNeighbors().get(i).setVisited(true);
				this.visit(origin.getNeighbors().get(i));
			}
		}
	}

	public void visit(V origin, VertexAction<V> a) {
		for (int i = 0; i < origin.getNeighbors().size(); i++) {
			while (!origin.getNeighbors().get(i).isVisited()) {
				a.run(origin.getNeighbors().get(i));
				origin.getNeighbors().get(i).setVisited(true);
				this.visit(origin.getNeighbors().get(i), a);
			}
		}
	}

	public void resetVisits() {
		vertices.forEach(v -> v.setVisited(false));
	}

	public void loadRandomGraph() {
		loadFromMatrix(getRandomGraph());
	}

	public void loadRandomGraph(int numberOfVertices) {
		loadFromMatrix(getRandomGraph(numberOfVertices));
	}

	protected int[][] getRandomGraph() {
		return getRandomGraph(1 + random.nextInt(99));
	}

	protected int[][] getRandomGraph(int numberOfVertices) {
		int[][] matrix = new int[numberOfVertices][numberOfVertices];

		for (int i = 0; i < numberOfVertices; i++)
			for (int j = 0; j < numberOfVertices; j++) {
				double r = random.nextDouble();
				matrix[i][j] = r > Math.pow((1.0/numberOfVertices), 1/1.2) ? 0 : 1;
			}
		return matrix;
	}

	public E getEdge(V a, V b) {
		try {
			return edges
					.parallelStream()
					.filter(edge -> (edge.getA().equals(a) && edge.getB().equals(b))
							|| (edge.getA().equals(b) && edge.getB().equals(a))).findAny().get();
		}
		catch (NoSuchElementException e) {
			return null;
		}
	}

	public V getVertex(int index) {
		return vertices.get(index);
	}

	public List<V> getVertices() {
		return vertices;
	}

	public void setVertices(List<V> vertices) {
		this.vertices = vertices;
	}

	public List<E> getEdges() {
		return edges;
	}

	public void setEdges(List<E> edges) {
		this.edges = edges;
	}

	public boolean isLinked() {
		return linked;
	}

	public void setLinked(boolean linked) {
		this.linked = linked;
	}

	public boolean isConnected() {
		return connectedComponents == 1;
	}

	public int getConnectedComponents() {
		countComponents();
		return connectedComponents;
	}

	public void setTree(Tree tree){
		this.tree = tree;
	}
	
	public Tree getTree() {
		return tree;
	}
}