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

	private List<Vertex> deepCopyList() {
		List<Vertex> copy = new ArrayList<Vertex>();
		copy.set(0, new Vertex(vertexes.get(0)));
		copy.set(1, new Vertex(vertexes.get(1)));
		return copy;
	}

	public String toString() {
		return String.format("[%s,%s]", vertexes.get(0).toString(), vertexes
				.get(1).toString());
	}

	public Edge clone() {
		return new Edge(deepCopyList());
	}

}
