package grafos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Test {

	// TODO ReadFromFile poderia retornar um grafo diretamente.
	// TODO Separar a parte de leitura de arquivo da parte de setar os edges e
	// vertexes
	// TODO criar pacote "algoutil" com tais funções auxiliares
	//TODO consertar o modo de como a lista de adjacências é impressa.
	//No exemplo simpleinput2 : o "0" está conectado a 1,2,..,6
	//Da forma que ele apresenta "0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6" dá a enteder
	//que 1 está ligado em 2 e assim por diante, o que não é verdade
	//ou trocar o nome de lista de adjancência para lista de siblings :)
	//TODO testar mais os Generics e se estiverem funcionando adicioná-los

	private static Graph g;
	private static int[][] input;

	public static void main(String... args) {

		g = new Graph();
		// g.teste2();
		// g.tABC();
		// g.tABCDE();
		tReadFromFile("simpleinput2.txt");
		// System.out.println(g);

		percorre(g.getVertices().get(0));
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
		// System.out.println(ch +": " + v.getSiblingsCount());
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

		input = new int[][] { { 0, 0, 2, 1 }, { 0, 0, 1, 1 }, { 0, 0, 0, 0 },
				{ 0, 0, 1, 3 } };
		printMatrix(input);

	}

	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input[i].length; j++)
				System.out.print(input[i][j] + " ");
			System.out.println();
		}
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

	public static void tReadFromFile(String fileName) {
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
			input = new File("files/" + fileName);
			scan = new Scanner(input);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		// ler o numero de v/e
		iVertex = scan.nextInt();
		iEdges = scan.nextInt();
		// inicializar todos os vertexes
		List<Vertex> vertices = new ArrayList<Vertex>();

		for (int i = 0; i < iVertex; i++) {
			// todas em 0,0 por enquanto
			vertices.add(new Vertex(0, 0));
		}
		// adicionar ao graph
		g.setVertices(vertices);

		// indices de dois vertices A e B
		int v1, v2;
		// inicializar as edges
		List<Edge> edges = new ArrayList<Edge>();

		try {
			for (int i = 0; i < iEdges; i++) {
				// recebe os indices dos vertexes v1 e v2
				v1 = scan.nextInt();
				v2 = scan.nextInt();
				// inclui a edge
				Vertex a = vertices.get(v1);
				Vertex b = vertices.get(v2);
				edges.add(new Edge(a, b));
			}
		} catch (NoSuchElementException e) {
			// EOF
		}
		// adicionar ao graph
		g.setEdges(edges);

		// encerra o scanner
		scan.close();
		// imprime
		System.out.println("Lido do arquivo:");
		g.printIdAdjacencyList();

	}
	
	//tentativa de função que percorre o Grafo. V1.0
	//ela deve imprimir todos os nós sem repetição
	//aparentemente funciona
	public static void percorre (Vertex origem){//tem que funcionar para qualquer vértice de origem
		for (int i = 0; i<origem.getSiblings().size(); i++){
			while (!origem.getSiblings().get(i).isVisited()){
				System.out.println(origem.getSiblings().get(i).getId());
				origem.getSiblings().get(i).setVisited(true);
				percorre(origem.getSiblings().get(i));
			}	
			
		}
	}

}
