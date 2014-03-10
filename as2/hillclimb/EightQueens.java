package hillclimb;

import java.util.ArrayList;
import java.util.Random;

import problem.Problem;
import problem.State;

/*
 *  The problem that is to be solved by the algorithms,
 *  implements the required interfaces.
 *  Is solved when 8 queens are placed on a board without any attacking pairs
 *  the value of this problem is the number of attacking pairs.
 */


public class EightQueens implements Problem<EightQueens>, State{
	@SuppressWarnings("unused")
	private enum Actions{
		// move queen in column a any space in column a
		//left blank and highestValueSuccessor is implemented instead
	}
	public EightQueens initialState = null;
	public Integer[] state = new Integer[8];
	public int totalNodes = 0;
  
   /*
    * Instantiates a random 8 queens problem
    */
	public EightQueens(){
		Random random = new Random();
		ArrayList<Integer> tmp = new ArrayList<Integer>(); // array used for random initialization
		for(int i=0; i<state.length; i++){
			state[i]=Integer.valueOf(random.nextInt(8));
		}
		initialState = this;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see problem.State#value()
	 */
	public int value() {
		int total = 0;
		for(int i=0; i<state.length; i++){
			total += checkAttacking(i, state[i].intValue());
		}
		return total;
	}
	
	/*
	 * checks the whether the indicated queen attacks
	 * any other queen,
	 * takes the column and row of a queen on the board
	 */
	private int checkAttacking(int column, int row){
		int total = 0;
		total += checkRow(column, row);
		total += checkDiagonal(column, row);
		return total;
	}
	
	/*
	 * checks the whether the indicated queen attacks
	 * any other queen diagonally
	 */
	private int checkDiagonal(int column, int row) {
		int total = 0;
		for(int i=0; i<state.length; i++){
			int difference = Math.abs(column-i);
			// how far off horizontally would a queen have to be to be
			// diagonal in proportion to the parameter queen
			if(state[i]==row+difference || state[i]==row-difference)
				total++;
		}
      //may have to decrement total in this and checkRow 
      //to account for self counting
      // rare moment - I was right
		return total-1;
	}

	/*
	 * checks the whether the indicated queen attacks
	 * any other queen horizontally
	 */
	private int checkRow(int column, int row){
		int total = 0;
		for(int i=0; i<state.length; i++){
			if(state[i]==row)
				total ++;
		}
		return total-1;
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
	/*
	 * (non-Javadoc)
	 * @see problem.Problem#highestValueSuccessor()
	 */
	public EightQueens highestValueSuccessor() {
		EightQueens current = new EightQueens(); 
      // makes temporary object that can be edited
		EightQueens newState = new EightQueens();
      // makes states of objects identical
      for(int i=0; i<state.length; i++)
         newState.state[i]=current.state[i]=state[i];
      // for every queen on the board
		//for(int i: state){
      for(int i=0; i<state.length; i++){
         //reset state
         newState.state = copyState(state);
         // move it to any row in it's column
			for(int row=0; row<8; row++){
				newState.state[i] = row;
            // if the value of the current state then 
            // make the new state the current
				if(current.value()>newState.value())
					current.state = copyState(newState.state);
            // otherwise if they are equal (plateau or shoulder)
            // check if sideways moves are still allowed
            // if they are then make the current node the new one
            // and decrement the sideways counter
				//else if(current.value()==newState.value() && sideways > 0){
					//current = newState;
				//	sideways--;
				//}
			}
		}
		return current;
	}
	
	/*
	 * copies the state returning a new state object
	 */
	private Integer[] copyState(Integer[] parent){
		Integer[] tmp = new Integer[8];
		for(int i=0; i<parent.length; i++){
			tmp[i]=parent[i];
		}
		return tmp;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
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

	@Override
	/*
	 * (non-Javadoc)
	 * @see problem.Problem#getGeneric()
	 */
	public Class<? extends EightQueens> getGeneric() {
		// TODO Auto-generated method stub
		return this.getClass();
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see problem.Problem#randomSuccessor()
	 */
	public EightQueens randomSuccessor() {
		EightQueens newState = new EightQueens();
		newState.state = copyState(state);
		Random rand = new Random();
		int column = rand.nextInt(8);
		int row = rand.nextInt(8);
		newState.state[column] = Integer.valueOf(row);
		return newState;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see problem.State#setTotalNodes(int)
	 */
	public void setTotalNodes(int total) {
		// TODO Auto-generated method stub
		totalNodes=total;
	}
}
