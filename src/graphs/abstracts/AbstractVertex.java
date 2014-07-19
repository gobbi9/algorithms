package graphs.abstracts;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractVertex<T extends GraphElement> extends GraphElement {
	protected List<T> neighbors;
	protected T parent;
	protected int depth;
	protected int distance;
	protected int eccentricity;

	protected AbstractVertex() {
		super();
		neighbors = new ArrayList<T>();
		visited = false;
		onThePath = false;
		depth = 0;
		distance = 0;
		eccentricity = 0;
	}

	public void add(T sibling) {
		if (neighbors == null)
			neighbors = new ArrayList<T>();
		neighbors.add(sibling);
	}

	public T getSiblingById(int id) {
		// JAVA 8 test the case of not finding it
		// return neighbors.stream().filter(v -> v.getId() == id).findFirst().get();
		for (T vertex : neighbors) {
			T v = vertex;
			if (v.getId() == id)
				return v;
		}
		return null;
	}

	public T get(T s) {
		// JAVA 8 test the case of not finding it
		// return neighbors.stream().filter(v -> v.equals(s)).findFirst().get();
		for (T v : neighbors)
			if (v.equals(s))
				return v;

		return null;
	}
	
	public int getDegree() {
		return neighbors.size();
	}

	public String neighborsToString() {
		String output = "";
		for (T v : neighbors)
			output += v.toString() + " ";
		return output;
	}

	public String toString() {
		return id + "";
	}
	
	public abstract AbstractVertex<T> clone();
	
	// ----------------------------------------------------------------------------------- //
	
	public List<T> getNeighbors() {
		return neighbors;
	}
	
	public T getParent() {
		return parent;
	}

	public void setParent(T parent) {
		this.parent = parent;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getEccentricity() {
		return eccentricity;
	}

	public void setEccentricity(int eccentricity) {
		this.eccentricity = eccentricity;
	}
}
