package grafos;

public class Edge extends EdgeAbstract {
	public Edge(Vertex[] vs){
		super();
		vertexes[0] = new Vertex(vs[0]);
		vertexes[1] = new Vertex(vs[1]);
	}
	
	public Edge(Vertex a, Vertex b){
		super();
		vertexes[0] = new Vertex(a);
		vertexes[1] = new Vertex(b);
	}
	
	private Vertex[] inverse(){
		Vertex[] inverted = new Vertex[2];
		Vertex temp = inverted[0].clone();
		inverted[0] = inverted[1].clone();
		inverted[1] = temp;
		return inverted;
	}

	public boolean equals(Object obj) {
		Edge e = (Edge) obj;
		if (this.vertexes[0].equals(e.vertexes[0]) && this.vertexes[1].equals(e.vertexes[1]))
			return true;
		e = new Edge(inverse());
		if (this.vertexes[0].equals(e.vertexes[0]) && this.vertexes[1].equals(e.vertexes[1]))
			return true;
		
		return false;
	}
	
	public Vertex[] copyVertexArray(Vertex[] vs){
		Vertex[] copy = new Vertex[2];
		copy[0] = new Vertex(vs[0]);
		copy[1] = new Vertex(vs[1]);
		return copy;
	}
	
	public Edge clone(){
		return new Edge((Vertex[])vertexes);
	}
	

}
