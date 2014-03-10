package hillclimb;

import problem.Node;
import problem.Problem;
import problem.State;

public class RandomResartHillClimbing extends HillClimb {
	public static <T extends State & Problem<? extends T>> T solve(Problem<T> problem, T initialState){
		Node<T> current = new Node<T>(initialState);
		int counter =0;
		do{
			counter++;
			Node<T> neighbor = highestValueSuccessor(current);
			if(neighbor.value > current.value || current.value == 0){
				current.state.setTotalNodes(counter);
				return current.state;
			}
			else if(neighbor.value == current.value){
				T newInstance = null;
				try {
					newInstance = problem.getGeneric().newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				neighbor = new Node<T>(newInstance);
			}
			current = neighbor;
		}while(true);
	}
}
