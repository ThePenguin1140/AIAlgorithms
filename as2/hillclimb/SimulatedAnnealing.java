package hillclimb;

import java.util.Random;

import problem.Node;
import problem.Problem;
import problem.State;
import problem.Schedule;

public class SimulatedAnnealing{
	public static <T extends State & Problem<? extends T>> T solve(Problem<T> problem, T initialState, Schedule schedule){
		Node<T> current = new Node<T>(initialState);
		Random random = new Random();
		int time = 1;
		int counter = 0;
		do{
			counter++;
			float probablity = random.nextFloat();
			double temp = schedule.getTemp(time);
			if(temp==0){
				current.state.setTotalNodes(counter);
				return current.state;
			}
			Node<T> neighbor = randomSuccessor(current);
			int deltaE = current.value - neighbor.value;//reverse for general algorithm
			double exp = (double)deltaE/temp;
			double acceptedProbability = Math.pow(Math.E, exp);
			if(deltaE > 0)
				current = neighbor;
			else if(probablity<=acceptedProbability)
				current = neighbor;
			time++;
		}while(true);
	}
	
	protected static <T extends State & Problem<? extends T>> Node<T> randomSuccessor(Node<T> current){
		return current.randomSuccessor(current.state);
	}
}
