package grafos;

public class Vertex extends VertexAbstract {
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

	// get sibling by its position
	public Vertex get(int x, int y) {
		for (VertexAbstract vertex : siblings) {
			Vertex v = (Vertex) vertex;
			if (v.getX() == x && v.getY() == y)
				return v;
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
	
	/*public String toString(){
		return String.format("(%d,%d)", x, y);
	}*/

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
