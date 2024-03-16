package aufgabe4_tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import aufgabe4.MillerRabin;

class MillerRabinTest {

	@Test
	public void testIsPrimeForPrimesMR() {
		MillerRabin mrChecker = new MillerRabin();
		assertTrue(mrChecker.isPrime(2));
		assertTrue(mrChecker.isPrime(3));
		assertTrue(mrChecker.isPrime(5));
		assertTrue(mrChecker.isPrime(7));
		assertTrue(mrChecker.isPrime(11));
	}

	@Test
	public void testIsPrimeForNonPrimesMR() {
		MillerRabin mrChecker = new MillerRabin();
		assertFalse(mrChecker.isPrime(4));
		assertFalse(mrChecker.isPrime(1));
		assertFalse(mrChecker.isPrime(9));
		assertFalse(mrChecker.isPrime(15));
		assertFalse(mrChecker.isPrime(22));
	}
}
