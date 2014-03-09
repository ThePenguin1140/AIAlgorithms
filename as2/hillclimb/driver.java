package hillclimb;

import problem.Schedule;

public class driver {
	public static void main(String[] args){
		System.out.println("Searching...");
		int amount = 2;
		int counter =0;
		for(int i=0; i<amount; i++){
			EightQueens problem = new EightQueens();
			class MySched implements Schedule{

				@Override
				public int getTemp(int time) {
					return (int)Math.pow(Math.E, time);
				}
				
			}
			MySched schedule = new MySched();
			EightQueens solution = SimulatedAnnealing.solve(problem, problem.initialState, schedule);
			if(solution.value()==0){
				counter++;
			}
		}
		System.out.println(counter+" out of "+amount+" solved ");
		double percentage = (double)counter/amount;
		System.out.print(percentage*100+"%");
	}
}
