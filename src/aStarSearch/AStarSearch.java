package aStarSearch;

import java.util.ArrayList;
import java.util.Collections;

public class AStarSearch{
	
	public static ArrayList<Node> search(Node initialState, Node goalState, Heuristic heuristic){
    //frontier of nodes to be explored
		ArrayList<Node> frontier = new ArrayList<Node>();
    //list of nodes that have been explored
		ArrayList<Node> explored = new ArrayList<Node>();
    //adding root node to frontier
		frontier.add(initialState);
		System.out.println(initialState.getState());

    //while the frontier is not empty continue the search
		while(!frontier.isEmpty()){
      //pick the best node from the frontier
			Node current = chooseNode(frontier, heuristic);
      //and remove it from the frontier
			frontier.remove(current);
      //if the node is the goal state then print the solution path
			if(current.equals(goalState)){
            ArrayList<Node> solutionPath = solutionPath(current);
            System.out.println("Depth: "+solutionPath.size());
            System.out.println("Nodes Frontier: "+frontier.size());
            System.out.println("Nodes Explored: "+explored.size());
				return solutionPath;
			}
      //if the frontier contains the current node or the explored set contains the current node
      //then don't expand the node
			if(containsNode(frontier, current) || containsNode(explored, current))
				continue;
      //otherwise add the node to explored
			explored.add(current);
      //and expand it, add the children to the frontier
			frontier.addAll(expand(current));		
		}
		//implement Exception for empty frontier here
		return null;
	}
	
  /*
   * Expands the 'current' node.
   * 
   * Returns a list containing all the direct children of the Node.
   */
	private static ArrayList<Node> expand(Node current){
		ArrayList<Node> tempList = new ArrayList<Node>();		
		for(Action action: Action.values()){
			Node childNode = new Node(current, action);
			if(childNode.getState()!=null){
				tempList.add(childNode);
			}
		}		
		return tempList;
	}
	
  /*
   * Iterates up a branch to find the solution path.
   */
	private static ArrayList<Node> solutionPath(Node current){
		ArrayList<Node> solutionPath = new ArrayList<Node>();
		while(current.getParent()!=null){
			solutionPath.add(current);
			current=current.getParent();
		}
		Collections.reverse(solutionPath);

		return solutionPath;
	}
	
  /*
   * Picks the most favorable node from the frontier based on the supplied heursitic.
   */
	private static Node chooseNode(ArrayList<Node> frontier, Heuristic heuristic){
		Node selectedNode=frontier.get(0);
		int fScore=0;
		switch(heuristic){
      //if using H1 heuristic
			case H1:
        //add the pathcost to the number of displaced tiles
				fScore=frontier.get(0).getPathCost()+frontier.get(0).getState().getDisplacedTiles();
        //do this for all frontier nodes,and find the min
				for(int i=1;i<frontier.size();i++){
					if(frontier.get(i)!=null){
						int score = frontier.get(i).getPathCost()+frontier.get(i).getState().getDisplacedTiles();
						if(score<fScore){
							selectedNode=frontier.get(i);
							fScore=score;
						}	
					}
				}
				break;
			case H2:
        // if using H2 heuristic then get the path cost and computer the approximate distnace to the goal state
				fScore=frontier.get(0).getPathCost()+frontier.get(0).getState().getDistanceToGoal();
        // do this for all nodes in the frontier and find the min
				for(int i=1; i<frontier.size();i++){
					if(frontier.get(i)!=null){
						int score = frontier.get(i).getPathCost()+frontier.get(i).getState().getDistanceToGoal();
						if(score<fScore){
							selectedNode=frontier.get(i);
							fScore=score;
						}
					}
				}
				break;
			default:
				System.out.println("Node Expansion Error!");
				break;
		}
		//System.out.println(fScore);
		return selectedNode;
	}
	
	private static boolean containsNode(ArrayList<Node> array, Node node){
		boolean contains=false;
		for(int i=0; i<array.size(); i++){
			if(array.get(i).equals(node))
				contains = true;
		}
		return contains;
	}
}
