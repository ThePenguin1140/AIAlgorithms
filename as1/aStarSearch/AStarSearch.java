package aStarSearch;

import java.util.ArrayList;
import java.util.Collections;

public class AStarSearch{
	
	public static ArrayList<Node> search(Node initialState, Node goalState, Heuristic heuristic){
		ArrayList<Node> frontier = new ArrayList<Node>();
		ArrayList<Node> explored = new ArrayList<Node>();
		frontier.add(initialState);
		System.out.println(initialState.getState());

		while(!frontier.isEmpty()){
			//Collections.sort(frontier, Node.NodeComparator);
			Node current = chooseNode(frontier, heuristic);
			//Node current = frontier.get(0);
			//System.out.println(current);
			//System.out.println(frontier.size());
			frontier.remove(current);
			if(current.equals(goalState)){
            ArrayList<Node> solutionPath = solutionPath(current);
            System.out.println("Depth: "+solutionPath.size());
            System.out.println("Nodes Frontier: "+frontier.size());
            System.out.println("Nodes Explored: "+explored.size());
				return solutionPath;
			}
			if(containsNode(frontier, current) || containsNode(explored, current))
				continue;
			explored.add(current);
			frontier.addAll(expand(current));		
		}
		//implement Exception for empty frontier here
		return null;
	}
	
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
	
	private static ArrayList<Node> solutionPath(Node current){
		ArrayList<Node> solutionPath = new ArrayList<Node>();
		while(current.getParent()!=null){
			solutionPath.add(current);
			current=current.getParent();
		}
		Collections.reverse(solutionPath);

		return solutionPath;
	}
	
	private static Node chooseNode(ArrayList<Node> frontier, Heuristic heuristic){
		Node selectedNode=frontier.get(0);
		int fScore=0;
		switch(heuristic){
			case H1:
				fScore=frontier.get(0).getPathCost()+frontier.get(0).getState().getDisplacedTiles();
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
				fScore=frontier.get(0).getPathCost()+frontier.get(0).getState().getDistanceToGoal();
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
