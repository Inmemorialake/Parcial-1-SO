public class Process {
    private String id;
    private int arrivalTime;
    private int burstTime;
    private int remainingTime;
    private int queue;
    private int priority;

    private int completionTime;
    private int waitingTime;
    private int turnaroundTime;
    private int responseTime;
    private boolean isCompleted;
    private boolean started;

    public Process(String id, int burstTime, int arrivalTime, int queue, int priority) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.queue = queue;
        this.priority = priority;
        this.completionTime = 0;
        this.waitingTime = 0;
        this.turnaroundTime = 0;
        this.responseTime = -1;
        this.isCompleted = false;
        this.started = false;
    }

    // Getters y setters
    public String getId() { return id; }
    public int getArrivalTime() { return arrivalTime; }
    public int getBurstTime() { return burstTime; }
    public int getRemainingTime() { return remainingTime; }
    public void setRemainingTime(int remainingTime) { this.remainingTime = remainingTime; }
    public int getQueue() { return queue; }
    public int getPriority() { return priority; }
    public int getCompletionTime() { return completionTime; }
    public void setCompletionTime(int completionTime) { this.completionTime = completionTime; }
    public int getWaitingTime() { return waitingTime; }
    public void setWaitingTime(int waitingTime) { this.waitingTime = waitingTime; }
    public int getTurnaroundTime() { return turnaroundTime; }
    public void setTurnaroundTime(int turnaroundTime) { this.turnaroundTime = turnaroundTime; }
    public int getResponseTime() { return responseTime; }
    public void setResponseTime(int responseTime) { this.responseTime = responseTime; }
    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean completed) { isCompleted = completed; }
    public boolean isStarted() { return started; }
    public void setStarted(boolean started) { this.started = started; }

    @Override
    public String toString() {
        return String.format("%s;BT=%d;AT=%d;Q=%d;P=%d;WT=%d;CT=%d;RT=%d;TAT=%d",
                id, burstTime, arrivalTime, queue, priority,
                waitingTime, completionTime, responseTime, turnaroundTime);
    }
}
