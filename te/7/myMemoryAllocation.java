import java.util.Scanner;
import java.util.Arrays;

public class myMemoryAllocation{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        // for Memory Blocks
        System.out.print("Enter number of Memory Blocks: ");
        int blockCount = sc.nextInt();
        int[] blocks = new int[blockCount];
        System.out.println("Enter memory size of each block:");
        for( int i=0; i < blockCount; i++){
            blocks[i] = sc.nextInt();
        }

        //copy of blocks for Bestfit
        int[] blocks2 = Arrays.copyOf(blocks, blockCount);
        int[] blocks3 = Arrays.copyOf(blocks, blockCount);

        // for processes
        System.out.print("Enter number of processes: ");
        int processCount = sc.nextInt();
        int[] processes = new int[processCount];
        System.out.println("Enter memory requirement of each process:");
        for( int i=0; i < processCount; i++){
            processes[i] = sc.nextInt();
        }

        //first fit
        int[] firstFitAllocation = new int[processCount];
        Arrays.fill(firstFitAllocation, -1);

        for(int i=0; i < processCount; i++)
        {
            for(int j=0; j < blockCount; j++)
            {
                if(blocks[j] >= processes[i] )
                {
                    firstFitAllocation[i] = j;
                    blocks[j] -= processes[i];
                    break;
                }
            }
        }

        //best fit
        int[] bestFitAllocation = new int[processCount];
        Arrays.fill(bestFitAllocation, -1);

        for(int i=0; i < processCount; i++)
        {
            int bestIndx = -1;
            for(int j=0; j < blockCount; j++)
            {
                if(blocks2[j] >= processes[i] && (bestIndx == -1 || blocks2[j] < blocks2[bestIndx]))
                {
                    bestIndx = j;
                }
            }
            if(bestIndx != -1)
            {
                bestFitAllocation[i] = bestIndx;
                blocks2[bestIndx] -= processes[i];
            }
        }

        //worst fit
        int[] worstFitAllocation = new int[processCount];
        Arrays.fill(worstFitAllocation, -1);

        for(int i=0; i < processCount; i++)
        {
            int worstIndx = -1;
            for(int j=0; j < blockCount; j++)
            {
                if(blocks3[j] >= processes[i] && (worstIndx == -1 || blocks3[j] > blocks[worstIndx]))
                {
                    worstIndx = j;
                }
            }
            if( worstIndx != -1)
            {
                worstFitAllocation[i] = worstIndx;
                blocks[worstIndx] -= processes[i];
            }
        }


        //print result
        System.out.println("First Fit allocation: ");
        for(int i=0; i < processCount; i++){
            if(firstFitAllocation[i] != -1){
                System.out.println("P"+ (i+1) + " to " + "B" + (firstFitAllocation[i] + 1));
            }
            else
            {
                System.out.println("P"+ (i+1) + " to -" );  
            }
            
        } 

        System.out.println("Best Fit allocation: ");
        for(int i=0; i < processCount; i++){
            if(bestFitAllocation[i] != -1){
                System.out.println("P"+ (i+1) + " to " + "B" + (bestFitAllocation[i] + 1) );
            }
            else
            {
                System.out.println("P"+ (i+1) + " to -" );  
            }    
        } 

        //print worstfit

        sc.close();

    }// main function

}// main class