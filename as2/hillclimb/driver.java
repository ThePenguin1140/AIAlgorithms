package hillclimb;


public class driver {
	public static void main(String[] args){
		System.out.println("Searching...");
		while(true){
			EightQueens problem = new EightQueens();
			EightQueens solution = HillClimb.solve(problem, problem.initialState);
         System.out.println(solution.value());
			if(solution.value()==0){
				System.out.println(solution);
				System.out.println("--------------------------------------");
				break;
			}
		}
	}
}
