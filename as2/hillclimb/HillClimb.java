package hillclimb;

import problem.Problem;
import problem.Node;
import problem.State;

public class HillClimb {
	public static <T extends State & Problem<? extends T>> T solve(Problem<T> problem, T initialState){
		Node<T> current = new Node<T>(initialState);
		do{
			Node<T> neighbor = highestValueSuccessor(current);
			if(neighbor.value <= current.value)
				return current.state;
			current = neighbor;
		}while(true);
	}

	private static <T extends State & Problem<? extends T>> Node<T> highestValueSuccessor(Node<T> current) {
		return current.highestValueSuccessor(current.state);
	}
}
