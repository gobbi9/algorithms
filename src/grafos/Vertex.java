package grafos;

import grafos.abstracts.AbstractVertex;

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
		return vertexCopy;
	}

}
