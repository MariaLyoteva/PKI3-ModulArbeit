package aufgabe4_tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import aufgabe4.Fermat;

class FermatTest {

	@Test
	public void testIsPrimeForPrimesFermat() {
		Fermat fermatChecker = new Fermat(5);
		assertTrue(fermatChecker.isPrime(2));
		assertTrue(fermatChecker.isPrime(3));
		assertTrue(fermatChecker.isPrime(5));
		assertTrue(fermatChecker.isPrime(7));
		assertTrue(fermatChecker.isPrime(11));
	}

	@Test
	public void testIsPrimeForNonPrimesFermat() {
		Fermat fermatChecker = new Fermat(5);
		assertFalse(fermatChecker.isPrime(4));
		assertFalse(fermatChecker.isPrime(6));
		assertFalse(fermatChecker.isPrime(9));
		assertFalse(fermatChecker.isPrime(15));
		assertFalse(fermatChecker.isPrime(21));
	}
}
