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

import algoutil.Util;

public class Tree {

	private List<Node> nodes;
	
	private static String options = "edges: {\n},\n stabilize: false,\nsmoothCurves: false,\nhierarchicalLayout: {\ndirection: \"UD\"\n }";
	private static int idPage = 0;
	
	public Tree(){
		nodes = new ArrayList<Node>();
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
	
	public void toHtml() {
		toHtml(this.nodes);
	}

	public void toHtml(List<Node> nodes) {
		// header
		final String HEAD = "digraph {\n" + "node [shape=circle fontSize=16]\n"
				+ "edge [length=100, color=gray, fontColor=black]\n";
		// footer - empty for now
		final String TAIL = "}\n";

		// page title - eyecandy
		final String TITLE = "Hello Simple Graph!";
		// template file
		final String TEMPL_PATH = "files/template.html";

		// creates the text structure in "dot" language
		StringBuffer dotBuf = new StringBuffer();

		dotBuf.append(HEAD);
		for (Node node : nodes) {
			dotBuf.append(node.toHtml());
		}
		dotBuf.append(TAIL);

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
				htmlBuf.append(dotBuf);
				continue;
			}
			if (line.contains("$title"))
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
}
