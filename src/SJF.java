import java.util.*;

public class SJF implements Planificador {
    @Override
    public void ejecutar(List<Process> procesos) {
        procesos.sort(Comparator.comparingInt(Process::getBurstTime));
        int tiempo = 0;

        for (Process p : procesos) {
            if (!p.isStarted()) {
                p.setResponseTime(tiempo - p.getArrivalTime());
                p.setStarted(true);
            }

            tiempo += p.getBurstTime();
            p.setCompletionTime(tiempo);
            p.setTurnaroundTime(p.getCompletionTime() - p.getArrivalTime());
            p.setWaitingTime(p.getTurnaroundTime() - p.getBurstTime());
            p.setCompleted(true);
        }
    }
}
