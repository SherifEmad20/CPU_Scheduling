import java.util.*;

class inf {
	String name, color;
	int start, finish, quantum;
}

public class AGAT {
	int numOfProcesses; // number of processes
	int totalQuantum; // total quantum time
	double totalTurnAround; // total turn around time
	double totalWaitingTime; // total waiting time
	int maxTime; // maximum time

	// list of processes
	ArrayList<process> processes = new ArrayList<process>();

	// temporary list to store processes
	ArrayList<process> tempList = new ArrayList<process>();

	// list to print process that is executing
	ArrayList<process> outputList = new ArrayList<process>();

	// list for dead processes
	ArrayList<process> deadList = new ArrayList<process>();

	// ready queue
	Queue<process> readyQueue = new LinkedList<process>();

	// list to store time
	ArrayList<Integer> timeList = new ArrayList<Integer>();
	// to take inputs call in main
	ArrayList<Integer> hisquantum = new ArrayList<Integer>();

	ArrayList<Integer> hisAGAT = new ArrayList<Integer>();

	// start executing algorithm
	public void startExecution() {

		int counter=0;
		process tempProcess=new process();
		tempProcess.setQuantum(1);
		boolean flag=true;
		//System.out.println(maxTime);
		int oldindex=0;
		for (int t = 0; t < maxTime; ) {
			flag=true;
			if(!processes.isEmpty()) {
				if (processes.get(0).getArrivalTime() >= 0) {
					//best agate
					processes.get(0).setAGATFactor(processes.get(0));
					int minAGAT = processes.get(0).getAGATFactor();
					int index = 0;
					storequantum(processes);
					for (int i = 0; i < processes.size(); i++) {
						processes.get(i).setAGATFactor(processes.get(i));
						if (minAGAT > processes.get(i).getAGATFactor() && processes.get(i).getArrivalTime() <= t&&i!=oldindex) {
							index = i;
							minAGAT = processes.get(i).getAGATFactor();
							counter=0;
						}
					}
					//outputList.add(processes.get(index));
					storeAGAT(processes);
					//40% quantum
					int fourty= (int) Math.round(processes.get(index).getQuantum()*0.4);
					counter=0;
					boolean brk=false;
					int reminder=processes.get(index).getQuantum();
					for(int i=0;i<processes.get(index).getQuantum();i++) {
						reminder--;
						processes.get(index).setBurstTime(processes.get(index).getBurstTime()-1);
						counter++;
						t++;
						outputList.add(processes.get(index));
//						System.out.println("output: ");
//						for (int q = 0; q < this.outputList.size(); q++)
//							System.out.print(this.outputList.get(q).getpName() + "	");
//						System.out.print("\n\n");
						storequantum(processes);
						//set agate
						for (int e = 0; e < processes.size(); e++) {
							processes.get(e).setAGATFactor(processes.get(e));
						}
						storeAGAT(processes);
						if(processes.get(index).getBurstTime()==0)
						{
							processes.get(index).setQuantum(0);
							processes.get(index).setCompletionTime(t);
							deadList.add(processes.get(index));
							processes.remove(index);
							flag=false;
							break;
						}
						if(counter>=fourty)
						{
							brk=false;
							for (int e = 0; e < processes.size(); e++) {
								if (minAGAT > processes.get(e).getAGATFactor() && processes.get(e).getArrivalTime() <= t) {
									index = e;
									minAGAT = processes.get(e).getAGATFactor();
									counter=0;
									brk=true;
									break;
								}
							}
							if(brk) {
								break;
							}
						}
					}
					
					if(flag&&(!brk)&&!processes.isEmpty()) {
						processes.get(index).setQuantum(processes.get(index).getQuantum()+2);
						oldindex=index;
						//tempProcess.equalProcess(processes.get(index));
						//processes.remove(index);
					}
					else if (!processes.isEmpty()&&!brk&&flag)
					{
//						for (int e = 0; e < processes.size(); e++) {
//							System.out.println("s"+processes.get(e).getpName()+" ws "+processes.get(e).getBurstTime()+" w "+t);
//						}
						processes.get(index).setQuantum(processes.get(index).getQuantum()+reminder);
					}
				}
				else
				{
					storequantum(processes);
					storeAGAT(processes);
				}
			}
			else {
				processes.add(tempProcess);
			}
				
		}
//		for(int i =0 ; i<hisAGAT.size();i++)
//		{
//			System.out.println(hisAGAT.get(i)+" ");
//		}
//		for(int i =0 ; i<hisAGAT.size();i++)
//		{
//			System.out.println(hisAGAT.get(i)+" ");
//		}
		printExecutionOrder();
		printTable();
	}

	// store quantum
	public void storequantum(ArrayList<process> p) {
		for (int i = 0; i < p.size(); i++) {
			hisquantum.add(p.get(i).getQuantum());
		}
	}

	// store AGAT
	public void storeAGAT(ArrayList<process> p) {
		for (int i = 0; i < p.size(); i++) {
			hisAGAT.add(p.get(i).getAGATFactor());
		}
	}

	// constructor
	public AGAT() {
		this.AGATinput();
	}

