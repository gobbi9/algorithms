package grafos;

import java.util.List;

public class DirectedEdge extends Edge{

	public DirectedEdge(List<Vertex> vs) {
		super(vs);
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
