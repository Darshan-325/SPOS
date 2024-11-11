import java.util.Scanner;
import java.util.Arrays;

public class myFIFO{
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        // for pages
        System.out.print("Enter the number of pages:");
        int numPages = sc.nextInt();
        System.out.println("Enter the reference string:");
        int[] pages = new int[numPages];
        for(int i=0; i < numPages; i++)
        {
            pages[i] = sc.nextInt();
        }

        //for frame
        System.out.print("Enter frame capacity: ");
        int frameCapacity = sc.nextInt();
        int[] frame = new int[frameCapacity];
        Arrays.fill(frame, -1);

        int[][] table = new int[numPages][frameCapacity];

        int index = 0, hits = 0, faults = 0;
        
        //Process begins
        for(int i=0; i < numPages; i++)
        {
            boolean pageFound = false;
            for(int j=0; j< frameCapacity; j++)
            {
                if(frame[j] == pages[i])
                {
                    hits++;
                    pageFound = true;
                    System.out.print("H ");
                    break;
                }
            }//frame loop

            if(pageFound == false)
            {
                frame[index] = pages[i];
                faults++;
                System.out.print("F ");
                index = (index + 1) % frameCapacity;
            }

            //update table
            for(int j=0; j < frameCapacity; j++)
            {
                table[i][j] = frame[j];
            }
        }//pages loop

        //Display table
        System.out.println("Frame Status Table: ");
        for(int i=0; i < frameCapacity; i++)
        {
            for(int j=0; j < numPages; j++)
            {
                System.out.print(table[j][i]+ " ");
            }
            System.out.println();
        }

        //hit ration and fault ration
        double hitRatio = ((double)hits/numPages)*100;
        double faultRatio = ((double)faults/numPages)*100; 

        System.out.println("Hits: "+ hits);
        System.out.println("Faults: "+ faults);

        System.out.println("Hit Ratio: "+ hitRatio);
        System.out.println("Fault Ratio: "+ faultRatio);

        sc.close();

    }// main function
}// main class