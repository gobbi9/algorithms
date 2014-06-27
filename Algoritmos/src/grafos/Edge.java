package grafos;

public class Edge extends EdgeAbstract {
	
	public Edge(Vertex[] vs){		
		this(vs[0], vs[1]);
	}
	
	public Edge(Vertex a, Vertex b){
		super();		
		// incluir siblings
//		a.add(b);		
//		b.add(a);		
		vertexes[0] = a;
		vertexes[1] = b;
	}
	
	private Vertex[] deepCopyArray(){
		Vertex[] copy = new Vertex[2];
		copy[0] = new Vertex((Vertex) vertexes[0]);
		copy[1] = new Vertex((Vertex) vertexes[1]);
		return copy;
	}
	
	//XXX duplicado -- () ou []?
	public String toString(){
		return String.format("[%s,%s]", vertexes[0].toString(), vertexes[1].toString());
	}
	
	public Edge clone(){
		return new Edge(deepCopyArray());
	}
	

}
