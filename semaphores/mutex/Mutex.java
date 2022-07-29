import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Mutex {
    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of threads to create: ");
        int numberOfThreads = input.nextInt();

        Thread[] threads = new Thread[numberOfThreads];

        Counter c = new Counter(); // Create the counter that will be shared between the threads
        Semaphore mutex = new Semaphore(1); // Create the mutex that will be shared between the threads

        for (int i = 0; i < numberOfThreads; i++) {
            ConcurrentCode task = new ConcurrentCode(c, mutex);
            Thread t = new Thread(task); // Create Thread
            threads[i] = t; // Add thread to array of threads
        }

        for (Thread t : threads) {
            t.start(); // Start a thread
        }

        for (Thread t : threads) {
            t.join(); // Wait a thread
        }
        System.out.println(c.getCount());
    }
}
