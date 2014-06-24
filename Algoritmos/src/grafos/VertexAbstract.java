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
	
	public VertexAbstract get(VertexAbstract s){
		for (VertexAbstract v : siblings)
			if (v.equals(s))
				return v;
				
		return null;
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
