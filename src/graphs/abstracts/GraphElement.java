package graphs.abstracts;

import graphs.interfaces.Element;

public abstract class GraphElement implements Element<GraphElement>{
	protected static int objCounter = 0;
	protected int id;
	protected boolean visited;
	
	protected GraphElement() {
		id = GraphElement.objCounter++;
	}
	
	public void visit() {
		this.visited = true;
		// debug
		System.out.printf("%s visited.\n", toString());
		return;
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
	
	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public abstract GraphElement clone();
}
