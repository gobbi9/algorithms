package grafos;

import grafos.abstracts.AbstractVertex;

public class MazeVertex extends AbstractVertex<MazeVertex>{
	private VertexType type;
	private int x, y;
	
	public MazeVertex(MazeVertex v) {
		this(v.getX(), v.getY(), v.getType());
	}
	
	public MazeVertex(int x, int y){
		this(x, y, VertexType.FLOOR);
	}

	public MazeVertex(int x, int y, VertexType type) {
		super();
		this.x = x;
		this.y = y;
		this.type = type;
	}

	public String toString(){
		return String.format("(%d,%d)", x, y);		
	}
	
	public boolean equals(Object obj) {
		MazeVertex v = (MazeVertex) obj;
		return (v.getX() == this.x && v.getY() == this.y);
	}
	
	public MazeVertex clone() {
		MazeVertex vertexCopy = new MazeVertex(x,y);
		return vertexCopy;
	}
	
	public int hashCode() {
		final int prime = 37;
		int result = 1;
		result = prime * result;
		return result;
	}
	
	public MazeVertex get(int x, int y) {
		for (MazeVertex vertex : neighbors) {
			if (vertex.getX() == x && vertex.getY() == y)
				return vertex;
		}
		return null;
	}
	
	public VertexType getType() {
		return type;
	}

	public void setType(VertexType type) {
		this.type = type;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
}
