package googleProject3;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Answer {

	public static void main(String args[]){
		System.out.println("The distance is: " + answer(0,59));
	}
	
	public static int answer(int src, int dest){
		//initialize
		Node source = new Node();
		Node destination = new Node();
		source.x = rowCalc(src,8);
		source.y = colCalc(src,8);
		destination.x = rowCalc(dest,8);
		destination.y = colCalc(dest,8);
		
		int distance = BFT(source,destination);
		return distance;
		
	}
	//BFT driver method
	public static int BFT(Node src, Node dest){
		//possible moves for the knight
		int[] rows = {1,2,2,1,-1,-2,-2,-1};
		int[] columns = {2,1,-1,-2,-2,-1,1,2};
		
		//hash to see if we've visited node before
		Map<Node,Boolean> visited = new HashMap<Node,Boolean>();
		//queue to handle visiting nodes
		Queue<Node> visiting = new LinkedList<>();
		
		visiting.add(src);
		
		while(!visiting.isEmpty()){
			Node node = visiting.remove();
			
			int x = node.x;
			int y = node.y;
			int distance = node.distance;
			
			if(node.isEqual(dest)){
				return distance;
			}
			
			if (visited.get(node) == null){
				visited.put(node, false);
			}
			
			if (!visited.get(node)){
				//say this node is now visited
				visited.put(node,true);
				
				//enqueue correct new positions
				for (int i = 0; i < 8; i++){
					int newX = x + rows[i];
					int newY = y + columns[i];
					
					if(isLegalMove(newX,newY,8)){
						Node newNode = new Node();
						newNode.x = newX;
						newNode.y = newY;
						newNode.distance = distance + 1;
						visiting.add(newNode);
					}
				}
				
			}
		}
		return -1;
	}
	
	//node class
	static class Node{
		int x;
		int y;
		int distance;
		
		public boolean isEqual(Node node){
			return x == node.x && y == node.y;
		}
	}
	
	//row calculator
	public static int rowCalc(int value, int size){
		int row = value/size;
		return row;
	}
	
	//column calculator
	public static int colCalc(int value, int size){
		int column = value%size;
		return column;
	}
	
	//is this move legal?
	public static boolean isLegalMove(int row,int col,int size){
		if (row >= 0 && col >= 0 && row < size && col < size){
			return true;
		}
		return false;
	}
	

}
