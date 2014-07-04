package graphs.interfaces;

public interface Element<T> {
	public abstract int getId();
	public abstract T clone();
	public abstract int hashCode();
	public abstract boolean equals (Object e);
}