	public void AGATinput() {

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
			System.out.print("Enter priority of process " + (i + 1) + ": ");
			int processPriority = input.nextInt();
			System.out.print("Enter quantum Time of process " + (i + 1) + ": ");
			int processQT = input.nextInt();
			System.out.print("Enter color of process " + (i + 1) + ": ");
			String color = input.next();
			System.out.print("\n");

			// creates a process with inputs as parameters
			process p = new process(processName, processBT, processAT, processPriority, processQT, color);
			p.setRemainingTime(processBT);
			this.processes.add(p); // add process to list
			this.tempList.add(p); // add process to templist

			// to print AGAT factor for each process
			// System.out.println(p.getAGATFactor());

		}
		for (int i = 0; i < numOfProcesses; i++) {
			// setting v1 and v2 values to calculate AGAT-Factor
			processes.get(i).setV1(this.v1());
			processes.get(i).setV2(this.v2());

			processes.get(i).setCeiling((int) Math.ceil(processes.get(i).getArrivalTime() / this.v1()));

			// calculate the AGAT factor for each process in the list
			processes.get(i).setAGATFactor(processes.get(i));
		}
	}

	// gets last arrival time
	public int getMaxArrivalTime() {
		int maxArrivalTime = 0;
		// System.out.println(this.processes.size());
		for (int i = 0; i < this.processes.size(); i++) {
			if (this.processes.get(i).getArrivalTime() > maxArrivalTime) {
				maxArrivalTime = this.processes.get(i).getArrivalTime();
			}
		}
		// System.out.println(maxArrivalTime );
		return maxArrivalTime;
	}

	// gets maximum service time
	public int getMaxBurstTime() {
		int maxBurstTime = 0;
		for (int i = 0; i < processes.size(); i++) {
			if (processes.get(i).getBurstTime() > maxBurstTime) {
				maxBurstTime = processes.get(i).getBurstTime();
			}
		}
		return maxBurstTime;
	}

	// calculate v1
	public double v1() {
		double maxAT = this.getMaxArrivalTime();
		if (maxAT > 10) {
			return maxAT / 10;
		} else {
			return 1;
		}
	}

	// calculate v2
	public double v2() {
		double maxBT = this.getMaxBurstTime();
		if (maxBT > 10) {
			return maxBT / 10;
		} else
			return 1;
	}

	// prints processes table
	public void printTable() {
		timeCalculations();
		System.out.println(
				"\n\nProcess \t Burst Time \t Arrival Time \t Priority \t Quantum \t   V1 \t Ceil(Arrivaltime/V1) \n");
		for (int i = 0; i < this.deadList.size(); i++) {
			System.out.print("\n " + this.deadList.get(i).getpName() + "   \t\t   "
					+ this.deadList.get(i).getNotchangedburst() + "   \t\t   " + this.deadList.get(i).getArrivalTime()
					+ "   \t\t   " + this.deadList.get(i).getPriority() + "   \t\t   "
					+ this.deadList.get(i).getNotchangedquantum() + "   \t\t   " + this.deadList.get(i).getV1() + "   \t\t   "
					+ this.deadList.get(i).getCeiling() + "\n");
		}
		System.out.print("\n\n");
		System.out.println("[AVARAGE TIME]");
		System.out.println("Average turn around time = " + this.totalTurnAround / this.numOfProcesses + ".");
		System.out.println("Average waiting time = " + this.totalWaitingTime / this.numOfProcesses + ".");
		System.out.print("\n\n");
	}

	// calculates turn around time and waiting time
	public void timeCalculations() {
		for (int i = 0; i < this.deadList.size(); i++) {
			this.deadList.get(i).setWaitingTime(this.deadList.get(i).getCompletionTime()
					- this.deadList.get(i).getNotchangedburst() - this.deadList.get(i).getArrivalTime());
			this.deadList.get(i)
					.setTurnaroundTime(this.deadList.get(i).getNotchangedburst() + this.deadList.get(i).getWaitingTime());
			//System.out.println(deadList.get(i).getNotchangedburst()+" s "+deadList.get(i).getTurnaroundTime() );
			System.out.println("["+deadList.get(i).getpName()+" "+deadList.get(i).getCompletionTime()+" "+deadList.get(i).getTurnaroundTime()+" "+deadList.get(i).getWaitingTime());
			this.totalTurnAround += this.deadList.get(i).getTurnaroundTime();
			this.totalWaitingTime += this.deadList.get(i).getWaitingTime();
		}
	}

	// gets 40% of quantum time
	public void printUpdates(int time) {
		System.out.print("[Time: " + time + "] -> ");
		System.out.print("Quantum ( ");
		for (int i = 0; i < this.processes.size(); i++)
			System.out.print(this.processes.get(i).getQuantum() + " ");
		System.out.print(") -> (40%) = ( ");
		for (int i = 0; i < this.processes.size(); i++)
			System.out.print((int) Math.round((this.processes.get(i).getQuantum()) * 0.4) + " ");
		System.out.print(") ");
	}

	// prints processes order of execution
	public void printExecutionOrder() {
		System.out.println("\n[Processes execution order]");
		for (int i = 0; i < this.outputList.size(); i++)
			System.out.print(this.outputList.get(i).getpName() + "	");
		System.out.print("\n\n");
	}

	/*
	 * // sort processes according to arrival time and AGAT factor public void
	 * sortProcesses(int time) { this.tempList.sort((o1, o2) -> { if
	 * (o1.getArrivalTime() <= time && o2.getArrivalTime() <= time) { if
	 * (o1.getAGATFactor() < o2.getAGATFactor()) return -1; else return 1; } else if
	 * (o1.getArrivalTime() < o2.getArrivalTime()) return -1; else return 1; }); }
	 */

	public void isFinished() {
		boolean flag = true;

		while (flag) {
			for (int i = 0; i < this.tempList.size(); i++) {
				if (this.readyQueue.peek().getAGATFactor() == this.tempList.get(i).getAGATFactor())
					flag = false;
			}

			if (flag && !this.readyQueue.isEmpty())
				this.readyQueue.remove();
		}
	}

}
