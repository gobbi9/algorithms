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
		node.setLevel(this.level + 1);
		children.add(node);
	}
	/*
	   var nodes = [
    {id: 1, label: 'Node 1', level: 0}
    ,{id: 2, label: 'Node 2', level: 1}
    ,{id: 3, label: 'Node 3', level: 1}
    ,{id: 4, label: 'Node 4', level: 2}
    ,{id: 5, label: 'Node 5', level: 2}
    ,{id: 6, label: 'Node 6', level: 2}
  ];

  // create an array with edges
  var edges = [
    {from: 1, to: 2},
    {from: 1, to: 3},
    {from: 2, to: 4},
    {from: 2, to: 5},
    {from: 3, to: 6}
  ];
	
	*/
	public String[] toHtml() {
		String nodes = "";
		String edges = "";

		nodes += String.format("{id: %d, label: '%s', level: %d}\n", this.getId(), this.getId()+"", this.getLevel());
		
		for (Node child : this.children)
			edges += String.format("{from: %d, to: %d},\n", this.getId(), child.getId());
		
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
