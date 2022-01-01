import java.util.*;

public class Main {
 
	public static void main(String []args) {
		ArrayList<process> processes = new ArrayList<process>();
		Scanner input = new Scanner(System.in); // inputs scanner
		int numOfProcesses = 0;
		
		System.out.println("1-Priority Scheduling with context switching \n2-Shortest- Job First \n3-Shortest-Remaining Time First \n4-AGAT Scheduling \nenter your choice");
			int choice = input.nextInt();
		if (choice==1)
		{
	        Priority obj = new Priority();
	        obj.Process(input);
	        obj.calculate();
		}
		else if (choice==2)
		{
	        SJF obj =  new SJF(); 
	        obj.startExecution();
		}
		else if (choice==3)
		{
			SRTF s = new SRTF();
			s.startExecution();
		}
		else if(choice==4)
		{
			AGAT a =new AGAT();
			a.startExecution();
		}
		
		
	
	}
	
	
}
