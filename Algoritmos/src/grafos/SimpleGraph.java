package grafos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

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

	public void unlink() {
		//TODO
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
			addVertex(new Vertex());		
	
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
