package hillclimb;

import problem.Problem;
import problem.Node;
import problem.State;

/*
 * Hill climbing algorithm from text book
 */
public class HillClimb {
	public static <T extends State & Problem<? extends T>> T solve(Problem<T> problem, T initialState){
		Node<T> current = new Node<T>(initialState);
		int sideways = 100;
		int counter = 0;
		do{
			counter++;
			Node<T> neighbor = highestValueSuccessor(current);
			if(neighbor.value > current.value){
				current.state.setTotalNodes(counter);
				return current.state;
			}
			else if(neighbor.value == current.value && sideways == 0){
				current.state.setTotalNodes(counter);
				return current.state;
			}
			if(neighbor.value == current.value)
				sideways--;
			current = neighbor;
		}while(true);
	}

	protected static <T extends State & Problem<? extends T>> Node<T> highestValueSuccessor(Node<T> current) {
		return current.highestValueSuccessor(current.state);
	}
}
