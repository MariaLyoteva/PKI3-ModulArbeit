package aufgabe4_tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import aufgabe4.PrimeCollector;
import aufgabe4.TheoremThreadData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;

class PrimeCollectorTest {
	private PrimeCollector primeCollector;
	private final int limit = 100;
	private final int threadCount = 4;

	@BeforeEach
	public void setUp() {
		primeCollector = new PrimeCollector(limit, threadCount);
	}

	@Test
	public void testPrimeCollectorInitialization() {
		assertNotNull(primeCollector, "PrimeCollector should be initialized");
	}

	@Test
	public void testCollectPrimes() {
		primeCollector.collectPrimes();
		// Assuming getters or direct access to the fields
		Set<Integer> primes = primeCollector.getCollectedPrimes();
		assertNotNull(primes, "Collected primes set should not be null");
		assertFalse(primes.isEmpty(), "Collected primes set should not be empty");
	}

	@Test
	public void testSetUpAndSubmitTasks() {
		primeCollector.collectPrimes();
		Map<Future<List<Integer>>, TheoremThreadData> futureMap = primeCollector.getFutureToTheoremMap();
		assertNotNull(futureMap, "Future map should not be null");
		assertEquals(threadCount, futureMap.size(), "Future map should have entries equal to the thread count");
	}

	@Test
	public void testProcessFutureResults() {
		primeCollector.collectPrimes();
		Map<String, TheoremThreadData> theoremDataMap = primeCollector.getTheoremDataMap();
		Set<Integer> allPrimes = primeCollector.getCollectedPrimes();

		assertNotNull(theoremDataMap, "Theorem data map should not be null");
		assertFalse(theoremDataMap.isEmpty(), "Theorem data map should not be empty");

		assertNotNull(allPrimes, "All primes set should not be null");
		assertFalse(allPrimes.isEmpty(), "All primes set should not be empty");
	}
}
