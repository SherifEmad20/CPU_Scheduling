import java.util.*;
	
public class SRTF {


		int Num_of_processes; // number of processes

		public static Stack<Integer> s = new Stack<Integer>();

		public SRTF() {
		}

		public void startExecution() {

			Scanner input = new Scanner(System.in);
			Scanner nameSc = new Scanner(System.in);
			Scanner timeSc = new Scanner(System.in);

			Num_of_processes = 0;

			System.out.print("Enter number of processes: ");
			Num_of_processes = input.nextInt();

			process[] process = new process[Num_of_processes];

			for (int i = 0; i < Num_of_processes; i++) {
				System.out.println("Enter the process " + (i + 1) + " name:");
				String processName = nameSc.nextLine();
				System.out.println("Enter the process " + (i + 1) + " burst time:");
				int processBT = timeSc.nextInt();
				System.out.println("Enter the process " + (i + 1) + " arrival time:");
				int processAT = timeSc.nextInt();
				process p = new process(i + 1, processName, processBT, processAT);
				process[i] = p;
				System.out.print("\n");

				p.setpName(processName);
				p.setBurstTime(processBT);
				p.setArrivalTime(processAT);
			}

			System.out.println("Enter the context switching");
			int context = input.nextInt();

			AvgTime(process, process.length, context);

		}

		public static void TurnAroundTime(process p[], int n, int wt[], int tat[]) {

			for (int i = 0; i < n; i++)
				tat[i] = p[i].getBurstTime() + wt[i];
		}

		public static void WaitingTime(process p[], int n, int wt[], int context) {

			int remainingtime[] = new int[n];

			for (int i = 0; i < n; i++)
				remainingtime[i] = p[i].getBurstTime();

			int complete = 0, t = 0, min = Integer.MAX_VALUE;

			int shortest = 0, finish_time;

			boolean check = false, switched = false;

			s.push(90);
			while (complete != n) {

				for (int j = 0; j < n; j++) {
					if ((p[j].getArrivalTime() <= t) && (remainingtime[j] < min) && remainingtime[j] > 0) {
						min = remainingtime[j];
						shortest = j;
						check = true;
					}
				}
				switched = false;
				if (check == false) {
					t++;
					continue;
				}
				// System.out.println(proc[shortest].getPid());
				if (s.peek() != p[shortest].getPid()) {
					if (t != 0) {
						switched = true;
					}
					s.push(p[shortest].getPid());
				}

				remainingtime[shortest]--;

				min = remainingtime[shortest];
				if (min == 0)
					min = Integer.MAX_VALUE;

				if (switched) {
					t++;
					t += context;
				} else
					t++;

				if (remainingtime[shortest] == 0) {

					complete++;
					check = false;
					finish_time = t;

					wt[shortest] = finish_time - p[shortest].getBurstTime() - p[shortest].getArrivalTime();
					if (wt[shortest] < 0)
						wt[shortest] = 0;
				}
			}

			s.remove(0);

		}

		public static void AvgTime(process p[], int n, int context) {
			int wt[] = new int[n], tat[] = new int[n];
			int total_wt = 0, total_tat = 0;

			// Function to find waiting time of all
			// processes
			WaitingTime(p, n, wt, context);

			// Function to find turn around time for
			// all processes
			TurnAroundTime(p, n, wt, tat);

			// Display processes along with all
			// details
			System.out.println("Execution Order: ");
			for (int i = 0; i < s.size(); i++)
				System.out.println("P" + s.get(i));
			System.out.println(" ");
			System.out.println("Processes " + " Burst time " + " Waiting time " + " Turn around time");

			// Calculate total waiting time and
			// total TurnAround time
			for (int i = 0; i < n; i++) {
				total_wt = total_wt + wt[i];
				total_tat = total_tat + tat[i];
				System.out
						.println(" " + p[i].getpName() + "\t\t" + +p[i].getBurstTime() + "\t\t " + wt[i] + "\t\t" + tat[i]);
			}

			System.out.println("Average waiting time = " + (float) total_wt / (float) n);
			System.out.println("Average turn around time = " + (float) total_tat / (float) n);
		}
}
