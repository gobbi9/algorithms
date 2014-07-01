package grafos;

public class DirectedGraph extends AbstractGraph<Vertex, DirectedEdge> {
		
	public DirectedGraph(){
		super();
	}

	public void addVertex(Vertex v) {
		// TODO Auto-generated method stub
		
	}

	public void removeVertex(Vertex v) {
		// TODO Auto-generated method stub
		
	}

	public void addEdge(DirectedEdge e) {
		// TODO Auto-generated method stub
		
	}

	public void removeEdge(DirectedEdge e) {
		// TODO Auto-generated method stub
		
	}

	public void link() {
		if (!linked) {
			for (Edge edge : edges) {
				edge.getA().add(edge.getB());
			}
			linked = true;
		}		
	}

	public void unlink() {
		// TODO Auto-generated method stub
		
	}

	public GraphElement clone() {
		// TODO Auto-generated method stub
		return null;
	}

	public void loadFromAdjacencyMatrix(String fileName) {
		// TODO Auto-generated method stub
		
	}
	
}
