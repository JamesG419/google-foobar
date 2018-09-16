package googleProject5;

import java.util.Arrays;
//Queue to do
public class Answer2 {
	
	public static void main(String args[]){
		int[] start = {0,17,0};
		int[] length = {3,4,44721};
		
		int sol[] = new int[start.length];
		
		for (int i= 0;i<sol.length;i++){
			sol[i] = answer(start[i],length[i]);
		}
		
		System.out.println(Arrays.toString(sol));
	}
	
	public static int answer(int start, int length) { 

        // Your code goes here.
		
		//initialize
		int checksum = 0;
		int current = start;
		int size = length - 1;
		
		for (int i = 0;i<length;i++){
			for(int j = 0;j<length;j++){
				if(j<=size){
					checksum ^= current;
				}
				current++;
			}
			size--;
		}
		
		return checksum;

	}
}
