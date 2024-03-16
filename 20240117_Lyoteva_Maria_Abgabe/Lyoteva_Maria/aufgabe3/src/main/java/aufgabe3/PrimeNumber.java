package aufgabe3;

import java.util.concurrent.Callable;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code PrimeNumber} class implements the Callable interface to calculate
 * prime numbers within a specified range assigned to a particular thread.
 * 
 * @author Maria Lyoteva
 */
public class PrimeNumber implements Callable<List<Integer>> {
	private int start;
	private int end;
	private int threadID;

	/**
	 * Initializes a new instance of the {@code PrimeNumber} class with the
	 * specified range and thread ID.
	 *
	 * @param start    The starting number of the range (inclusive).
	 * @param end      The ending number of the range (exclusive).
	 * @param threadID The unique identifier for the thread.
	 */
	public PrimeNumber(int start, int end, int threadID) {
		this.start = start;
		this.end = end;
		this.threadID = threadID;
	}

	/**
	 * Calculates prime numbers within the specified range assigned to the thread.
	 *
	 * @return A list of prime numbers found within the assigned range.
	 */
	@Override
	public List<Integer> call() {
		List<Integer> primeCollection = new ArrayList<>();

		// Print a message indicating that the thread is calculating primes
		System.out.println("Thread " + (threadID + 1) + " is calculating primes.");

		// Iterate through the range and identify prime numbers
		for (int i = start; i < end; i++) {
			if (isPrime(i)) {
				primeCollection.add(i);
			}
		}
		return primeCollection;
	}

	/**
	 * Checks if a given number is prime.
	 *
	 * @param num The number to check for primality.
	 * @return {@code true} if the number is prime, otherwise {@code false}.
	 */
	public static boolean isPrime(int num) {
		if (num <= 1 || (num != 2 && num % 2 == 0)) {
			return false;
		} else {
			for (int i = 3; i <= Math.sqrt(num); i += 2) {
				if (num % i == 0) {
					return false;
				}
			}
		}
		return true;
	}

	public int getThreadID() {
		return threadID;
	}
}
