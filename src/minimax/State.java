package minimax;

public class State{
	public State parent = null;
	public State leftChild = null;
	public State midChild = null;
	public State rightChild = null;
	public Integer value = null;
	public String name = null;
	//public Integer[] actions = {-1,0,1};
	
	public State(int value){
		this.value = value;
	}
	
	public State(State left, State mid, State right){
		leftChild=left;
		midChild=mid;
		rightChild=right;
	}

	public boolean hasNoChildren() {
		// TODO Auto-generated method stub
		if(	leftChild==null &&
			rightChild==null &&
			midChild==null)
			return true;
		else
			return false;
	}
	
	public String toString(){
		if(name==null)
			return String.valueOf(value);
		return name;
	}
}
