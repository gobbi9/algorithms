package grafos;

public class Edge {
	private Vertex[] nodes;
	//metodo equals deve retorar true para (a,b) e (b,a) por ex
	public Edge(Vertex[] vs){
		nodes = vs.clone();
	}
	
	public Edge(Vertex a, Vertex b){
		nodes = new Vertex[2];
		nodes[0] = a;
		nodes[1] = b;
	}
	
	public Edge clone(){
		return new Edge(nodes);
	}
	
	public String toString(){
		return String.format("(%s, %s)", nodes[0].toString(), nodes[1].toString());
	}
}
