package grafos;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractEdge<T extends GraphElement> extends GraphElement {
	protected List<T> vertexes;

	public AbstractEdge() {
		super();
		vertexes = new ArrayList<T>();
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
		return String.format("(%s, %s)", vertexes.get(0).toString(),
				vertexes.get(1).toString());
	}
	
	public T getA(){
		return vertexes.get(0);		
	}
	
	public T getB(){
		return vertexes.get(1);		
	}
	
}