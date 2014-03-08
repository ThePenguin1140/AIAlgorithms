package hillclimb;

import java.util.Timer;
import java.util.TimerTask;

public class driver {
	public static void main(String[] args){
		Timer tm = new Timer();
		tm.schedule(new TimerTask(){
			int count = 5;
			String loading = "Searching";
			@Override
			public void run() {
				loading+=".";
				count--;
				if(count==0){
					loading="Searching";
					count=5;
				}
				System.out.println(loading);
			}
		}, 0, 100);
		while(true){
			EightQueens problem = new EightQueens();
			EightQueens solution = HillClimb.solve(problem, problem.initialState);
			if(solution.value()==0){
				System.out.println(solution);
				System.out.println("--------------------------------------");
				break;
			}
		}
	}
}
