package graphs;

import graphs.abstracts.AbstractEdge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MazeEdge extends AbstractEdge<MazeVertex> {

	public MazeEdge(List<MazeVertex> vs) {
		this(vs.get(0), vs.get(1));
	}

	public MazeEdge(MazeVertex a, MazeVertex b) {
		super();
		vertexes = Arrays.asList(a,b);
	}
	
	protected List<MazeVertex> deepCopyList() {
		List<MazeVertex> copy = new ArrayList<MazeVertex>();
		copy.add(vertexes.get(0).clone());
		copy.add(vertexes.get(1).clone());
		return copy;
	}

	public MazeEdge clone() {
		return new MazeEdge(deepCopyList());
	}
	
	public String toHtml() {
		// returns a string in the format:
		// A -- B[label=x];
		// where A and B are vertices, and x is the weight of the edge 
		String s = String.format("%d %s %d%s;\n", 
				getA().getId(), "--", 
				getB().getId(),
				getWeight() > MIN_WEIGHT ? "[label="+getWeight()+"]" : "");
		
		return s;
	}

}
