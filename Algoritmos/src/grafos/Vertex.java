package grafos;

public class Vertex extends AbstractVertex<Vertex> {
	private int x, y;
	private VertexType type;
	
	private Vertex parent;
	private int depth;
		
	public Vertex(Vertex v) {
		this(v.getX(), v.getY(), v.getType());
	}
	
	public Vertex(int x, int y){
		this(x, y, VertexType.FLOOR);
	}

	public Vertex(int x, int y, VertexType type) {
		super();
		this.x = x;
		this.y = y;
		this.type = type;
	}

	public Vertex get(int x, int y) {
		for (Vertex vertex : neighbors) {
			if (vertex.getX() == x && vertex.getY() == y)
				return vertex;
		}
		return null;
	}
	
	public boolean equals(Object obj) {
		Vertex v = (Vertex) obj;
		return (v.getX() == this.x && v.getY() == this.y);
	}

	public Vertex clone() {
		Vertex vertexCopy = new Vertex(x, y);
		return vertexCopy;
	}
	
	public String toString(){
		return String.format("(%d,%d)", x, y);		
	}
	
	public char toChar() {
		// melhor legibilidade para grafos de até 26 vertices
		return (char) ('A' + getId());
	}
	
	public void visit() {
		this.visited = true;
		// debug
		System.out.printf("%s visitado.\n", toString());
		return;
	}

	// -----------------------------------------------------------

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public VertexType getType() {
		return type;
	}

	public void setType(VertexType type) {
		this.type = type;
	}

	public Vertex getParent() {
		return parent;
	}

	public void setParent(Vertex parent) {
		this.parent = parent;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}
}
