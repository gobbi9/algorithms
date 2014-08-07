package trees;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private Node parent;
	private List<Node> children;
	private int value;
	private boolean visited;
	private boolean onThePath;
	private int level;
	private int eccentricity;
	
	public Node(){
		this(0);
	}
	
	public Node(int value){
		this(value, 0);
	}
	
	public Node(int value, int level){
		children = new ArrayList<Node>();
		parent = null;
		this.value = value;
		this.level = level;
		eccentricity = 0;
	}
	
	public void addChild(Node node){
		node.setParent(this);
		children.add(node);
	}

	public String[] toHtml() {
		String nodes = "";
		String edges = "";

		nodes += String.format("{id: %d, label: '%s', level: %d%s},\n", 
				this.getValue(), this.getValue()+"", this.getLevel(), 
				onThePath ? " ,fontColor: '#FFFFFF', color: '#FF0000'" : "");
		
		for (Node child : this.children)
			edges += String.format("{from: %d, to: %d%s},\n", this.getValue(), child.getValue(),
					this.isOnThePath() && child.isOnThePath() ? " ,color: '#FF0000'" : "");
		
		return new String[]{nodes, edges};
	}
	
	public String toString(){
		return value+"";
	}
	
	public int hashCode(){
		return value;
	}
	
	public boolean equals(Object anotherNode){
		Node node = (Node) anotherNode;
		return node.value == this.value;
	}
	
	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public List<Node> getChildren() {
		return children;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean isOnThePath() {
		return onThePath;
	}

	public void setOnThePath(boolean onThePath) {
		this.onThePath = onThePath;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getEccentricity() {
		return eccentricity;
	}

	public void setEccentricity(int eccentricity) {
		this.eccentricity = eccentricity;
	}
}
