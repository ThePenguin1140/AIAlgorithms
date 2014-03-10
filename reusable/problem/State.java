package problem;

public interface State extends Comparable<State>{
	public Object state = new Object(); 
	public int value = 0;
	public int totalNodes = 0;
	
	public int value();
	
	public void setTotalNodes(int total);
}
