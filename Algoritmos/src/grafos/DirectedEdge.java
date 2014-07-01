package grafos;


public class DirectedEdge extends Edge{
	
	public DirectedEdge(Vertex a, Vertex b, int weight){
		super(a,b,weight);
	}
	
	public DirectedEdge(Vertex a, Vertex b) {
		super(a,b);
	}
	
	public boolean equals(Object obj) {
		DirectedEdge e = (DirectedEdge) obj;
		if (this.vertexes.get(0).equals(e.vertexes.get(0)) && this.vertexes.get(1).equals(e.vertexes.get(1)))
			return true;
		
		return false;
	}

}
