package grafos;

public class Vertex extends VertexAbstract {
	private int x, y;

	//TODO fazer enumerator para indicar o tipo de Vertex
	/*
	 * 0 -> nada
	 * 1 -> parede
	 * 2 -> inicio
	 * 3 -> fim
	 */
	
	public Vertex(Vertex v) {
		this(v.getX(), v.getY());
	}

	public Vertex(int x, int y) {
		super();
		this.x = x;
		this.y = y;
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
}
