import java.util.Scanner;

public class myRR {
     public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of process: ");
        int n = sc.nextInt();

        int[] pid = new int[n];
        int[] AT = new int[n];
        int[] BT = new int[n];
        int[] remainingBT = new int[n];
        int[] CT = new int[n];
        int[] TAT = new int[n];
        int[] WT = new int[n];

        int ATAT = 0, AWT = 0;

        //pid AT BT CT TAT WT

        //take inputs
        for(int i=0; i < n; i++)
        {
            System.out.print("Enter the process id of process "+(i+1)+" :");
            pid[i] = sc.nextInt();
            System.out.print("Enter the process Arrival time: ");
            AT[i] = sc.nextInt();
            System.out.print("Enter the process Burst time: ");
            BT[i] = sc.nextInt();

            System.out.println("************************");
            remainingBT[i] = BT[i];
        }

        System.out.print("Enter Time Quantum: ");
        int timeQuantum = sc.nextInt();

        int currentTime = 0;
        boolean processRemaining = true;

        while( processRemaining == true )
        {
            for(int i=0; i < n; i++)
            {
                if(remainingBT[i] > 0)
                {
                    if(remainingBT[i] > timeQuantum)
                    {
                        currentTime += timeQuantum;
                        remainingBT[i] -= timeQuantum;
                    }
                    else
                    {
                        currentTime += remainingBT[i];
                        remainingBT[i] = 0;
                        
                        CT[i] = currentTime;
                        TAT[i] = CT[i] - AT[i];
                        WT[i] = TAT[i] - BT[i];

                        ATAT += TAT[i];
                        AWT += WT[i];
                    }
                }
            }//for

            //check if all burst time is 0
            for(int i=0; i < n; i++)
            {
                if(remainingBT[i] !=0 )
                {
                    processRemaining = true;
                    break;
                }
                else
                { 
                    processRemaining = false;
                }
            }

        }//while

        //print result
        System.out.println("pid\tAT\tBT\tCT\tTAT\tWT");
        for(int i=0; i < n; i++)
        {
            System.out.print("pid_" + pid[i] + "   ");
            System.out.print(AT[i] + "\t");
            System.out.print(BT[i] + "\t");
            System.out.print(CT[i] + "\t");
            System.out.print(TAT[i] + "\t");
            System.out.print(WT[i] + "\t");

            System.out.println();   
        }
        System.out.println();

        System.out.println("Average Turn around time: "+ (ATAT/n));
        System.out.println("Average Wating time: "+ (AWT/n));

        sc.close();
    }// main function    

    
}
