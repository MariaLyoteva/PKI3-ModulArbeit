package aufgabe4_tests;

import org.junit.jupiter.api.Test;

import aufgabe4.MyWay;
import aufgabe4.PrimeNumber;
import aufgabe4.PrimeTheorems;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class PrimeNumberTest {

	private PrimeTheorems primeCheck;

	@BeforeEach
	public void setUp() {
		primeCheck = new MyWay();
	}

	@Test
	public void testPrimeNumberInRange() {
		int start = 1;
		int end = 10;
		PrimeNumber primeNumber = new PrimeNumber(start, end, primeCheck);
		List<Integer> primes = primeNumber.call();
		assertEquals(List.of(2, 3, 5, 7), primes);
	}

	@Test
	public void testPrimeNumberNoPrimesInRange() {
		int start = 20;
		int end = 22;
		PrimeNumber primeNumber = new PrimeNumber(start, end, primeCheck);
		List<Integer> primes = primeNumber.call();
		assertTrue(primes.isEmpty());
	}

	@Test
	public void testPrimeNumberWithNegativeRange() {
		int start = -10;
		int end = -1;
		PrimeNumber primeNumber = new PrimeNumber(start, end, primeCheck);
		List<Integer> primes = primeNumber.call();
		assertTrue(primes.isEmpty());
	}

	@Test
	public void testPrimeNumberWithExecutorService() throws ExecutionException, InterruptedException {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		int start = 1;
		int end = 10;
		PrimeNumber primeNumber = new PrimeNumber(start, end, primeCheck);
		Future<List<Integer>> future = executorService.submit(primeNumber);
		List<Integer> primes = future.get();
		executorService.shutdown();
		assertEquals(List.of(2, 3, 5, 7), primes);
	}
}
