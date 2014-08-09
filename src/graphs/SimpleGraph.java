package graphs;

import graphs.abstracts.AbstractGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 * Pratical Use: @link http://www.graph-magics.com/practic_use.php
 */

// TODO method equals
// TODO method clone should do a "deepclone"
public class SimpleGraph extends AbstractGraph<Vertex, Edge> {

	public SimpleGraph() {
		super();
	}

	public void loadFromSimpleInput(String fileName) {
		reset();
		// Reads a file in the following format:
		// V E (number of vertexes/number of edges)
		// V1 V2 (edge1 = (V1,V2))
		// Vn Vm (edge2 = (Vn,Vm))
		// ... etc ('E' times)

		File input;
		Scanner scan = null;

		int iVertex, iEdges;

		try {
			// loads the input file
			input = new File(fileName);
			scan = new Scanner(input);

		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		// reads the #vertices and #edges
		iVertex = scan.nextInt();
		iEdges = scan.nextInt();

		// initialize the vertexes
		for (int i = 0; i < iVertex; i++)
			addVertex(new Vertex());

		// indexes of two vertices v1 and v2
		int v1, v2;
		for (int i = 0; i < iEdges; i++) {
			// reads the indexes of v1 and v2
			v1 = scan.nextInt();
			v2 = scan.nextInt();
			// get the according vertexes
			Vertex a = getVertex(v1);
			Vertex b = getVertex(v2);
			// initialize an edge
			addEdge(new Edge(a, b));
		}

		// closes the scanner
		scan.close();

	}
	
	public void loadFromMatrix(double[][] matrix){
		reset();
		for (int i = 0; i < matrix.length; i++)
			vertices.add(new Vertex());
		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix[i].length; j++)
				if (j > i && matrix[i][j] != 0)
					edges.add(new Edge(vertices.get(i), vertices.get(j), matrix[i][j]));
	}

	// -------------- getters and setters --------------------//

}
