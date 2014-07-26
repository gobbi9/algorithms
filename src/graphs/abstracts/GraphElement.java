package graphs.abstracts;

import graphs.interfaces.Element;

public abstract class GraphElement implements Element<GraphElement>{
	protected static int objCounter = Element.START_INDEX;
	protected int id;
	protected boolean visited;
	protected boolean onThePath;
	
	protected GraphElement() {
		id = GraphElement.objCounter++;
	}
	
	public void visit() {
		this.visited = true;
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
	
	public boolean isOnThePath() {
		return onThePath;
	}

	public void setOnThePath(boolean onThePath) {
		this.onThePath = onThePath;
	}
}
