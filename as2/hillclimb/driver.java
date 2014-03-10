package hillclimb;

import problem.Schedule;

public class driver {
	public static void main(String[] args){
		hillClimg(1000);
		restart(1000);
		annealing(100);
	}
	
	private static void annealing(int amount){
		System.out.println("Searching using simulated Annealing...");
		int totalNodes =0;
		int counter =0;
		for(int i=0; i<amount; i++){
			EightQueens problem = new EightQueens();
			class MySched implements Schedule{

				@Override
				public double getTemp(int time) {
					//return (int)Math.pow(Math.E, -(time-10));
					return -((double)time/1000)+300;
				}
				
			}
			MySched schedule = new MySched();
			EightQueens solution = SimulatedAnnealing.solve(problem, problem.initialState, schedule);
			totalNodes+=solution.totalNodes;
			if(solution.value()==0){
				counter++;
			}
		}
		System.out.println(counter+" out of "+amount+" solved ");
		System.out.println(totalNodes/amount +" nodes were generated.");
		double percentage = (double)counter/amount;
		System.out.println(percentage*100+"%");
	}
	
	private static void restart(int amount){
		System.out.println("Searching using Random Restart Hill Climb...");
		int totalNodes=0;
		int counter =0;
		for(int i=0; i<amount; i++){
			EightQueens problem = new EightQueens();
			EightQueens solution = RandomResartHillClimbing.solve(problem, problem.initialState);
			totalNodes+=solution.totalNodes;
			if(solution.value()==0){
				counter++;
			}
		}
		System.out.println(counter+" out of "+amount+" solved ");
		System.out.println(totalNodes/amount +" nodes were generated.");
		double percentage = (double)counter/amount;
		System.out.println(percentage*100+"%");
	}
	
	private static void hillClimg(int amount){
		System.out.println("Searching using Hill Climb...");
		int totalNodes=0;
		int counter =0;
		for(int i=0; i<amount; i++){
			EightQueens problem = new EightQueens();
			EightQueens solution = HillClimb.solve(problem, problem.initialState);
			totalNodes+=solution.totalNodes;
			if(solution.value()==0){
				counter++;
			}
		}
		System.out.println(counter+" out of "+amount+" solved ");
		System.out.println(totalNodes/amount +" nodes were generated.");
		double percentage = (double)counter/amount;
		System.out.println(percentage*100+"%");
	}
}
