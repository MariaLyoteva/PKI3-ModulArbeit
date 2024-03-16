package aufgabe3_tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import aufgabe3.PrimeCollector;

import org.junit.jupiter.api.Assertions;
import java.util.Set;

class PrimeCollectorTest {
    private PrimeCollector collector;

    @BeforeEach
    public void setUp() {
        collector = new PrimeCollector(10, 2);
    }

    @Test
    public void testCorrectPrimeCollection() {
        collector.collectPrimes();
        Set<Integer> expectedPrimes = Set.of(2, 3, 5, 7);
        System.out.println(collector.getCollectedPrimes());
        Assertions.assertEquals(expectedPrimes, collector.getCollectedPrimes());
    }

    @Test
    public void testThreadCountHandling() {
        Assertions.assertEquals(2, collector.getThreadCount());
    }

    @Test
    public void testRangeDivision() {
        collector.collectPrimes();
        // Additional checks for range division
    }
}

