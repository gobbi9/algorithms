package graphs;

import graphs.abstracts.AbstractVertex;

public class Vertex extends AbstractVertex<Vertex> {

	public Vertex() {
		super();
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result;
		return result;
	}

	public Vertex clone() {
		Vertex vertexCopy = new Vertex();
		vertexCopy.id = id;
		vertexCopy.setDepth(depth);
		vertexCopy.setDistance(distance);
		vertexCopy.setEccentricity(eccentricity);
		vertexCopy.setOnThePath(onThePath);
		vertexCopy.setParent(parent);
		vertexCopy.setVisited(visited);
		return vertexCopy;
	}

}
