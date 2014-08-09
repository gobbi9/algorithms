package graphs;


public class DirectedEdge extends Edge{
	
	public DirectedEdge(Vertex a, Vertex b, double weight){
		super(a,b,weight);
	}
	
	public DirectedEdge(Vertex a, Vertex b) {
		super(a,b);
	}
	
	public String toHtml() {
		// returns a string in the format:
		// A -- B[label=x];
		// A -- B[label=x, color=red]; if edge belongs to a path
		// where A and B are vertices, and x is the weight of the edge 
		String s = String.format("%d %s %d[%s%s];\n", 
				getA().getId(), "->", 
				getB().getId(),
				isOnThePath() ? "color=red," : "color=gray,",
				getWeight() > MIN_WEIGHT ? "label="+getWeight() : "");
		if (getA().isOnThePath())
			s += String.format("%d [fontColor=white,color=red]\n", getA().getId());
		if (getB().isOnThePath())
			s += String.format("%d [fontColor=white,color=red]\n", getB().getId());
		
		return s;
	}
	
	public boolean equals(Object obj) {
		DirectedEdge e = (DirectedEdge) obj;
		if (this.vertexes.get(0).equals(e.vertexes.get(0)) && this.vertexes.get(1).equals(e.vertexes.get(1)))
			return true;
		
		return false;
	}

}
