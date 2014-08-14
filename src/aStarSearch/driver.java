package aStarSearch;
import java.util.*;

public class driver {

	public static void main(String[] args){
		Node goalState = new Node(new TilePuzzle(false));
		Scanner sc = new Scanner(System.in);
	    System.out.println("Which heuristic would you like to use? (1:H1 2:H2");
	    int h = sc.nextInt();
	    Heuristic heuristic = Heuristic.H1;
	    if(h==1)
	        heuristic = Heuristic.H1; 
	    else if(h==2)
	        heuristic = Heuristic.H2;
	    //System.out.println(heuristic);
	    System.out.println("What would you like to solve\n"+ 
	        "1: One problem with true randomness\n"+
	        "2: 120 problems with increasing depth up to 24");
	    System.out.println("WARNING, A PROBLEM MAY TAKE VERY LONG TO SOLVE USING TRUE RANDOMNESS");
	    int tr = sc.nextInt();
	    if(tr==1){
	        Node problem = new Node(new TilePuzzle(true));   
	        ArrayList<Node> solution = AStarSearch.search(problem, goalState, heuristic);
	    }else{
	        for(int i=0; i<10; i++){
	        	for(int n=1; n<13; n++){
	        		TilePuzzle puzzle = new TilePuzzle(false);
	        		puzzle.shuffle(n);
			    	Node problem = new Node(puzzle);
			    	ArrayList<Node> solution = AStarSearch.search(problem, goalState,heuristic);
				}
        	}
      	}
   	}
}
