import java.util.Scanner;

public class myPriority{
    public static void main(String[] args){

        int n;

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of Processes: ");
        n = sc.nextInt();

        int[] pid = new int[n];
        int[] AT = new int[n];
        int[] BT = new int[n];
        int[] priority = new int[n];
        int[] CT = new int[n];
        int[] TAT = new int[n];
        int[] WT = new int[n];
        boolean[] isCompleted = new boolean[n];

        float aTAT = 0;
        float aWT = 0;
        int completed = 0;
        int currentTime = 0;

        for(int i=0; i < n; i++){
            System.out.print("Enter process id: ");
            pid[i] = sc.nextInt();
            System.out.print("Enter process AT: ");
            AT[i] = sc.nextInt();
            System.out.print("Enter process BT: ");
            BT[i] = sc.nextInt();
            System.out.print("Enter process priority: ");
            priority[i] = sc.nextInt();
        }// for 1

        while(completed < n){

            int prior = Integer.MAX_VALUE;
            int idx = -1;
            

            for(int i=0; i < n; i++){
                if(!isCompleted[i] && AT[i] <= currentTime && priority[i] < prior){
                    prior = priority[i];
                    idx = i;
                }
            }//for

            //assign to CPU
            if(idx != -1){
                CT[idx] = currentTime + BT[idx];
                currentTime = CT[idx];

                TAT[idx] = CT[idx] - AT[idx];
                WT[idx] = TAT[idx] - BT[idx];

                aTAT += TAT[idx];
                aWT += WT[idx];

                isCompleted[idx] = true;
                completed++;
            }
            else{
                currentTime++;
            }
        }// while
                
        for (int i = 0; i < n; i++) {
            System.out.println("P" + pid[i] + "\t" + AT[i] + "\t" + BT[i] + "\t" + priority[i] + "\t" + CT[i] + "\t" + TAT[i] + "\t" + WT[i]);
        }

        // Display averages
        System.out.println("\nAverage Turnaround Time: " + (aTAT / n));
        System.out.println("Average Waiting Time: " + (aWT / n));

        sc.close();
    }//main func    
}// main class