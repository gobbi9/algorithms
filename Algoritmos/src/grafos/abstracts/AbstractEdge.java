package grafos.abstracts;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractEdge<T extends AbstractVertex<T>> extends GraphElement {
	protected List<T> vertexes;
	protected int weight;
	
	public AbstractEdge(){
		this(1);
	}
	
	public AbstractEdge(int weight) {
		super();
		vertexes = new ArrayList<T>();
		this.weight = weight;
	}

	public boolean contains(T v){
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
	
	public T getA(){
		return vertexes.get(0);		
	}
	
	public T getB(){
		return vertexes.get(1);		
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
}
