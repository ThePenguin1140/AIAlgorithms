package aStarSearch;
import java.util.*;

/**
 * 3 by 3 Sliding Block Puzzle
 * Instantiation creates a randomly generated Puzzle
 * The blank space (0) can be moved around using the moveUp, Down, Left, Right methods
 * Object implements the generation of two heuristic functions: 
 * the number of misplaced tiles
 * or the sum of the distances of each tile to it's respective goal position
 */

public class TilePuzzle{
	ArrayList<Integer> fieldValues = new ArrayList<Integer>();
	Integer[][] tiles = new Integer[3][3];
	int rowBlank, colBlank;
	public int displacedTiles;
	int distanceToGoal;
	
	public TilePuzzle(boolean random){
		for(int i=0; i<9; i++){
			fieldValues.add((Integer)i);
		}
		if(random)
			Collections.shuffle(fieldValues);
		Iterator<Integer> values = fieldValues.iterator();
		for(int row=0;row<3;row++){
			for(int col=0;col<3&&values.hasNext();col++){
				tiles[row][col]=values.next();
			}
		}
		if(!findZero())
			System.exit(0);
		generateHeuristics();
	}
	
	public TilePuzzle(TilePuzzle parent){
		for(int row = 0; row<3; row++){
			for(int col=0; col<3; col++){
				this.tiles[row][col]=new Integer(parent.tiles[row][col]);
			}
		}
		this.fieldValues = new ArrayList<Integer>();
		this.findZero();
		this.generateHeuristics();
	}

	public TilePuzzle moveUp(){
		if(swap(rowBlank-1,colBlank))
			return this;
		else
			return null;
	}

	public TilePuzzle moveDown(){
		if(swap(rowBlank+1,colBlank))
			return this;
		else
			return null;
	}

	public TilePuzzle moveLeft(){
		if(swap(rowBlank,colBlank-1))
			return this;
		else
			return null;
	}
	
	public TilePuzzle moveRight(){
		if(swap(rowBlank,colBlank+1))
			return this;
		else
			return null;
	}
	
	public int getDisplacedTiles() {
		generateHeuristics();
		return displacedTiles;
	}

	public int getDistanceToGoal() {
		generateHeuristics();
		return distanceToGoal;
	}
	
	public boolean equals(TilePuzzle puzzle){
		ArrayList<Integer> valueList, otherList;
		valueList = toArray();
		otherList = puzzle.toArray();
		// compares the two puzzles
		// if all items in the arrays are equal to each other then 
		// the puzzles are equal
		for (int i = 0; i < 9; i++) {
			if(!valueList.get(i).equals(otherList.get(i)))
				return false;
		}
		return true;
	}

	public void shuffle(int depth){
		int corner = 0;
		for(int i=0; i<depth; i++){	
			if(corner>3)
				corner=0;
			circularShuffle(corner);
			corner++;
		}
	}

	private void circularShuffle(int corner){
		switch(corner){
			case 0:
				this.moveDown();
				this.moveRight();
				this.moveUp();
				this.moveLeft();
				break;
			case 1:
				this.moveRight();
				this.moveRight(); // move to left corner
				this.moveDown(); // shuffle
				this.moveLeft();
				this.moveUp();
				this.moveRight();
				this.moveLeft();// move back
				this.moveLeft();
				break;
			case 2:
				this.moveDown(); //get into lower right corner
				this.moveDown();
				this.moveUp();	// shuffle
				this.moveRight();
				this.moveDown();
				this.moveLeft();
				this.moveUp(); //move back to upper right corner
				this.moveUp();
				break;
			case 3:
				this.moveDown();
				this.moveDown();
				this.moveRight();
				this.moveRight(); // move to lower left corner
				this.moveUp(); 	// shuffle
				this.moveLeft();
				this.moveDown();
				this.moveRight();
				this.moveLeft(); // move back to upper right corner
				this.moveLeft();
				this.moveUp();
				this.moveUp();
				break;
			default:
				//System.out.println("suffle error");
				break;
		}
	}

	private void generateHeuristics(){
		displacedTiles=distanceToGoal=0; // reset heuristic
		ArrayList<Integer> tilesAsList = toArray();
		for(Integer value: tilesAsList){
			if(value!=tilesAsList.indexOf(value))
				displacedTiles++;
			distanceToGoal+=toGoal(value, tilesAsList.indexOf(value));
		}
	}
	
	private int toGoal(int value, int index){
		return Math.abs(((value-index)%3))+//column offset
				Math.abs((value%3))-Math.abs((index%3));//row offset
	}
	
	private boolean swap(int row, int col){
		if(incorrectMove(row, col)){
			//System.out.println("Incorrect Move\nEdge Collision\n");
			return false;
		}
		tiles[rowBlank][colBlank]=tiles[row][col];
		tiles[row][col]=0;
		return findZero();
	}
	
	private boolean incorrectMove(int x, int y){
		if(x>2 || x<0 || y>2 || y<0)
			return true;
		else
			return false;
	}
	
	public ArrayList<Integer> toArray(){
		ArrayList<Integer> valueArray = new ArrayList<Integer>();
		for(int row=0; row<3; row++){
			for(int col=0; col<3; col++){
				valueArray.add(tiles[row][col]);
			}
		}
		return valueArray;
	}
	
	private boolean findZero(){
		boolean found=false;
		for(int row=0; row<3; row++){
			for(int col=0; col<3 && !found; col++){
				if(tiles[row][col]==0){
					if(found){
						System.out.println("Two or more blanks found!");
						return false;
					}
					rowBlank=row;
					colBlank=col;
					found=true;
				}
			}
		}
		if(!found){
			System.out.println("Blank not found");
			return false;
		}else
			return true;
	}
	
	public String toString(){
		String output = "";
		for(int row=0; row<3; row++){
			output+="|";
			for(int col=0; col<3; col++){
				output+=tiles[row][col]+"|";
			}
			output+="\n";
		}
		return output+"\n";
	}
}
