import java.util.concurrent.Semaphore;
import java.util.Scanner;

public class Synchronized {

    // Semaphore for mutual exclusion to control access to the readCount variable
    static Semaphore mutex = new Semaphore(1);
    // Semaphore for writers to gain exclusive access to the shared resource
    static Semaphore wrt = new Semaphore(1);

    static int readCount = 0;
    static String message = "Hello"; // Shared resource
    static Scanner sc = new Scanner(System.in);

    // Reader class implementing the Runnable interface
    static class Reader implements Runnable {
        public void run() {
            try {
                // Acquire mutex to safely update the readCount
                mutex.acquire();
                readCount++;
                if (readCount == 1) {
                    // If it's the first reader, acquire wrt to block writers
                    wrt.acquire();
                }
                mutex.release();

                // Reading section
                System.out.println("Thread " + Thread.currentThread().getName() + " is READING: " + message);
                Thread.sleep(1500);
                System.out.println("Thread " + Thread.currentThread().getName() + " has FINISHED READING");

                // Acquire mutex to safely update the readCount
                mutex.acquire();
                readCount--;
                if (readCount == 0) {
                    // If no more readers, release wrt to allow writers
                    wrt.release();
                }
                mutex.release();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // Writer class implementing the Runnable interface
    static class Writer implements Runnable {
        public void run() {
            try {
                // Acquire wrt to get exclusive access to write
                wrt.acquire();
                
                // Writing section
                System.out.println("Thread " + Thread.currentThread().getName() + " is WRITING. Enter new message: ");
                message = sc.nextLine();
                System.out.println("Thread " + Thread.currentThread().getName() + " has written: " + message);
                
                Thread.sleep(1500);
                System.out.println("Thread " + Thread.currentThread().getName() + " has finished WRITING");

                // Release wrt to allow others
                wrt.release();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        // Creating reader and writer threads
        Reader reader = new Reader();
        Writer writer = new Writer();

        Thread r1 = new Thread(reader, "Reader1");
        Thread r2 = new Thread(reader, "Reader2");
        Thread r3 = new Thread(reader, "Reader3");
        
        Thread w1 = new Thread(writer, "Writer1");
        Thread w2 = new Thread(writer, "Writer2");
        Thread w3 = new Thread(writer, "Writer3");

        // Starting the threads
        r1.start();
        w1.start();
        r2.start();
        w2.start();
        r3.start();
        w3.start();

        // Waiting for all threads to finish
        try {
            r1.join();
            r2.join();
            r3.join();
            w1.join();
            w2.join();
            w3.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        sc.close();
    }
}