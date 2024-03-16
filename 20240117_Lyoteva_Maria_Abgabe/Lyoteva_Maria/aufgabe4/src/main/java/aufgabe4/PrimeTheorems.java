package aufgabe4;

/**
 * The `PrimeTheorems` interface defines a contract for classes that implement
 * different algorithms to determine whether a given number is prime or not.
 * 
 * @author Maria Lyoteva
 */
public interface PrimeTheorems {

	/**
	 * Checks if the specified number is prime according to a specific algorithm.
	 *
	 * @param number The number to be checked for primality.
	 * @return `true` if the number is prime, `false` otherwise.
	 */
	boolean isPrime(int number);

}
