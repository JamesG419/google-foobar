package googleProject4;
//Bomb Baby
import java.math.BigInteger;

public class AnswerTake2 {

	public static String answer(String M, String F) {
		
		try{
			//initialize and parse
			BigInteger generation = BigInteger.valueOf(0);
			
			BigInteger m = new BigInteger(M);
			BigInteger f = new BigInteger(F);
			
			BigInteger finalValue = BigInteger.valueOf(1);
			
			//keep going until one of them hits 1 or 0
			while (m.compareTo(finalValue) >= 0 && f.compareTo(finalValue) >= 0){
				
				//if they both hit 1, return generation as answer
				if (m.equals(finalValue) && f.equals(finalValue)){
					String gen = generation.toString();
					return gen;
				}
				
				//if either hits 0, it's impossible
				else if(m.equals(f)){
					return "impossible";
				}
				
				//start calculating generations and remainders
				else{
					//cases for whichever is larger
					//generation is calculated by dividing the smaller into the larger value
					//remainder is mod. remainder is to get closer to 1
					if(m.compareTo(f) > 0){
						generation = generation.add(counter(m,f));
						m = remainder(m,f);
					}
					else {
						generation = generation.add(counter(f,m));
						f = remainder(f,m);
					}
				}
			}
			return "impossible";
		}
		catch (NumberFormatException ex){
			return "impossible";
		}
	}	
	//counter function
	public static BigInteger counter(BigInteger larger, BigInteger smaller){
		BigInteger counter;
		BigInteger one = BigInteger.valueOf(1); 
		if(smaller.equals(one)){
			counter = larger.divide(smaller);
			counter = counter.subtract(one);
			}
		
		else{
			counter = larger.divide(smaller);
			}
		
		return counter;
	}
	//remainder function
	public static BigInteger remainder(BigInteger larger, BigInteger smaller){
		BigInteger remainder;
		BigInteger one = BigInteger.valueOf(1); 
		if(smaller.equals(one)){
			remainder = larger.remainder(smaller);
			remainder = remainder.add(one);
		}
		else{
			remainder = larger.remainder(smaller);
		}
		return remainder;
	}
}
