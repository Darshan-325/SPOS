import java.util.Scanner;

public class mySRTF{
    public static void main(String[] args){
        int n;

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of Processes: ");
        n = sc.nextInt();

        int[] pid = new int[n];
        int[] AT = new int[n];
        int[] BT = new int[n];
        int[] remainingBT = new int[n];
        int[] CT = new int[n];
        int[] TAT = new int[n];
        int[] WT = new int[n];

        float ATAT = 0;
        float AWT = 0;
        int currentTime = 0;
        int completed = 0;

        for(int i=0; i < n; i++){
            System.out.print("Enter Process id: ");
            pid[i] = sc.nextInt();

            System.out.print("Enter Process Arrival Time: ");
            AT[i] = sc.nextInt();

            System.out.print("Enter Process Burst time: ");
            BT[i] = sc.nextInt();

            remainingBT[i] = BT[i];
        }// loop 1

        while(completed < n){

            int min_BT = Integer.MAX_VALUE;
            int idx = -1;

            //check for process with minimum burst time
            for(int i=0; i < n; i++){
                if(AT[i]<=currentTime && remainingBT[i] > 0 && remainingBT[i] < min_BT){
                    min_BT = remainingBT[i];
                    idx = i;
                }//if 1
            }//for

            //assign CPU to process if i != -1
            if(idx != -1){
                remainingBT[idx]--;
                currentTime++;

                if(remainingBT[idx] == 0)
                {
                    completed++;

                    CT[idx] = currentTime;
                    TAT[idx] = CT[idx] - AT[idx];
                    WT[idx] =  TAT[idx] - BT[idx];

                    ATAT += TAT[idx];
                    AWT += WT[idx];
                }
            }
            else
            {
                currentTime++;
            }

        }//while

        System.out.println("PID \t\t AT \t\t BT \t\t CT \t\t TAT \t\t WT");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + pid[i] + "\t\t" + AT[i] + "\t\t" + BT[i] + "\t\t" + CT[i] + "\t\t" + TAT[i] + "\t\t" + WT[i]);
        }

        System.out.println("\nAverage Turnaround Time: " + (ATAT / n));
        System.out.println("Average Waiting Time: " + (AWT / n));

        sc.close();  

    }//main func
}//main class