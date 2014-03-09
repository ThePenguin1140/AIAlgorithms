package hillclimb;

import java.util.ArrayList;
import java.util.Collections;

import problem.Problem;
import problem.State;

public class EightQueens implements Problem<EightQueens>, State{
	@SuppressWarnings("unused")
	private enum Actions{
		// move queen in column a http://137.149.157.7:8950/to any space in column a
		//left blank and highestValueSuccessor is implemented instead
	}
	public EightQueens initialState = null;
	public Integer[] state = new Integer[8];
  
   // TODO redo problem generation
   // Redo this to make problem generation more random by allowing 
   // queens to be in the same row
   // by shuffeling this is being prevented 
	public EightQueens(){
		ArrayList<Integer> tmp = new ArrayList<Integer>(); // array used for random initialization
		for(int i=0; i<8; i++){
			tmp.add(Integer.valueOf(i));
		}
		Collections.shuffle(tmp);
		for(Integer i: tmp){
			state[tmp.indexOf(i)]=i;
		}
		initialState = this;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see problem.State#value()
	 * Calculates the heuristic value of the problem,
	 * used to determine the slope of the successor
	 */
	public int value() {
		int total = 0;
		for(int i=0; i<state.length; i++){
			total += checkAttacking(i, state[i].intValue());
		}
		return total;
	}
	
	private int checkAttacking(int column, int row){
		int total = 0;
		total += checkRow(column, row);
		total += checkDiagonal(column, row);
		return total;
	}
	
	private int checkDiagonal(int column, int row) {
		int total = 0;
		for(int i=0; i<state.length; i++){
			int difference = Math.abs(column-i);
			if(state[i]==row+difference || state[i]==row-difference)
				total++;
		}
      //may have to decrement total in this and checkRow 
      //to account for self counting
		return total;
	}

	private int checkRow(int column, int row){
		int total = 0;
		for(int i=0; i<state.length; i++){
			if(state[i]==row)
				total ++;
		}
		return total;
	}

	@Override
	public int compareTo(State o) {
		return ((Integer)value).compareTo((Integer.valueOf(o.value())));
	}

	@Override
	public EightQueens Result(problem.Problem.Actions Action) {
		// not implemented since there are no enumerated actions for this problem
		return null;
	}

	@Override
	public EightQueens highestValueSuccessor() {
		//TODO rewrite to actually generate successors?
		//and comment this because there be no understanding
		EightQueens successor = this;
		EightQueens newState = new EightQueens();
		newState.state = state;
		int sideways = 200;
		for(int i: state){
			for(int row=0; row<8; row++){
				newState.state[i] = row;
				if(successor.value()<newState.value())
					successor = newState;
				else if(successor.value()==newState.value() && sideways > 0){
					successor = newState;
					sideways--;
				}
			}
		}
		return successor;
	}
	
	//TODO write to string for 8 queens state
	public String toString(){
		String output = "";
		for(int row=0; row<state.length; row++){
			for(int col=0; col<state.length; col++){
				if(state[col]==row)
					output+="[*]";
				else
					output+="[ ]";

			}
			output+="\n";
		}
		return output;
	}
}
