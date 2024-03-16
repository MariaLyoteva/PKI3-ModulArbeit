package aufgabe4;

import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		  Scanner scanner = new Scanner(System.in);

	        System.out.println("Geben Sie die Obergrenze für Primzahlen ein:");
	        int limit = scanner.nextInt();
	   
	        System.out.println("Geben Sie die Anzahl der Threads ein:");
	        int thread_count = scanner.nextInt();

	        scanner.close();
	        
	        PrimeCollector coll= new PrimeCollector(limit, thread_count);
	        coll.collectPrimes();
    }
}
