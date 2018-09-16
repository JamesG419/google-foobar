package googleProject4;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Queue;
//Bomb baby
public class Answer {
	
    public static String answer(String M, String F) { 

        // Your code goes here.
        try{
	        //parse and convert string to integer
			BigInteger m = new BigInteger(M);
			BigInteger f = new BigInteger(F);
			
			//send ints to Node
			Node root = new Node();
			Node end = new Node();
			
			root.m = BigInteger.valueOf(1);
			root.f = BigInteger.valueOf(1);
			end.m = m;
			end.f = f;
			
			String distance = BFT(root,end);
			
			return distance;
	       }
        catch (NumberFormatException ex){
        	return "impossible";
        }
    } 
	
	//breadth first search through tree
	public static String BFT(Node root, Node end){
		//queue to hold nodes
		Queue<Node> queue = new LinkedList<Node>();
		
		//add root node
		queue.add(root);
		
		//go through queue
		while(!queue.isEmpty()){
			//dequeue current node
			Node node = queue.remove();
			
			BigInteger m = node.m;
			BigInteger f = node.f;
			int distance = node.distance;
			
			//is this the right node?
			if(node.isEqual(end)){
				String returnDistance = Integer.toString(distance);
				return returnDistance;
			}
			
			//enque next legal moves
			BigInteger newM = m.add(f);
			BigInteger newF = f.add(m);
			
			if(isComboLegal(newM,f,end)){
				Node newNode = new Node();
				newNode.m = newM;
				newNode.f = f;
				newNode.distance= distance + 1;
				queue.add(newNode);
			}
			if(isComboLegal(m,newF,end)){
				Node newNode = new Node();
				newNode.m = m;
				newNode.f = newF;
				newNode.distance= distance + 1;
				queue.add(newNode);
			}
		}
		//if it cannot be done
		return "impossible";
	}

	//node object to hold bomb values in tree search
	static class Node{
		BigInteger m;
		BigInteger f;
		int distance;
		
		public boolean isEqual(Node node){
			return m.equals(node.m) && f.equals(node.f);
		}
		
		
		}
	//is that combo too large method
	public static boolean isComboLegal(BigInteger m, BigInteger f, Node end){
		BigInteger endM = end.m;
		BigInteger endF = end.f;
		
		if (m.compareTo(endM)<= 0 && f.compareTo(endF) <= 0){
			return true;
		}
		return false;
	}
}

