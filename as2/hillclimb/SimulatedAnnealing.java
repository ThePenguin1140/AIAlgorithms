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
		do{
			float probablity = random.nextFloat();
			int temp = schedule.getTemp(time);
			if(temp==0)
				return current.state;
			Node<T> neighbor = randomSuccessor(current);
			int deltaE = neighbor.value - current.value;
			if(deltaE > 0)
				current = neighbor;
			else if(probablity<=Math.pow(Math.E, deltaE/temp))
				current = neighbor;
		}while(true);
	}
	
	protected static <T extends State & Problem<? extends T>> Node<T> randomSuccessor(Node<T> current){
		return current.randomSuccessor(current.state);
	}
}
