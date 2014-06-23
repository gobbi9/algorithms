package grafos;

public class Vertex {
	protected String name;
	
	public Vertex(String name){
		this.name = name;
	}
	
	//sujeito a mudança
	public boolean equals(Object obj){
		Vertex v = (Vertex) obj;
		return this.name.equals(v.name);
	}
	
	public Vertex clone(){
		return new Vertex(name+"");
	}
	
	public String toString(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
}
