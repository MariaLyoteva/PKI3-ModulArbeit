
package aufgabe4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * A utility class for collecting prime numbers within a specified limit using
 * multiple prime detection algorithms. It divides the range of numbers into
 * threads and assigns each thread a prime detection algorithm to find primes.
 * The results are aggregated and printed, including the execution time and
 * number of primes found for each algorithm. The complete set of prime numbers
 * is also collected.
 * 
 * @author Maria Lyoteva
 */
public class PrimeCollector {
	private final int limit;
	private final int threadCount;
	private ExecutorService exec;
	private Map<String, TheoremThreadData> theoremDataMap;
	private Map<Future<List<Integer>>, TheoremThreadData> futureToTheoremMap;
	private Set<Integer> allPrimes;

	public PrimeCollector(int limit, int threadCount) {
		this.limit = limit;
		this.threadCount = threadCount;
		this.exec = Executors.newFixedThreadPool(threadCount);
		this.theoremDataMap = new HashMap<>();
		this.futureToTheoremMap = new ConcurrentHashMap<>();
		this.allPrimes = new ConcurrentSkipListSet<>();
	}

	/**
	 * Collects prime numbers within the specified limit using multiple algorithms
	 * and aggregates the results. This method divides the range into threads,
	 * assigns algorithms, and processes the results. It also prints the aggregated
	 * data and the complete set of prime numbers.
	 */
	public void collectPrimes() {
		setUpAndSubmitTasks();
		processFutureResults();
		printAggregatedTheoremData();
		exec.shutdown();
		printAllPrimes();
	}

	/**
	 * Sets up and submits prime detection tasks to different threads. Each thread
	 * is assigned a range of numbers and a prime detection algorithm. The results
	 * are stored in a map with corresponding thread data.
	 */
	private void setUpAndSubmitTasks() {
		int range = limit / threadCount;
		PrimeTheorems[] theorems = new PrimeTheorems[] { new Fermat(3), new MillerRabin(), new MyWay() };
		int theoremIndex = 0;
		int start;
		int end;
		for (int i = 0; i < threadCount; i++) {
			start = i * range + 1;
			end = (i + 1) * range;

			// Adjust end for the last thread
			if (i == threadCount - 1) {
				end = limit;
			}

			PrimeTheorems selectedTheorem = theorems[theoremIndex % theorems.length];
			theoremIndex++;

			TheoremThreadData data = new TheoremThreadData(selectedTheorem.getClass().getSimpleName(), i);
			data.setStartTime(System.currentTimeMillis());

			Future<List<Integer>> future = exec.submit(new PrimeNumber(start, end, selectedTheorem));
			futureToTheoremMap.put(future, data);
		}
	}

	/**
	 * Processes the results of prime detection tasks submitted to threads. It
	 * aggregates the data, calculates execution times, and updates the theorem data
	 * map. The results are printed, including thread-specific information.
	 */
	private void processFutureResults() {
		for (Map.Entry<Future<List<Integer>>, TheoremThreadData> entry : futureToTheoremMap.entrySet()) {
			try {
				List<Integer> primes = entry.getKey().get();
				TheoremThreadData info = entry.getValue();
				info.setEndTime(System.currentTimeMillis());
				info.setNumberOfPrimesFound(primes.size());

				TheoremThreadData aggregatedData = theoremDataMap.getOrDefault(info.theoremName,
						new TheoremThreadData(info.theoremName, -1));
				long duration = info.endTime - info.startTime;
				aggregatedData.addTime(duration);
				aggregatedData.addPrimes(primes.size());
				theoremDataMap.put(info.theoremName, aggregatedData);

				System.out.println(
						"Thread " + (info.threadId + 1) + " using " + info.theoremName + " found primes: " + primes);
				allPrimes.addAll(primes);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Prints the aggregated theorem data, including the execution time and the
	 * total number of primes found for each algorithm.
	 */
	private void printAggregatedTheoremData() {
		System.out.println();
		for (TheoremThreadData data : theoremDataMap.values()) {
			System.out.println("Theorem " + data.theoremName + " took a total of " + data.totalTime
					+ " ms and found a total of " + data.totalPrimes + " primes.");
		}
	}

	private void printAllPrimes() {
		System.out.println();
		System.out.println("The complete set of primes:");
		System.out.println(allPrimes);
	}

	// Created for testing purposes
	public Set<Integer> getCollectedPrimes() {
		return allPrimes;
	}

	public Map<Future<List<Integer>>, TheoremThreadData> getFutureToTheoremMap() {
		return this.futureToTheoremMap;
	}

	public Map<String, TheoremThreadData> getTheoremDataMap() {
		return this.theoremDataMap;
	}
}
