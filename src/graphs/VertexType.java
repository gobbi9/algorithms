package graphs;

public enum VertexType{
	FLOOR(0),
	WALL(1),
	START(2),
	END(3);
	
	private int type;
	
	private VertexType(int type){
		this.setType(type);
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
