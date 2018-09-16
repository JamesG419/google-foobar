package googleProject5;

import java.util.Arrays;
//Queue to Do
public class Answer3 {

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
		int rowFirstNumber;
		int rowRange = length - 1;
		int rowLastNumber;
		int checksum = 0;
		
		//iterate through the rows
		for (int i = 0;i < length; i++){
			rowFirstNumber = length*i + start;
			rowLastNumber = rowFirstNumber + rowRange;
			checksum ^= getXor(rowFirstNumber,rowLastNumber);
			rowRange--;
		}
		
		return checksum;
	}
	
	
	//range checksum calculator
	public static int getXor(int a, int b){
		if (a == 0){
			return f(b)^ f(a);
		}
		else{
			return f(b)^f(a - 1);
			}
		
	}
	//xor pattern for ranges
	public static int f(int a){
		int[] res = {a,1,a+1,0};
		return res[a%4];
	}
}
