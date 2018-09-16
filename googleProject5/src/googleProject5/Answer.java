package googleProject5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//Queue to do
public class Answer {
	
		public static void main(String args[]){
			int[] start = {0,17};//,0};
			int[] length = {3,4};//,44721};
			
			int sol[] = new int[start.length];
			
			for (int i= 0;i<sol.length;i++){
				sol[i] = answer(start[i],length[i]);
			}
			
			System.out.println(Arrays.toString(sol));

			
		}
	    public static int answer(int start, int length) { 

	        // Your code goes here.
	    	
	    	//create matrix
	    	int[][] matrix = matrixCreate(start,length);
	    	
	    	//make checksum array
	    	List<Integer> checksum = new ArrayList<Integer>();
	    	checksum = checksum(matrix,length);
	    	int[] checksumArray = arrayList2array(checksum);
	    	
	    	//checksum calculation
	    	int solution = xorCalc(checksumArray);
	    	
	    	return solution;
    }
	    
	    //matrix creation and fill in
	    public static int[][] matrixCreate(int start, int size){
	    	//square matrix
	    	int n = size;
	    	int[][] matrix = new int[n][n];
	    	int currentInt = start;
	    	
	    	for (int i = 0; i<matrix.length; i++){
	    		for (int j = 0; j<matrix[i].length; j++){
	    			matrix[i][j] = currentInt;
	    			currentInt++;
	    		}
	    	}
	    	
	    	return matrix;
	    }
	    
	    //create and return the checksum array
	    public static List<Integer> checksum(int[][] matrix,int size){
	    	List<Integer> checksumArray = new ArrayList<Integer>();
	    	int counter = size;
	    	
	    	for (int i = 0; i< size;i++){
	    		for (int j = 0;j<counter;j++){
	    			checksumArray.add(matrix[i][j]);
	    		
	    		}
	    		counter--;
	    	}
	    	
	    	return checksumArray;
	    }
	    
	    //arraylist to array
	    public static int[] arrayList2array(List<Integer> list){
	    	int[] solution = new int[list.size()];
	    	for (int i = 0; i <list.size();i++){
	    		solution[i] = list.get(i);
	    	}
	    	return solution;
	    }
	    
	    //xor checksum
	    public static int xorCalc(int[] array){
	    	int current = array[0];
	    	
	    	for (int i = 1; i <array.length;i++){
	    		current = current ^ array[i];
	    	}
	    	
	    	return current;
	    }
}

