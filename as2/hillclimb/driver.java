package hillclimb;


public class driver {
	public static void main(String[] args){
		System.out.println("Searching...");
	//	for(int i=0; i<10; i++){
      while(true){
			EightQueens problem = new EightQueens();
        // System.out.println(problem);
			EightQueens solution = HillClimb.solve(problem, problem.initialState);
       //  System.out.println(solution);
    //     System.out.println(solution.value());
			if(solution.value()==0){
				System.out.println(solution);
            System.out.println(solution.value());
				System.out.println("--------------------------------------");
				break;
			}
		}
	}
}
