package aufgabe4;

/**
 * An implementation of the PrimeTheorems interface that uses a basic method to determine primality.
 * It checks for divisibility by all odd integers from 3 up to the square root of the number.
 * 
 * @author Maria Lyoteva
 */
public class MyWay implements PrimeTheorems {
	
	/**
     * Checks if a given number is prime using a basic method.
     *
     * @param num The number to be tested for primality.
     * @return {@code true} if the number is prime, {@code false} otherwise.
     */
	@Override
	public boolean isPrime(int num) {
		if(num<=1 ||(num !=2 && num%2==0)) {
			return false;
		} else {
			for(int i=3; i<=Math.sqrt(num); i+=2) {
				if(num%i==0) {
					return false;
				}
			}
		}
		return true;
	}

}
