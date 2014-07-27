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
	
	public Node(){
		this(0);
	}
	
	public Node(int value){
		children = new ArrayList<Node>();
		parent = null;
		id = counter++;
		this.value = value;
	}
	
	public void addChild(Node node){
		children.add(node);
	}
	
	public String toHtml() {
		String s = "";
		for (Node child : children){
			s += String.format("%d %s %d[%s];\n", 
					this.getId(), "--", 
					child.getId(),
					this.isOnThePath() && child.isOnThePath() ? "color=red" : "color=gray");			
		}

		if (this.isOnThePath())
			s += String.format("%d [fontColor=white,color=red]\n", this.getId());
		
		return s;
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

	public void setChildren(List<Node> children) {
		this.children = children;
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
}
