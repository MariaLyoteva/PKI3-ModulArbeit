package aufgabe3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * The {@code PrimeCollector} class is responsible for collecting prime numbers
 * within a specified limit using multiple threads. It divides the range of
 * numbers into equal segments, assigns each segment to a thread, and collects
 * the prime numbers found by each thread.
 * 
 * @author Maria Lyoteva
 */
public class PrimeCollector {
	private final int limit;
	private final int threadCount;
	private final Set<Integer> allPrimes;

	/**
	 * Initializes a new instance of the {@code PrimeCollector} class.
	 *
	 * @param limit       The upper limit of the range to search for prime numbers.
	 * @param threadCount The number of threads to use for prime number calculation.
	 */
	public PrimeCollector(int limit, int threadCount) {
		this.limit = limit;
		this.threadCount = threadCount;
		this.allPrimes = new ConcurrentSkipListSet<>();
	}

	/**
	 * Collects prime numbers within the specified limit using multiple threads. It
	 * divides the range of numbers into segments, assigns each segment to a thread,
	 * and collects the prime numbers found by each thread.
	 */
	public void collectPrimes() {
		ExecutorService exec = Executors.newFixedThreadPool(threadCount);
		Map<Future<List<Integer>>, Integer> futureToThreadIdMap = new HashMap<>();
		int start;
		int end;
		int range = (limit / threadCount); // Adjusted range

		// Divide the range into segments and assign them to threads
		for (int i = 0; i < threadCount; i++) {
			start = i * range + 1;
			end = (i + 1) * range + 1;

			// Adjust end for the last thread
			if (i == threadCount - 1) {
				end = limit;
			}

			Future<List<Integer>> future = exec.submit(new PrimeNumber(start, end, i));
			futureToThreadIdMap.put(future, i);
		}

		// Collect prime numbers found by each thread
		for (Map.Entry<Future<List<Integer>>, Integer> entry : futureToThreadIdMap.entrySet()) {
			try {
				List<Integer> primes = entry.getKey().get();
				allPrimes.addAll(primes);
				System.out.println("Thread " + (entry.getValue() + 1) + " found primes: " + primes);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		exec.shutdown();
		System.out.println("All found prime numbers: " + allPrimes);
	}

	public Set<Integer> getCollectedPrimes() {
		return allPrimes;
	}

	public int getThreadCount() {
		return threadCount;
	}
}
