package trees;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;

import algoutil.Util;

public class Tree {

	private List<Node> nodes;
	private List<Integer> pathIds;
	
	private static String options = "edges: {\n},\n stabilize: false,\nsmoothCurves: false,\nhierarchicalLayout: {\ndirection: \"UD\"\n }";
	private static int idPage = 0;
	
	public Tree(){
		nodes = new ArrayList<Node>();
		pathIds = new ArrayList<Integer>();
	}
	
	public void addNode(Node node){
		for (Node n : nodes)
			if (n.equals(node))
				return;
		nodes.add(node);
	}
	
	public Node getRoot(){
		return nodes.get(0);
	}
	
	public Node getNodeByValue(int value){
		try{
			return nodes.stream().filter(n -> n.getValue() == value).findAny().get();
		}
		catch (NoSuchElementException e){
			return null;
		}
	}
	
	public List<Integer> getPathIds() {
		return pathIds;
	}

	public Tree to(int value){
		nodes.forEach(n -> n.setOnThePath(false));
		Node end = getNodeByValue(value);
		
		while (end != null){
			end.setOnThePath(true);
			pathIds.add(end.getValue());
			end = end.getParent();
		}
		return this;
	}
	
	public void toHtml() {
		toHtml(this.nodes);
	}

	public void toHtml(List<Node> nodes) {
		// page title - eyecandy
		final String TITLE = "Hello Simple Graph!";
		// template file
		final String TEMPL_PATH = "files/templateTree.html";

		StringBuffer nodesBuffer = new StringBuffer();
		StringBuffer edgesBuffer = new StringBuffer();

		for (Node node : nodes) {
			String[] output = node.toHtml();
			nodesBuffer.append(output[0]);
			edgesBuffer.append(output[1]);
		}
		
		Scanner scan;
		try {
			scan = new Scanner(new File(TEMPL_PATH));
		}
		catch (FileNotFoundException e1) {
			scan = null;
			e1.printStackTrace();
		}
		// final file's buffer
		StringBuffer htmlBuf = new StringBuffer();
		String line;

		// replaces the flags
		while (scan.hasNextLine()) {
			line = scan.nextLine();
			if (line.contains("$data")) {
				htmlBuf.append(nodesBuffer);
				continue;
			}
			if (line.contains("$nodes"))				
				line = line.replace("$nodes", nodesBuffer.toString());
			else if (line.contains("$edges"))
				line = line.replace("$edges", edgesBuffer.toString());
			else if (line.contains("$title"))
				line = line.replace("$title", TITLE);
			else if (line.contains("$options"))
				line = line.replace("$options", options);
			htmlBuf.append(line + '\n');
		}

		scan.close();

		String fileName = "output/oTree" + idPage + ".html";
		idPage++;

		try {
			Files.write(Paths.get(fileName), htmlBuf.toString().getBytes());
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		Util.runInFirefox(fileName);

	}
	
	public boolean equals(Object o){
		Tree anotherTree = (Tree) o;
		if (anotherTree.nodes.size() != nodes.size())
			return false;
		List<Node> list1 = anotherTree.nodes.stream().sorted((u,v) -> {return u.getValue() - v.getValue();}).collect(Collectors.toList());
		List<Node> list2 = nodes.stream().sorted((u,v) -> {return u.getValue() - v.getValue();}).collect(Collectors.toList());
		for (int i=0; i<list1.size(); i++){
			Node nodeA = list1.get(i);
			Node nodeB = list2.get(i);
			if (nodeA.getValue() != nodeB.getValue())
				return false;
			else{
				if (nodeA.getChildren().size() != nodeB.getChildren().size())
					return false;
				else{
					List<Node> childrenA = nodeA.getChildren().stream().sorted((u,v) -> {return u.getValue() - v.getValue();}).collect(Collectors.toList());
					List<Node> childrenB = nodeB.getChildren().stream().sorted((u,v) -> {return u.getValue() - v.getValue();}).collect(Collectors.toList());
					for (int j=0; j<childrenA.size(); j++){
						if (childrenA.get(j).getValue() != childrenB.get(j).getValue())
							return false;
					}
				}
			}
		}
				
		return true;
	}
}
