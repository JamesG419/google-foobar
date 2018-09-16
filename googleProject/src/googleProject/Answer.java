package googleProject; 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Answer {   
    public static int[] answer(int[] data, int n) { 

        // Your code goes here.
        
        int length = data.length;
        int currentData;
        int currentCount;
        
        HashMap<Integer,Integer> counts = new HashMap<Integer,Integer>();
        List<Integer> solution = new ArrayList<Integer>();
        
        //how many times does an int show up in the array?
        for (int i = 0; i < length; i++){
            currentData = data[i];
            if (!counts.containsKey(currentData)){
                counts.put(currentData,1);
           } 
            else{
                currentCount = counts.get(currentData);
                counts.put(currentData,currentCount + 1);
            }            
        }
        
        //send solution to array list
        for (int i = 0; i < length; i++){
        	 currentData = data[i];
        	 currentCount = counts.get(currentData);
        	 if (currentCount <= n ) {
        		 solution.add(currentData);
        	 } 
        }
        //send to array
        int finalSolution[] = new int[solution.size()];
        for (int i = 0; i<solution.size();i++){
            finalSolution[i] = solution.get(i);
        }
        return finalSolution;
        
    } 
}



