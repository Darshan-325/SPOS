import java.util.* ;

public class myFCFS {
    public static void main(String[] args){
        int n;
        int TATA = 0;
        int WTA = 0;

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Number of Processes: ");
        n = sc.nextInt();

        int[] pid = new int[n];
        int[] AT = new int[n];
        int[] BT = new int[n];
        int[] CT = new int[n];
        int[] TAT = new int[n];
        int[] WT = new int[n];

        for(int i = 0; i<n; i++){

            System.out.print("Enter pid of process " + (i + 1) + ": ");
            pid[i] = sc.nextInt();

            System.out.print("Enter Arrival time of process: " + (i + 1)+ ": ");
            AT[i] = sc.nextInt();

            System.out.print("Enter Bust Time of process: " + (i + 1)+ ": ");
            BT[i] = sc.nextInt();
        }

        for (int i=0; i<n; i++){

            //completion time
            if(i == 0){
                CT[i] = AT[i] + BT[i];
            }else{
                CT[i] = CT[i - 1] +  BT[i];
            }

            //TAT
            TAT[i] = CT[i] - AT[i];

            //WT
            WT[i] = TAT[i] - BT[i];
        }

        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Process\tArrival\tBurst\tCompletion\tTurnaround\tWaiting");
        System.out.println("--------------------------------------------------------------------------");

        for (int i = 0; i < n; i++) {
            System.out.print("pid_" + pid[i] + "   ");
            System.out.print(AT[i] + "\t");
            System.out.print(BT[i] + "\t");
            System.out.print(CT[i] + "\t");

            System.out.print(TAT[i] + "\t");
            TATA += TAT[i];

            System.out.print(WT[i] + "\t");
            WTA += WT[i];

            System.out.println();
        }

        System.out.println();
        System.out.println("Average Turn Around Time: " +(TATA/n));
        System.out.println("Average Wating time:" + (WTA/n));
        sc.close();

    }//main function
}//main class
