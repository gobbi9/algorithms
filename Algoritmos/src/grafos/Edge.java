package grafos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Edge extends AbstractEdge<Vertex> {

	public Edge(List<Vertex> vs) {
		this(vs.get(0), vs.get(1));
	}

	public Edge(Vertex a, Vertex b) {
		super();
		vertexes = Arrays.asList(a,b);
	}
	
	protected List<Vertex> deepCopyList() {
		List<Vertex> copy = new ArrayList<Vertex>();
		copy.add(vertexes.get(0).clone());
		copy.add(vertexes.get(1).clone());
		return copy;
	}

	public Edge clone() {
		return new Edge(deepCopyList());
	}

}
