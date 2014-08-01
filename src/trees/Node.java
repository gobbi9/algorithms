package trees;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private static int counter = 0;
	private Node parent;
	private List<Node> children;
	private int value;
	private int id;
	private boolean visited;
	private boolean onThePath;
	private int level;
	
	public Node(){
		this(0);
	}
	
	public Node(int value){
		children = new ArrayList<Node>();
		parent = null;
		id = counter++;
		this.value = value;
		level = 0;
	}
	
	public void addChild(Node node){
		node.setParent(this);
		children.add(node);
	}

	public String[] toHtml() {
		String nodes = "";
		String edges = "";

		nodes += String.format("{id: %d, label: '%s', level: %d},\n", this.getValue(), this.getValue()+"", this.getLevel());
		
		for (Node child : this.children)
			edges += String.format("{from: %d, to: %d},\n", this.getValue(), child.getValue());
		
		return new String[]{nodes, edges};
	}
	
	public String toString(){
		return value+"";
	}
	
	public int hashCode(){
		return id;
	}
	
	public boolean equals(Object anotherNode){
		Node node = (Node) anotherNode;
		return node.id == this.id;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
}
