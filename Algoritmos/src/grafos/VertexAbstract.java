package grafos;

import java.util.ArrayList;
import java.util.List;

public abstract class VertexAbstract extends GraphElement {
	protected List<VertexAbstract> siblings;
	
	protected VertexAbstract(){
		super();
		siblings = null;
	}
		
	public void add(VertexAbstract sibling){
		if (siblings == null)
			siblings = new ArrayList<VertexAbstract>();
		siblings.add(sibling);		
	}
	
	//getSibling by ID
	public Vertex getById(int id){
		for (VertexAbstract vertex : siblings) {
			Vertex v = (Vertex) vertex;
			if (v.getId() == id)
				return v;
		}
		return null;
	}
	
	public VertexAbstract get(VertexAbstract s){
		for (VertexAbstract v : siblings)
			if (v.equals(s))
				return v;
				
		return null;
	}
	
	public int getDegree() {
		return siblings.size();		
	}
	
	public String siblingsToString(){
		String output = "";
		for (VertexAbstract v : siblings)
			output += v.toString() + " ";
		return output;
	}
	
	public void setSiblings(List<VertexAbstract> siblings){
		this.siblings = siblings;
	}
	
	public String toString(){
		return id + "";
	}
}
