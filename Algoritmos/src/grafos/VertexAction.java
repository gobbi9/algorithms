package grafos;

public interface VertexAction<Tv extends AbstractVertex<Tv>> {
	public abstract void action(Tv v);
}
