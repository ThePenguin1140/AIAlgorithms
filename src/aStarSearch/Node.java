package aStarSearch;
import java.util.Comparator;


public class Node implements Comparable<Node>{
	private Node parent;
	private TilePuzzle state;
	private int pathCost;
	Action action;
	
	public Node(Node current, Action action){
		this.pathCost=current.getPathCost();
		this.parent = current;
		this.state = new TilePuzzle(current.getState());
		this.action=action;
		TilePuzzle state = move(action);
		if(state!=null){
			this.pathCost++;
		}
		else{
			//System.out.println("Child Node not generated");
			this.state=null;
			this.parent=null;
		}
	}
	
	public Node(TilePuzzle initial){
		parent = null;
		state = initial;
		pathCost=0;
	}
	
	public TilePuzzle move(Action action){
		TilePuzzle newState = null;
		switch(action){
		case UP:
			newState = state.moveUp();
			//if(newState==null)
			//	System.out.println("Tried UP");
			break;
		case DOWN:
			newState = state.moveDown();
			//if(newState==null)
			//	System.out.println("Tried DOWN");
			break;
		case LEFT:
			newState = state.moveLeft();
			//if(newState==null)
			//	System.out.println("Tried LEFT");
			break;
		case RIGHT:
			newState = state.moveRight();
			//if(newState==null)
			//	System.out.println("Tried RIGHT");
			break;
		default:
			System.out.println("Incorrect action!");
			break;
		}
	//	if(newState!=null)
		//	System.out.println(newState);
		return newState;
	}
	
	public int getPathCost(){
		return pathCost;
	}
	
	public Node getParent(){
		return parent;
	}
	
	public TilePuzzle getState(){
		return state;
	}
	
	public boolean equals(Node node){
		return state.equals(node.getState());
	}
	
	public String toString(){
		String output = state.toString();
		output+=action;
		output+=pathCost;
		return output;
	}

	public int compareTo(Node otherNode) {
		Integer thisFScore = pathCost + state.displacedTiles;
		Integer otherFScore = otherNode.getPathCost() + otherNode.getState().getDisplacedTiles();
		return thisFScore.compareTo(otherFScore);
	}
	
	public static Comparator<Node> NodeComparator = new Comparator<Node>(){
		public int compare(Node node1, Node node2){
			return node1.compareTo(node2);
		}
	};
}
