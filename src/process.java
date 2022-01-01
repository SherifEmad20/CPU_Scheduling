public class process {

	private String pName;
	private String color;
	private int burstTime;
	private int priority;
	private int arrivalTime;
	private int completionTime;
	private double turnaroundTime;
	private double waitingTime;
	private int remainingTime;
	private int quantum;
	private int start;
	private int AGATFactor;
	private int ceiling;
	private double v1;
	private double v2;
	private int notchangedburst;
	private int notchangedquantum;
	private int pid;

	public process() {
		pName = "";
		color = "";
		burstTime = 0;
		priority = 0;
		arrivalTime = 0;
		completionTime = 0;
		turnaroundTime = 0;
		waitingTime = 0;
		remainingTime = 0;
		quantum = 0;
		start = 0;
		AGATFactor = 0;
		ceiling = 0;
		v1 = 0;
		v2 = 0;
		notchangedburst = 0;
		notchangedquantum = 0;
	}

	public process(String process, int BT, int AT, int prior, int quan, String color) {
		this.pName = process;
		this.burstTime = BT;
		this.arrivalTime = AT;
		this.priority = prior;
		this.quantum = quan;
		this.color = color;
		notchangedburst = BT;
		notchangedquantum = quan;
	}

	public process(int pID, String processName, int processBT, int processAT) {
		this.pid = pID;
		pName = processName;
		burstTime = processBT;
		arrivalTime = processAT;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getPid() {
		return pid;
	}

	public void equalProcess(process p) {
		pName = p.pName;
		color = p.color;
		arrivalTime = p.arrivalTime;
		burstTime = p.burstTime;
		priority = p.priority;
		quantum = p.quantum;
		AGATFactor = p.AGATFactor;
		start = p.start;
		completionTime = p.completionTime;
		waitingTime = p.waitingTime;
		turnaroundTime = p.turnaroundTime;
	}

	public void setNotchangedquantum(int notchangedquantum) {
		this.notchangedquantum = notchangedquantum;
	}

	public int getNotchangedquantum() {
		return notchangedquantum;
	}

	public void setNotchangedburst(int notchangedburst) {
		this.notchangedburst = notchangedburst;
	}

	public int getNotchangedburst() {
		return notchangedburst;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getColor() {
		return color;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getStart() {
		return start;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public int getBurstTime() {
		return burstTime;
	}

	public void setBurstTime(int BT) {
		burstTime = BT;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int AT) {
		arrivalTime = AT;
	}

	public int getCompletionTime() {
		return completionTime;
	}

	public void setCompletionTime(int completionTime) {
		this.completionTime = completionTime;
	}

	public double getTurnaroundTime() {
		return turnaroundTime;
	}

	public void setTurnaroundTime(double turnaroundTime) {
		this.turnaroundTime = turnaroundTime;
	}

	public double getWaitingTime() {
		return waitingTime;
	}

	public void setWaitingTime(double waitingTime) {
		this.waitingTime = waitingTime;
	}

	public int getQuantum() {
		return quantum;
	}

	public void setQuantum(int quantum) {
		this.quantum = quantum;
	}

	public int getAGATFactor() {
		return AGATFactor;
	}

	public void setAGATFactor(process p) {
		AGATFactor = (10 - getPriority()) + ((int) Math.ceil(getArrivalTime() / v1))
				+ ((int) Math.ceil(getNotchangedburst() / v2));
	}

	public int getRemainingTime() {
		return remainingTime;
	}

	public void setRemainingTime(int remainingTime) {
		this.remainingTime = remainingTime;
	}

	public double getV1() {
		return v1;
	}

	public void setV1(double v1) {
		this.v1 = v1;
	}

	public double getV2() {
		return v2;
	}

	public void setV2(double v2) {
		this.v2 = v2;
	}

	public int getCeiling() {
		return ceiling;
	}

	public void setCeiling(int ceiling) {
		this.ceiling = ceiling;
	}

}
