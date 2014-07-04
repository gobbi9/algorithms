package grafos;


public class DirectedEdge extends Edge{
	
	public DirectedEdge(Vertex a, Vertex b, int weight){
		super(a,b,weight);
	}
	
	public DirectedEdge(Vertex a, Vertex b) {
		super(a,b);
	}
	
	public String toHtml() {
		// retorna uma string no formato:
		// A -- B[label=x];
		// onde A e B são os vertices e label é o peso caso não seja o peso padrão 
		String s = String.format("%c %s %c%s;\n", 
				getA().toChar(), "->", 
				getB().toChar(),
				getWeight() > MIN_WEIGHT ? "[label="+getWeight()+"]" : "");
		
		return s;
	}
	
	public boolean equals(Object obj) {
		DirectedEdge e = (DirectedEdge) obj;
		if (this.vertexes.get(0).equals(e.vertexes.get(0)) && this.vertexes.get(1).equals(e.vertexes.get(1)))
			return true;
		
		return false;
	}

}
