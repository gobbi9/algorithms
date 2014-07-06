package graphs.abstracts;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractEdge<V extends AbstractVertex<V>> extends GraphElement {
	protected List<V> vertexes;
	protected int weight;
	protected static final int MIN_WEIGHT = 1;
	
	public AbstractEdge(){
		this(MIN_WEIGHT);
	}
	
	public AbstractEdge(int weight) {
		super();
		vertexes = new ArrayList<V>();
		this.weight = weight;
		visited = false;
		onThePath = false;
	}

	public boolean contains(V v){
		if (getA().equals(v) || getB().equals(v))
			return true;
		return false;
	}
	
	public int hashCode(){
		final int prime = 13;
		int result = 1;
		result = prime * result + vertexes.hashCode();
		return result;
	}
	
	public boolean equals(Object obj) {
		AbstractEdge<?> e = (AbstractEdge<?>) obj;
		if (this.vertexes.get(0).equals(e.vertexes.get(0)) && this.vertexes.get(1).equals(e.vertexes.get(1)))
			return true;
		if (this.vertexes.get(0).equals(e.vertexes.get(1)) && this.vertexes.get(1).equals(e.vertexes.get(0)))
			return true;
		
		return false;
	}
	
	public String toString() {
		return String.format("[%s, %s](%d)", vertexes.get(0).toString(),
				vertexes.get(1).toString(), weight);
	}
	
	public String toHtml() {
		// returns a string in the format:
		// A -- B[label=x];
		// where A and B are vertices, and x is the weight of the edge 
		String s = String.format("%d %s %d%s;\n", 
				getA().getId(), "--", 
				getB().getId(),
				getWeight() > MIN_WEIGHT ? "[label="+getWeight()+"]" : "");
		if (getA().isOnThePath())
			s += String.format("%d [fontColor=white,color=red]\n", getA().getId());
		if (getB().isOnThePath())
			s += String.format("%d [fontColor=white,color=red]\n", getB().getId());
		
		return s;
	}
	
	public V getA(){
		return vertexes.get(0);		
	}
	
	public V getB(){
		return vertexes.get(1);		
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
}
