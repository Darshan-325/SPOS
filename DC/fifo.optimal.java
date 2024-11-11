import java.util.Scanner;

// Class to implement FIFO page replacement algorithm
class FIFO {
    private int front = -1;
    private int rear = -1;
    private int arr[];

    FIFO(int n) {
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = -1;
    }

    boolean isEmpty() {
        return front == -1;
    }

    boolean isFull() {
        return (rear + 1) % arr.length == front;
    }

    void enqueue(int ele) {
        if (!isFull()) {
            if (front == -1) front = 0;
            rear = (rear + 1) % arr.length;
            arr[rear] = ele;
        }
    }

    int dequeue() {
        if (!isEmpty()) {
            int temp = arr[front];
            if (front == rear) {
                front = rear = -1;
            } else {
                front = (front + 1) % arr.length;
            }
            return temp;
        }
        return -1;
    }

    boolean search(int ele) {
        for (int i : arr) if (i == ele) return true;
        return false;
    }

    void display() {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != -1) {
                System.out.printf("%3d", arr[i]);
            } else {
                System.out.print("  -");
            }
        }
        System.out.println();
    }
}

public class PageReplacement {
    // Method to display the frame array
    static void display(int arr[]) {
        for (int i : arr) {
            if (i != -1) {
                System.out.printf("%3d", i);
            } else {
                System.out.print("  -");
            }
        }
        System.out.println();
    }

    // Method to search if an element exists in the frame array
    static boolean search(int arr[], int e) {
        for (int i : arr) if (i == e) return true;
        return false;
    }

    // Method to check if a page exists in the future
    static boolean forward(int pages[], int start, int ele) {
        for (int i = start; i < pages.length; i++) {
            if (pages[i] == ele) return true;
        }
        return false;
    }

    // Method to find the page to replace in the Optimal algorithm
    static int findOP(int frames[], int pages[], int ind) {
        int maxDist = -1;
        int replaceIndex = -1;

        for (int i = 0; i < frames.length; i++) {
            int j;
            for (j = ind + 1; j < pages.length; j++) {
                if (frames[i] == pages[j]) break;
            }

            if (j == pages.length) return i; // If page not found in the future, replace this
            if (j > maxDist) {
                maxDist = j;
                replaceIndex = i;
            }
        }
        return replaceIndex;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the size of the frame: ");
        int size = sc.nextInt();

        System.out.print("Enter the number of pages: ");
        int n = sc.nextInt();
        int pages[] = new int[n];

        System.out.println("Enter the pages:");
        for (int i = 0; i < n; i++) {
            pages[i] = sc.nextInt();
        }

        int hit;
        int ch;

        do {
            System.out.println("\n---------MENU---------");
            System.out.println("1. FIFO");
            System.out.println("2. Optimal");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            ch = sc.nextInt();

            switch (ch) {
                case 1: {
                    FIFO queue = new FIFO(size);
                    hit = 0;

                    System.out.println("\nFIFO Page Replacement:");
                    for (int i = 0; i < n; i++) {
                        if (queue.search(pages[i])) {
                            hit++;
                        } else {
                            if (queue.isFull()) queue.dequeue();
                            queue.enqueue(pages[i]);
                        }
                        queue.display();
                    }
                    System.out.println("Total Hits: " + hit);
                    System.out.println("Total Faults: " + (n - hit));
                    break;
                }

                case 2: {
                    int frames[] = new int[size];
                    for (int i = 0; i < size; i++) frames[i] = -1;

                    hit = 0;
                    System.out.println("\nOptimal Page Replacement:");

                    for (int i = 0; i < n; i++) {
                        if (search(frames, pages[i])) {
                            hit++;
                        } else {
                            int replaceIndex = -1;

                            // If there is space, fill it first
                            for (int j = 0; j < size; j++) {
                                if (frames[j] == -1) {
                                    replaceIndex = j;
                                    break;
                                }
                            }

                            // If no space, find optimal page to replace
                            if (replaceIndex == -1) {
                                replaceIndex = findOP(frames, pages, i);
                            }

                            frames[replaceIndex] = pages[i];
                        }
                        display(frames);
                    }
                    System.out.println("Total Hits: " + hit);
                    System.out.println("Total Faults: " + (n - hit));
                    break;
                }

                case 3:
                    System.out.println("Exiting... Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        } while (ch != 3);

        sc.close();
    }
}