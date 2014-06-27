package grafos;

public class Vertex extends VertexAbstract<Vertex> {
	private int x, y;
	private TypeVertex type;
		
	public Vertex(Vertex v) {
		this(v.getX(), v.getY(), v.getType());
	}
	
	public Vertex(int x, int y){
		this(x, y, TypeVertex.FLOOR);
	}

	public Vertex(int x, int y, TypeVertex type) {
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

	// -----------------------------------------------------------

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public TypeVertex getType() {
		return type;
	}

	public void setType(TypeVertex type) {
		this.type = type;
	}
}
