package aufgabe4;

import java.math.BigInteger;
import java.util.Random;

/**
 * The {@code MillerRabin} class implements the Miller-Rabin primality test as a
 * part of the PrimeTheorems interface. It determines whether a given number is
 * prime by applying the Miller-Rabin probabilistic primality test. The test
 * uses random bases and multiple iterations to increase accuracy.
 * 
 * @author Maria Lyoteva
 */
public class MillerRabin implements PrimeTheorems {

	/**
	 * Checks if a given number is prime using the Miller-Rabin probabilistic
	 * primality test.
	 *
	 * @param number The number to be tested for primality.
	 * @return {@code true} if the number is likely to be prime, {@code false}
	 *         otherwise.
	 * 
	 * @author Maria Lyoteva
	 */
	@Override
	public boolean isPrime(int number) {
		int oddFactor = number - 1;
		int exponentOfTwo = 0;

		if (number <= 1)
			return false;
		if (number <= 3)
			return true;
		if (number % 2 == 0)
			return false;

		while (oddFactor % 2 == 0) {
			oddFactor /= 2;
			exponentOfTwo++;
		}

		Random rand = new Random();
		for (int i = 0; i < Math.min(number, 10); i++) {
			BigInteger randomBase = BigInteger.valueOf(rand.nextInt(number - 3) + 2);
			BigInteger currModEx = modPower(randomBase, BigInteger.valueOf(oddFactor), BigInteger.valueOf(number));

			if (!currModEx.equals(BigInteger.ONE)
					&& !currModEx.equals(BigInteger.valueOf(number).subtract(BigInteger.ONE))) {
				if (!squareAndTest(currModEx, exponentOfTwo, number)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Applies the square-and-test step within the Miller-Rabin primality test.
	 *
	 * @param currModEx     The result of modular exponentiation.
	 * @param exponentOfTwo The exponent of two in the factorization of (n - 1).
	 * @param number        The number being tested for primality.
	 * @return {@code true} if the square-and-test step indicates the number may be
	 *         prime, {@code false} otherwise.
	 */
	private boolean squareAndTest(BigInteger currModEx, int exponentOfTwo, int number) {
		BigInteger numMinusOne = BigInteger.valueOf(number).subtract(BigInteger.ONE);

		for (int i = 0; i < exponentOfTwo; i++) {
			currModEx = currModEx.multiply(currModEx).mod(BigInteger.valueOf(number));
			if (currModEx.equals(BigInteger.ONE))
				return false;
			if (currModEx.equals(numMinusOne))
				return true;
		}
		return false;
	}

	private BigInteger modPower(BigInteger base, BigInteger exponent, BigInteger modulus) {
		return base.modPow(exponent, modulus);
	}
}
