package grafos;

import java.util.ArrayList;
import java.util.List;

//TODO <T extends VertexAbstract<?> tbm funciona, ver qual é o melhor dps
//PQ isso?? Resposta: agora ele não reclama mais de List<Vertex> ou List<VertexAbstract>
//pois na verdade ele espera um tipo T que extenda GraphElement
//no caso aceitaria Edge e EdgeAbstract tbm em alguns lugares q não deveria por isso são necessário mais testes 
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
	
	//getSibling by ID
	public T getById(int id){
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
