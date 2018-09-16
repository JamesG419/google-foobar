package googleProject6;

import java.math.BigInteger;

public class Answer {
	
	public static void main(String args[]){
		String a = "250215646546123156465461313162332321653266";
		int answer = answer(a);
		
		System.out.println(answer);
	}

    public static int answer(String n) { 

        // Your code goes here.
    	//convert decimal string to binary string
    	String binary = decimalToBinary(n);
    	
    	//initialize counter
    	int counter = 0;
    	int length = binary.length();
    	
    	//counter algorithm--go until reach 1
    	while(length > 1 && binary != "1"){
    	
    		int last = binary.length() - 1;
    	//divide by two if last digit is zero
    		if(binary.charAt(last) == '0'){
    			binary = binary.substring(0, last);
    			counter++;
    		}
    	//check for 3
    		else if(binary.charAt(last) == '1' && binary.charAt(last - 1) == '1' && length == 2){
    			binary = binary.substring(0, binary.length() - 1) + "0";
    			counter++;
    		}
    	//add one if last 2 numbers are 1
    		else if(binary.charAt(last) == '1' && binary.charAt(last - 1) == '1'){
    			binary = add1(binary);
    			counter++;
    		}
    	//subtract one otherwise
    		else{
    			binary = binary.substring(0, binary.length() - 1) + "0";
    			counter++;
    		}
    		length = binary.length();
    	}
    	return counter;

    } 
    
    //method to convert decimal string to binary string
    public static String decimalToBinary(String number){
    	BigInteger num = new BigInteger(number);
    	String binaryString = num.toString(2);
    	
    	return binaryString;
    }
    
    //add 1 method
    public static String add1(String number){
    	
    	String finalString = "";
    	boolean carry = true;
    	
    	for(int i = number.length() - 1;i>=0;i--){
    		if (i == 0 && carry){
    			finalString = "10" + finalString;
    		}
    		else if (number.charAt(i) == '1' && carry){
    			finalString = "0" + finalString;
    		}
    		else if (number.charAt(i) == '0' && carry){
    			finalString = "1" + finalString;
    			carry = false;
    		}

    		else {
    			finalString = number.substring(0, number.length()- finalString.length()) + finalString;
    		}

    	}
    	
    	
    	return finalString;
    }
 
}

