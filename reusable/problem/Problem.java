package problem;

/*
 * Interface used to define a problem that 
 * can be solved by AI search algorithms
 */
public interface Problem<T extends State> {
	// inital state of object
	public Object initialState = new Object();
	// actions possilbe on this state
	public enum Actions{};
	
	/*
	 * returns the state resulting by applying
	 * Action to this state
	 */
	public T Result(Actions Action);
	
	/*
	 * returns the highest value successor 
	 * to the current state
	 */
	public T highestValueSuccessor();
	
	/*
	 * returns a random successor to the current 
	 * state
	 */
	public T randomSuccessor();
	
	/*
	 * returns the generic class type of the problem
	 */
	public Class<? extends T> getGeneric();
}
