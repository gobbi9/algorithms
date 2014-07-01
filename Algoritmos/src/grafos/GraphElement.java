package grafos;

public abstract class GraphElement implements Element<GraphElement>{
	protected static int objCounter = 0;
	protected int id;

	protected GraphElement() {
		id = GraphElement.objCounter++;
	}

	public int hashCode(){
		return id;
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
