package graphs;

import graphs.abstracts.AbstractEdge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Edge extends AbstractEdge<Vertex> {

	private Edge(List<Vertex> vs, double weight) {
		this(vs.get(0), vs.get(1), weight);
	}

	public Edge(Vertex a, Vertex b) {
		this(a, b, 1);
	}

	public Edge(Vertex a, Vertex b, double weight) {
		super(weight);
		vertexes = Arrays.asList(a, b);
	}

	protected List<Vertex> deepCopyList() {
		List<Vertex> copy = new ArrayList<Vertex>();
		copy.add(vertexes.get(0).clone());
		copy.add(vertexes.get(1).clone());
		return copy;
	}

	public Edge clone() {
		Edge edge = new Edge(deepCopyList(), this.weight);
		edge.id = id;
		return edge;
	}

}
