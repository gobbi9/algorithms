package graphs.interfaces;

public interface Element<T> {
	public final int START_INDEX = 1;
	public abstract int getId();
	public abstract int hashCode();
	public abstract boolean equals (Object e);
}
