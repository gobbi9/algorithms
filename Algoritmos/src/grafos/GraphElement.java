package grafos;

public abstract class GraphElement {
	protected static int objCounter = 0;
	protected int id;

	protected GraphElement() {
		id = GraphElement.objCounter++;
	}

	public boolean equals(Object obj){
		GraphElement v = (GraphElement) obj;
		return this.id == v.id;
	}
	
	public int getId() {
		return this.id;
	}

	public abstract GraphElement clone();
}
