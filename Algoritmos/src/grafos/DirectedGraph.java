package grafos;

public class DirectedGraph extends AbstractGraph<Vertex, DirectedEdge> {
		
	public DirectedGraph(){
		super();
	}

	@Override
	public void addVertex(Vertex v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeVertex(Vertex v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addEdge(DirectedEdge e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeEdge(DirectedEdge e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void link() {
		if (!linked) {
			for (Edge edge : edges) {
				edge.getA().add(edge.getB());
			}
			linked = true;
		}		
	}

	@Override
	public void unlink() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GraphElement clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void loadFromAdjacencyMatrix(String fileName) {
		// TODO Auto-generated method stub
		
	}
	
}
