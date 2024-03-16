package aufgabe3;
import java.util.Scanner;

/**
 * The {@code Main} class represents the main entry point for the Prime Number Collection program.
 * It allows the user to input the upper limit for prime numbers and the thread count, and then
 * initiates the collection of prime numbers using multiple threads.
 * 
 * @author Maria Lyoteva
 */
public class Main {
    public static void main(String[] args) {
    	
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the upper limit for the prime numbers:");
        int limit = scanner.nextInt();
        System.out.println("Please enter the thread count:");
        int threadCount = scanner.nextInt();

        scanner.close();

        PrimeCollector collectionOfPrimes = new PrimeCollector(limit, threadCount);
        
        // Initiate the collection of prime numbers using multiple threads
        collectionOfPrimes.collectPrimes();
    }
}

		