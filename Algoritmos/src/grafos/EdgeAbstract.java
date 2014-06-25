package grafos;

public abstract class EdgeAbstract extends GraphElement{
	protected VertexAbstract[] vertexes;
	
	public EdgeAbstract(){
		super();
		vertexes = new VertexAbstract[2];
	}
	
	
	public String toString(){
		return String.format("(%s, %s)", vertexes[0].toString(), vertexes[1].toString());
	}	
}
