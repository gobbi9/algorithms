package grafos;

public abstract class EdgeAbstract extends GraphElement {
	protected VertexAbstract[] vertexes;

	public EdgeAbstract() {
		super();
		vertexes = new VertexAbstract[2];
	}

	public boolean equals(Object obj) {
		EdgeAbstract e = (EdgeAbstract) obj;
		if (this.vertexes[0].equals(e.vertexes[0]) && this.vertexes[1].equals(e.vertexes[1]))
			return true;
		if (this.vertexes[0].equals(e.vertexes[1]) && this.vertexes[1].equals(e.vertexes[0]))
			return true;
		
		return false;
	}
	
	public String toString() {
		return String.format("(%s, %s)", vertexes[0].toString(),
				vertexes[1].toString());
	}
}
