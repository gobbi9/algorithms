package grafos;

public abstract class GraphElement {
	protected Integer id = 0;		
	
	protected GraphElement() {
		this.id++;
	}
	
	public boolean equals(Object obj){
		GraphElement v = (GraphElement) obj;
		return this.id.equals(v.id);
	}
	
	public abstract GraphElement clone();
}
