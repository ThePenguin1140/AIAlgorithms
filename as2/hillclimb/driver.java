package hillclimb;


public class driver {
	public static void main(String[] args){
		System.out.println("Searching...");
		int amount = 1000;
		int counter =0;
		for(int i=0; i<amount; i++){
//      while(true){
			EightQueens problem = new EightQueens();
        // 	System.out.println(problem);
			EightQueens solution = RandomResartHillClimbing.solve(problem, problem.initialState);
//         	System.out.println(solution);
//         	System.out.println(solution.value());
			if(solution.value()==0){
//				System.out.println(solution);
//				System.out.println(solution.value());
//				System.out.println("--------------------------------------");
				counter++;
//				break;
			}
		}
		System.out.println(counter+" out of "+amount+" solved ");
		double percentage = (double)counter/amount;
		System.out.print(percentage*100+"%");
	}
}
