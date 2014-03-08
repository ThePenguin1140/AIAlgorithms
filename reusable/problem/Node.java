package problem;

import java.util.ArrayList;
import java.util.Collections;

public class Node<T extends State & Problem<? extends T>> {
	public T state;
	public int value;

	public Node(T initialState) {
		this.state = initialState;
		this.value = initialState.value();
	}

	public Node() {
		state = null;
		value = Integer.MAX_VALUE;
	}
	
	public Node<T> childNode(Node<T> parent, T.Actions action){
		return new Node<T>(parent.state.Result(action));	
	}

	public Node<T> highestValueSuccessor(T cur) {
		if(Problem.Actions.values().length==0)
			return new Node<T>(cur.highestValueSuccessor());
		else{
			ArrayList<T> tmp = new ArrayList<T>(); 
			for(Problem.Actions action: Problem.Actions.values()){
				tmp.add(state.Result(action));
			}
			Collections.sort(tmp);
			Node<T> bestChoice = new Node<T>(tmp.get(0));
			return bestChoice;
		}
	}
}
