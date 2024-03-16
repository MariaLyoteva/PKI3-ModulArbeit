package aufgabe4;

import java.math.BigInteger;
import java.util.Random;

/**
 * The {@code Fermat} class implements the Fermat primality test as a part of
 * the PrimeTheorems interface. It determines whether a given number is prime by
 * applying Fermat's Little Theorem. The accuracy of the test is determined by
 * the certainty parameter, which controls the number of iterations.
 * 
 * @author Maria Lyoteva
 */
public class Fermat implements PrimeTheorems {
	private final int certainty;

	public Fermat(int certainty) {
		this.certainty = certainty;
	}

	/**
	 * Checks if a given number is prime using Fermat's Little Theorem.
	 *
	 * @param number The number to be tested for primality.
	 * @return {@code true} if the number is likely to be prime, {@code false}
	 *         otherwise.
	 */
	@Override
	public boolean isPrime(int number) {
		if (number <= 1 || number == 4)
			return false;
		if (number <= 3)
			return true;

		Random rand = new Random();
		BigInteger bigN = BigInteger.valueOf(number);

		for (int i = 0; i < certainty; i++) {
			BigInteger a = BigInteger.valueOf(rand.nextInt(number - 3) + 2);
			if (!modPower(a, bigN.subtract(BigInteger.ONE), bigN).equals(BigInteger.ONE)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Calculates the modular exponentiation of a base raised to an exponent modulo
	 * a modulus.
	 *
	 * @param base     The base value.
	 * @param exponent The exponent value.
	 * @param modulus  The modulus value.
	 * @return The result of (base^exponent) % modulus.
	 */
	private static BigInteger modPower(BigInteger base, BigInteger exponent, BigInteger modulus) {
		return base.modPow(exponent, modulus);
	}
}
