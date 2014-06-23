package grafos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//TODO metodo equals
//TODO metodo clone deve fazer um "deepclone"
public class Graph {

	private List<Vertex> vertices;
	private List<Edge> edges;
	//adicionar metodo addVertex que deve verificar se o vertex jah existe 
	//adicionar metodo addEdge que deve verificar se o edge jah existe
	public Graph() {
		
	}

	public void teste() {
		Vertex a = new Vertex("a");
		Vertex b = new Vertex("b");
		Vertex c = new Vertex("c");
		Vertex d = new Vertex("d");
		Vertex e = new Vertex("e");

		vertices = Arrays.asList(a, b, c, d, e);
		
		Edge e1 = new Edge(a,b);
		Edge e2 = new Edge(a,c);
		Edge e3 = new Edge(a,e);
		Edge e4 = new Edge(b,c);
		Edge e5 = new Edge(c,d);
		Edge e6 = new Edge(e,d);
		
		edges = Arrays.asList(e1,e2,e3,e4,e5,e6);
		
	}
	
	public void teste2(){
		//montar a conexao a partir dos vertices e dos edges
		Vertex2 a = new Vertex2("a");
		Vertex2 b = new Vertex2("b");
		Vertex2 c = new Vertex2("c");
		Vertex2 d = new Vertex2("d");
		Vertex2 e = new Vertex2("e");
		
		a.setSiblings(Arrays.asList(b,c,e));
		b.setSiblings(Arrays.asList(a,c));
		c.setSiblings(Arrays.asList(b,a,d));
		d.setSiblings(Arrays.asList(b,c,e));
		e.setSiblings(Arrays.asList(a,d));
		
		System.out.println(a.get(d));
		System.out.println(a.get(b).get(c).get(d).get(e).siblingsToString());
		System.out.println(a.siblingsToString());
		
	}
	
	
	
	public String verticesToString(){
		String output = "";
		output += "{";
		if (vertices.size() > 0){
			output += vertices.get(0).toString();
			if (vertices.size() > 1)
				for (int i = 1; i<vertices.size(); i++)
					output += ", " + vertices.get(i).toString();
		}
		output += "}";
		return output;
	}
	
	public String edgesToString(){
		String output = "";
		output += "{";
		if (edges.size() > 0){
			output += edges.get(0).toString();
			if (edges.size() > 1)
				for (int i = 1; i<edges.size(); i++)
					output += ", " + edges.get(i).toString();
		}
		output += "}";
		return output;
	}
	
	public String toString(){
		String output = "";
		output += verticesToString();		
		output += ", ";
		output += edgesToString();
		return output;
	}	
}

class Vertex2 extends Vertex{
	private List<Vertex2> siblings;
	
	public Vertex2(String name){
		super(name);
		siblings = null;
	}
	
	public void addSibling(Vertex2 sibling){
		if (siblings == null)
			siblings = new ArrayList<Vertex2>();
		siblings.add(sibling);
		
	}
	
	public void setSiblings(List<Vertex2> siblings){
		this.siblings = siblings;
	}
	
	//get sibling by its name
	public Vertex2 get(String name){
		for (Vertex2 v : siblings)
			if (v.toString().equals(name))
				return v;
				
		return null;
	}
	
	//get sibling
	public Vertex2 get(Vertex2 s){
		for (Vertex2 v : siblings)
			if (v.equals(s))
				return v;
				
		return null;
	}
	
	public String siblingsToString(){
		String output = "";
		for (Vertex2 v : siblings)
			output += v.toString() + " ";
		return output;
	}
	
}