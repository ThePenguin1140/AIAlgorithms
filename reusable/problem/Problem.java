package problem;

public interface Problem<T extends State> {
//	public State initialState = (State)new Object();
	public Object initialState = new Object();
	public enum Actions{};
	
	public T Result(Actions Action);
	
	public T highestValueSuccessor();
	
	public T randomSuccessor();
	
	public Class<? extends T> getGeneric();
}
