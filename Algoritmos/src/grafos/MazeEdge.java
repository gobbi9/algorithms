package grafos;

import grafos.abstracts.AbstractEdge;

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

}
