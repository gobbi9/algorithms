package graphs.interfaces;

import graphs.abstracts.AbstractVertex;

public interface VertexAction<V extends AbstractVertex<V>> {
	public abstract void action(V v);
}
