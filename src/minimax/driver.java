package minimax;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class driver {
	public static void main(String[] args){
		Scanner fileScanner = null;
    System.out.println(System.getProperty("user.dir"));
		try {
			fileScanner = new Scanner(new File("UtilityValues.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		State states[] = new State[3];
		for(int i=0; i<3 && fileScanner.hasNextInt(); i++){
			int value = fileScanner.nextInt();
			State left = new State(value);
			value = fileScanner.nextInt();
			State mid = new State(value);
			value = fileScanner.nextInt();
			State right = new State(value);
			states[i] = new State(left, mid, right);
		}
		states[0].name = "B";
		states[1].name = "C";
		states[2].name = "D";
		State head = new State(states[0], states[1], states[2]);
		head.name = "A";
		
		AlphaBetaPruning.alphaBetaSearch(head);
	}
}
