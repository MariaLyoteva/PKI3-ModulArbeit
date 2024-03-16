package aufgabe4;

/**
 * The `TheoremThreadData` class represents data related to the execution of
 * prime checking threads. It includes information such as the name of the
 * theorem, thread ID, start and end times, the number of primes found, total
 * execution time, and the total number of primes found across all threads using
 * the same theorem.
 * 
 * @author Maria Lyoteva
 */
public class TheoremThreadData {
	String theoremName;
	int threadId;
	long startTime; // Start time of thread execution
	long endTime; // End time of thread execution
	int numberOfPrimesFound;
	long totalTime; // Total execution time of the thread
	int totalPrimes;

	/**
	 * Creates a new `TheoremThreadData` object with the specified theorem name and
	 * thread ID.
	 *
	 * @param name The name of the theorem being used.
	 * @param id   The ID of the thread.
	 */
	TheoremThreadData(String name, int id) {
		this.theoremName = name;
		this.threadId = id;
	}

	/**
	 * Sets the start time of thread execution.
	 *
	 * @param startTime The start time in milliseconds.
	 */
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	/**
	 * Sets the end time of thread execution.
	 *
	 * @param endTime The end time in milliseconds.
	 */
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	/**
	 * Sets the number of primes found by the thread.
	 *
	 * @param numberOfPrimes The number of primes found.
	 */
	public void setNumberOfPrimesFound(int numberOfPrimes) {
		this.numberOfPrimesFound = numberOfPrimes;
	}

	/**
	 * Adds the execution time of the thread to the total execution time.
	 *
	 * @param time The execution time to be added in milliseconds.
	 */
	public void addTime(long time) {
		this.totalTime += time;
	}

	/**
	 * Adds the number of primes found by the thread to the total number of primes
	 * found.
	 *
	 * @param primes The number of primes found.
	 */
	public void addPrimes(int primes) {
		this.totalPrimes += primes;
	}
}
