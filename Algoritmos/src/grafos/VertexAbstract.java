package grafos;

import java.util.ArrayList;
import java.util.List;

public abstract class VertexAbstract<T extends GraphElement> extends GraphElement {
	protected List<T> siblings;
	protected boolean visited;
	
	protected VertexAbstract(){
		super();
		siblings = null;
		visited = false;
	}
		
	public void add(T sibling){
		if (siblings == null)
			siblings = new ArrayList<T>();
		siblings.add(sibling);		
	}
	
	public T getSiblingById(int id){
		for (T vertex : siblings) {
			T v = vertex;
			if (v.getId() == id)
				return v;
		}
		return null;
	}
	
	public T get(T s){
		for (T v : siblings)
			if (v.equals(s))
				return v;
				
		return null;
	}
	
	public int getDegree() {
		return siblings.size();		
	}
	
	public String siblingsToString(){
		String output = "";
		for (T v : siblings)
			output += v.toString() + " ";
		return output;
	}
	
	public void setSiblings(List<T> siblings){
		this.siblings = siblings;
	}
	
	public List<T> getSiblings(){
		return siblings;
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
