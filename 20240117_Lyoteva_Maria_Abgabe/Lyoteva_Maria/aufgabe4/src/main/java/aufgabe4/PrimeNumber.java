package aufgabe4;

import java.util.concurrent.Callable;
import java.util.List;
import java.util.ArrayList;

/**
 * The `PrimeNumber` class represents a task for calculating prime numbers
 * within a specified range. It implements the `Callable` interface, allowing it
 * to be executed by a thread and return a list of prime numbers found within
 * the assigned range.
 * 
 * @author Maria Lyoteva
 */
public class PrimeNumber implements Callable<List<Integer>> {
	private int start;
	private int end;
	private PrimeTheorems primeCheck;

	/**
	 * Initializes a `PrimeNumber` task with the specified start and end values of
	 * the range and the prime checking algorithm.
	 *
	 * @param start      The starting value of the range (inclusive).
	 * @param end        The ending value of the range (inclusive).
	 * @param primeCheck The prime checking algorithm used to determine if a number
	 *                   is prime.
	 */
	public PrimeNumber(int start, int end, PrimeTheorems primeCheck) {
		this.start = start;
		this.end = end;
		this.primeCheck = primeCheck;
	}

	/**
	 * Calculates prime numbers within the specified range assigned to the thread.
	 *
	 * @return A list of prime numbers found within the assigned range.
	 */
	@Override
	public List<Integer> call() {
		List<Integer> primeCollection = new ArrayList<>();
		for (int i = start; i <= end; i++) {
			if (primeCheck.isPrime(i)) {
				primeCollection.add(i);
			}
		}
		return primeCollection;
	}
}
