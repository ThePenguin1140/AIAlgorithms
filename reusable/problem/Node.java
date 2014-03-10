package problem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/*
 * holds the information that is generated while an algorithm 
 * executes
 */

public class Node<T extends State & Problem<? extends T>> {
	// state of node
	public T state;
	// heuristic value of node
	public int value;
	
	// Constructor for any node
	public Node(T initialState) {
		this.state = initialState;
		this.value = initialState.value();
	}
	
	/*
	 * blank constructor
	 */
	public Node() {
		state = null;
		value = Integer.MAX_VALUE;
	}
	
	/*
	 * returns the child node of this node when action is applied 
	 */
	public Node<T> childNode(Node<T> parent, T.Actions action){
		return new Node<T>(parent.state.Result(action));	
	}

	/*
	 * returns the highest value Successor of this node
	 */
	public Node<T> highestValueSuccessor(T cur) {
		// if the problem does not have any actions listed
		// then call the problem to find it's own highest value successor
		if(Problem.Actions.values().length==0)
			return new Node<T>(cur.highestValueSuccessor());
		// CODE BELOW IS NOT TESTED, CANNOT GUARANTEE FUNCTIONALITY
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
	
	/*
	 * returns a random successor of this node
	 */
	public Node<T> randomSuccessor(T cur){
		// if the problem has not actions listed
		// call the problem to find it's own random successor
		if(Problem.Actions.values().length==0)
			return new Node<T>(cur.randomSuccessor());
		// CODE BELOW IS NOT TESTED, CANNOT GUARANTEE FUNCTIONALITY
		else{
			Random rd = new Random();
			int i = rd.nextInt(Problem.Actions.values().length);
			return new Node<T>(cur.Result(Problem.Actions.values()[i]));
		}
	}
}
