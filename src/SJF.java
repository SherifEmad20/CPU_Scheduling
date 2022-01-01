import java.util.*;

public class SJF {

	// number of processes
	int numOfProcesses;

	// average waiting time
	double avgWaitingTime = 0;

	// average turn around time
	double avgTurnaroundTime = 0;

	// total number of processes
	double totalNumOfProcess = 0;

	// end time
	int maxTime = 0;

	int minTime = 0;

	ArrayList<process> processes = new ArrayList<process>();

	public SJF() {
		SJFInput();
	}

	public void SJFInput() {

		Scanner input = new Scanner(System.in); // inputs scanner

		numOfProcesses = 0;
		System.out.print("Enter number of process: ");
		numOfProcesses = input.nextInt();

		for (int i = 0; i < numOfProcesses; i++) {
			System.out.print("Enter process " + (i + 1) + " name: ");
			String processName = input.next();
			System.out.print("Enter burst Time of process " + (i + 1) + ": ");
			int processBT = input.nextInt();
			maxTime += processBT;
			System.out.print("Enter arrival Time of process " + (i + 1) + ": ");
			int processAT = input.nextInt();
			System.out.print("\n");

			// creates a process with inputs as parameters
			process p = new process(i + 1, processName, processBT, processAT);
			p.setRemainingTime(processBT);
			this.processes.add(p); // add process to list

			// System.out.println(p.getAGATFactor());

		}
	}

	public void startExecution() {
		this.printTable();
	}

	public void getWaitingTime() {

		// sort burst Time
		for (int i = 0; i < processes.size(); i++) {
			int index = i;
			for (int j = i + 1; j < processes.size(); j++) {
				if (processes.get(j).getBurstTime() < processes.get(index).getBurstTime())
					index = j;
			}
			int temp;
			temp = processes.get(i).getBurstTime();
			processes.get(i).setBurstTime(processes.get(index).getBurstTime());
			processes.get(index).setBurstTime(temp);

			temp = processes.get(i).getPid();
			processes.get(i).setPid(processes.get(index).getPid());
			processes.get(index).setPid(temp);

		}

		for (int i = 1; i < processes.size(); i++) {
			processes.get(0).setWaitingTime(0);
			for (int j = 0; j < i; j++)
				processes.get(i)
						.setWaitingTime(processes.get(i - 1).getWaitingTime() + processes.get(j).getBurstTime());
			totalNumOfProcess += processes.get(i).getWaitingTime();
		}
		avgWaitingTime = totalNumOfProcess / numOfProcesses;
		totalNumOfProcess = 0;
	}

	public void getTurnAroundTime() {

		for (int i = 0; i < processes.size(); i++) {
			processes.get(i).setTurnaroundTime(processes.get(i).getBurstTime() + processes.get(i).getWaitingTime());

			totalNumOfProcess += processes.get(i).getTurnaroundTime();
		}
		avgTurnaroundTime = totalNumOfProcess / numOfProcesses;

	}

	public void printTable() {
		getWaitingTime();
		getTurnAroundTime();
		System.out.println("\n\nProcess id \t Burst Time \t Arrival Time \t Waiting \t Turn around \n");
		for (int i = 0; i < this.processes.size(); i++) {
			System.out.print("\n " + this.processes.get(i).getPid() + "   \t\t   "
					+ this.processes.get(i).getBurstTime() + "   \t\t   " + this.processes.get(i).getArrivalTime()
					+ "   \t\t   " + this.processes.get(i).getWaitingTime() + "   \t\t   "
					+ this.processes.get(i).getTurnaroundTime() + "   \t\t   " + "\n");
		}
		System.out.print("\n\n");
		System.out.println("[AVARAGE TIME]");
		System.out.println("Average turn around time = " + this.avgTurnaroundTime + ".");
		System.out.println("Average waiting time = " + this.avgWaitingTime + ".");
		System.out.print("\n\n");

	}
}