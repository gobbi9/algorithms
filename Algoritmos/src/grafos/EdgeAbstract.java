package grafos;

import java.util.ArrayList;
import java.util.List;

public abstract class EdgeAbstract<T extends GraphElement> extends GraphElement {
	protected List<T> vertexes;

	public EdgeAbstract() {
		super();
		vertexes = new ArrayList<T>();
	}

	public boolean equals(Object obj) {
		EdgeAbstract<?> e = (EdgeAbstract<?>) obj;
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
