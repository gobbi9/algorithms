package grafos.interfaces;

import grafos.abstracts.AbstractVertex;

public interface VertexAction<Tv extends AbstractVertex<Tv>> {
	public abstract void action(Tv v);
}
