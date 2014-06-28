package grafos;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractVertex<T extends GraphElement> extends GraphElement {
	protected List<T> neighbors;
	protected boolean visited;
	
	protected AbstractVertex(){
		super();
		neighbors = null;
		visited = false;
	}
		
	public void add(T sibling){
		if (neighbors == null)
			neighbors = new ArrayList<T>();
		neighbors.add(sibling);		
	}
	
	public T getSiblingById(int id){
		for (T vertex : neighbors) {
			T v = vertex;
			if (v.getId() == id)
				return v;
		}
		return null;
	}
	
	public T get(T s){
		for (T v : neighbors)
			if (v.equals(s))
				return v;
				
		return null;
	}
	
	public int getDegree() {
		return neighbors.size();		
	}
	
	public String neighborsToString(){
		String output = "";
		for (T v : neighbors)
			output += v.toString() + " ";
		return output;
	}
	
	public void setNeighbors(List<T> neighbors){
		this.neighbors = neighbors;
	}
	
	public List<T> getNeighbors(){
		return neighbors;
	}
	
	public String toString(){
		return id + "";
	}
	
	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}
}
