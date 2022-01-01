import java.util.Scanner;

public class Priority {
	int BurstTime[];
    int Priority[];
    int ArrivalTime[];
    String[] ProcessId;
    String[] ProcessName;
    int nProcess;

    void Process(Scanner input) 
    {
        System.out.print("Enter number of Processes  : ");
        int inputNumberOfProcess = input.nextInt();
        nProcess = inputNumberOfProcess;
        BurstTime = new int[nProcess];
        Priority = new int[nProcess];
        ArrivalTime = new int[nProcess];
        ProcessId = new String[nProcess];
        ProcessName= new String[nProcess];
        String s = "P";
        for (int i = 0; i < nProcess; i++) 
        {
            ProcessId[i] = s.concat(Integer.toString(i));
            System.out.print("Enter  Process "+ (i) +" name : ");
            ProcessName[i] = input.next();
            System.out.print("Enter  Process "+ (i) +" brust time : ");
            BurstTime[i] = input.nextInt();
            System.out.print("Enter  Process "+ (i) +" arrival time : "  );
            ArrivalTime[i] = input.nextInt();
            System.out.print("Enter  Process "+ (i) +" priority : ");
            Priority[i] = input.nextInt();
        }
    }

    void sort(int[] arrival, int[] brust, int[] priority, String[] id) 
    {

        int temp1;
        String temp2;
        for (int i = 0; i < nProcess; i++) 
        {

            for (int j = 0; j < nProcess - i - 1; j++) 
            {
                if (arrival[j] > arrival[j + 1]) 
                {
                    temp1 = arrival[j];
                    arrival[j] = arrival[j + 1];
                    arrival[j + 1] = temp1;
                    
                    temp1 = brust[j];
                    brust[j] = brust[j + 1];
                    brust[j + 1] = temp1;

                    temp1 =  priority[j];
                    priority[j] =  priority[j + 1];
                    priority[j + 1] = temp1;

                    temp2 = id[j];
                    id[j] = id[j + 1];
                    id[j + 1] = temp2;

                }
                if (arrival[j] == arrival[j + 1]) 
                {
                    if ( priority[j] >  priority[j + 1]) 
                    {

                        temp1 = arrival[j];
                        arrival[j] = arrival[j + 1];
                        arrival[j + 1] = temp1;

                        temp1 =brust[j];
                        brust[j] = brust[j + 1];
                        brust[j + 1] = temp1;

                        temp1 =  priority[j];
                        priority[j] =  priority[j + 1];
                        priority[j + 1] = temp1;

                        temp2 = id[j];
                        id[j] = id[j + 1];
                        id[j + 1] = temp2;

                    }
                }
            }

        }
    }

    void calculate() 
    {
        int Finish[] = new int[nProcess];
        int brust[] = BurstTime.clone();
        int arrival[] = ArrivalTime.clone();
        int  priority[] = Priority.clone();
        String id[] = ProcessId.clone();
        int Waiting[] = new int[nProcess];
        int TurnAround[] = new int[nProcess];

        sort(arrival, brust,  priority, id);

        Finish[0] = arrival[0] + brust[0];
        TurnAround[0] = Finish[0] - arrival[0];
        Waiting[0] = TurnAround[0] - brust[0];

        for (int i = 1; i < nProcess; i++) 
        {
            Finish[i] = brust[i] + Finish[i - 1];
            TurnAround[i] = Finish[i] - arrival[i];
            Waiting[i] = TurnAround[i] - brust[i];
            //to solve starvation
            if(Waiting[i]>5) {
            	 priority[i]--;
            	sort(arrival,  brust,  priority,  id) ;
            	
            	
            }
        }
        float sum = 0;
        for (int n : Waiting) 
        {
            sum += n;
        }
        float avWaiting = sum / nProcess;

        sum = 0;
        for (int n : TurnAround) 
        {
            sum += n;
        }
        float avTurnAround = sum / nProcess;

    
        System.out.println("Answer : ");
        System.out.println("Process "+"      "+"Waiting Time "+ "      "+"Turn Around Time");

        for(int i=0; i<nProcess;i++) {
        	
           System.out.println(id[i]+"              "+Waiting[i]+"        \t\t"+TurnAround[i]);}
   
        System.out.println("Average waiting time: "+avWaiting);
        System.out.println("Average turn around time: "+avTurnAround);

    }
}
