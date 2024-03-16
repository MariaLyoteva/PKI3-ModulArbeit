package aufgabe4_tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import aufgabe4.MyWay;

class MyWayTest {

	@Test
	public void testIsPrimeForPrimes() {
		MyWay myWay = new MyWay();
		assertTrue(myWay.isPrime(2));
		assertTrue(myWay.isPrime(3));
		assertTrue(myWay.isPrime(5));
		assertTrue(myWay.isPrime(7));
		assertTrue(myWay.isPrime(11));
	}

	@Test
	public void testIsPrimeForNonPrimes() {
		MyWay myWay = new MyWay();
		assertFalse(myWay.isPrime(0));
		assertFalse(myWay.isPrime(1));
		assertFalse(myWay.isPrime(4));
		assertFalse(myWay.isPrime(6));
		assertFalse(myWay.isPrime(8));
		assertFalse(myWay.isPrime(9));
		assertFalse(myWay.isPrime(10));
		assertFalse(myWay.isPrime(15));
	}

	@Test
	public void testIsPrimeForNegativeNumbers() {
		MyWay myWay = new MyWay();
		assertFalse(myWay.isPrime(-2));
		assertFalse(myWay.isPrime(-3));
		assertFalse(myWay.isPrime(-5));
		assertFalse(myWay.isPrime(-7));
		assertFalse(myWay.isPrime(-11));
	}
}
