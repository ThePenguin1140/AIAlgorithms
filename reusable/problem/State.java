package problem;

/*
 * interface that needs to be implemented in order for
 * algorithms to be able to solve a problem
 */
public interface State extends Comparable<State>{
	public Object state = new Object(); 
	public int value = 0;
	public int totalNodes = 0;
	
	/*
	 * Calculates the heuristic value of the problem,
	 * used to determine the slope of the successor
	 */
	public int value();
	
	/*
	 * used to set the number of total nodes generated 
	 * by this problem
	 */
	public void setTotalNodes(int total);
}
