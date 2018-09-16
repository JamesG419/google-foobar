package googleProject2;
//lambda's lucky lambs
public class Answer {
	public static void main (String args[]){
		int test1 = 2000000001;
		
		int sol1 = answer(test1);
		
		System.out.println("Difference 1 is " + sol1);
		

	}
	
	
	public static int answer(int total_lambs){
		int difference;
		int fibCounter = 1;
		int geoCounter = 1;
		
		int first = 1;
		int previous = 0;
		int current;
		int future;
		int sum;
		
		//fib loop
		
		current = first;
		sum = first;
		
		while(sum <= total_lambs){
			future = current + previous;
			sum += future;
			if (sum <= total_lambs){
				fibCounter++;
			}
			previous = current;
			current = future;
		}
		
		//fibCounter = (int) ((Math.log(total_lambs * Math.sqrt(5) + 1/2))/Math.log(1.6180339887)) - 2;
		
		//geo loop
		geoCounter = (int) (Math.log(total_lambs + 1)/Math.log(2));
		sum = (int) Math.pow(2,geoCounter)-1;
		int remainder = total_lambs - sum;
		int prev1 = (int)Math.pow(2, geoCounter - 1);
		int prev2 = (int)Math.pow(2, geoCounter - 2);
		
		if (remainder >= prev1 + prev2){
			geoCounter++;
		}
		
		difference = fibCounter - geoCounter;
		return difference;
	}

}
