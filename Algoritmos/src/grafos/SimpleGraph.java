package grafos;

import grafos.abstracts.AbstractGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import algoutil.Util;

/**
 * 
 * Pratical Use: @link http://www.graph-magics.com/practic_use.php Tentarei fazer o "shortest path"
 */

// TODO metodo equals
// TODO metodo clone deve fazer um "deepclone"
public class SimpleGraph extends AbstractGraph<Vertex, Edge> {

	public SimpleGraph() {
		super();
	}

	public void loadFromSimpleInput(String fileName) {
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

		}
		catch (FileNotFoundException e) {
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
			addEdge(new Edge(a, b));
		}

		// encerra o scanner
		scan.close();

	}
	
	public void loadFromMatrix(int[][] matrix){
		reset();
		for (int i = 0; i < matrix.length; i++)
			vertices.add(new Vertex());
		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix[i].length; j++)
				if (j > i && matrix[i][j] != 0)
					edges.add(new Edge(vertices.get(i), vertices.get(j), matrix[i][j]));
	}

	public void loadFromMatrix(String fileName) {
		loadFromMatrix(Util.loadMatrixFromFile(fileName));
	}

	// -------------- getters and setters --------------------//

}
