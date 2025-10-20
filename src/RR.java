import java.util.*;

public class RR implements Planificador {
    private int quantum;

    public RR(int quantum) {
        this.quantum = quantum;
    }

    @Override
    public void ejecutar(List<Process> procesos) {
        Queue<Process> cola = new LinkedList<>(procesos);
        int tiempo = 0;

        while (!cola.isEmpty()) {
            Process p = cola.poll();

            if (!p.isStarted()) {
                p.setResponseTime(tiempo - p.getArrivalTime());
                p.setStarted(true);
            }

            if (p.getRemainingTime() > quantum) {
                tiempo += quantum;
                p.setRemainingTime(p.getRemainingTime() - quantum);
                cola.offer(p);
            } else {
                tiempo += p.getRemainingTime();
                p.setRemainingTime(0);
                p.setCompletionTime(tiempo);
                p.setTurnaroundTime(p.getCompletionTime() - p.getArrivalTime());
                p.setWaitingTime(p.getTurnaroundTime() - p.getBurstTime());
                p.setCompleted(true);
            }
        }
    }
}
