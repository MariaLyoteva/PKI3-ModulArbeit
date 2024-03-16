package aufgabe3_tests;


import org.junit.jupiter.api.Test;

import aufgabe3.PrimeNumber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class PrimeNumberTest {

    @Test
    public void testisPrime() {
        PrimeNumber primeNumber = new PrimeNumber(1, 30, 0);
        List<Integer> expectedPrimes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        assertEquals(expectedPrimes, primeNumber.call());
    }

    @Test
    public void testBoundConditions() {
        PrimeNumber primeNumber = new PrimeNumber(0, 3, 0);
        List<Integer> expectedPrimes = Arrays.asList(2);
        assertEquals(expectedPrimes, primeNumber.call());
    }

    @Test
    public void testNegativeNumbers() {
        PrimeNumber primeNumber = new PrimeNumber(-10, 5, 0);
        List<Integer> expectedPrimes = Arrays.asList(2, 3);
        assertEquals(expectedPrimes, primeNumber.call());
    }

    @Test
    public void testSingleNumberRange() {
        PrimeNumber primeNumber = new PrimeNumber(5, 5, 0);
        List<Integer> expectedPrimes = Arrays.asList();
        assertEquals(expectedPrimes, primeNumber.call());
    }

    @Test
    public void testEmptyRange() {
        PrimeNumber primeNumber = new PrimeNumber(10, 5, 0);
        assertTrue(primeNumber.call().isEmpty());
    }
    
    @Test
    public void testThreadID() {
        PrimeNumber primeNumber = new PrimeNumber(1, 10, 5);
        assertEquals(5, primeNumber.getThreadID());
    }
    
    @Test
    public void testConcurrency() throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        PrimeNumber primeNumber1 = new PrimeNumber(1, 10, 1);
        PrimeNumber primeNumber2 = new PrimeNumber(10, 20, 2);
        Future<List<Integer>> future1 = executor.submit(primeNumber1);
        Future<List<Integer>> future2 = executor.submit(primeNumber2);
        
        assertNotNull(future1.get());
        assertNotNull(future2.get());

        executor.shutdown();
    }
}

