import java.util.Scanner;
import java.util.Arrays;

public class myLRU {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        // Input: Number of pages
        System.out.print("Enter the number of pages you want to enter: ");
        int noofpages = sc.nextInt();
        int[] pages = new int[noofpages];

        // Input: Reference string (pages)
        System.out.println("Enter the reference string:");
        for (int i = 0; i < noofpages; i++) {
            pages[i] = sc.nextInt();
        }

        // Input: Capacity of the frame
        System.out.print("Enter the capacity of frame: ");
        int capacity = sc.nextInt();

        // Frame and last used initialization
        int[] frame = new int[capacity];
        int[] lastUsed = new int[capacity];
        int[][] table = new int[noofpages][capacity];
        Arrays.fill(frame, -1); // Initialize frame slots with -1 for empty
        Arrays.fill(lastUsed, -1); // Initialize last used times with -1 for unused

        // Variables to track hits, faults
        int hit = 0, fault = 0;

        System.out.println("\nProcessing:");
        // Process each page in the reference string
        for (int i = 0; i < noofpages; i++) {
            int page = pages[i];
            boolean pageFound = false;

            // Check if page is already in the frame (hit)
            for (int j = 0; j < capacity; j++) {
                if (frame[j] == page) {
                    pageFound = true;
                    hit++;
                    lastUsed[j] = i; // Update last used time to current index
                    System.out.printf("%4s", "H");
                    break;
                }
            }

            // If page not found in frame (page fault)
            if (!pageFound) {
                fault++;
                System.out.printf("%4s", "F");

                // Find the least recently used page to replace
                int lruIndex = 0;
                for (int j = 1; j < capacity; j++) {
                    if (lastUsed[j] < lastUsed[lruIndex]) {
                        lruIndex = j;
                    }
                }

                // Replace the LRU page with the new page
                frame[lruIndex] = page;
                lastUsed[lruIndex] = i; // Update last used time for this slot
            }

            // Copy the current frame status to the table for display
            for (int j = 0; j < capacity; j++) {
                table[i][j] = frame[j];
            }
        }

        // Display the frame status table
        System.out.println("\n\nFrame Status Table:");
        for (int i = 0; i < capacity; i++) {
            for (int j = 0; j < noofpages; j++) {
                if (table[j][i] != -1) {
                    System.out.printf("%3d ", table[j][i]);
                } else {
                    System.out.printf(" -  ");
                }
            }
            System.out.println();
        }

        // Calculate and display hit ratio and fault ratio
        double faultRatio = ((double) fault / noofpages) * 100;
        double hitRatio = ((double) hit / noofpages) * 100;

        System.out.println("\nPage Faults: " + fault + "\nPage Hits: " + hit);
        System.out.printf("Hit Ratio: %.2f%% \nFault Ratio: %.2f%%", hitRatio, faultRatio);

        // Close the scanner
        sc.close();
    }
}
