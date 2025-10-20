import java.util.*;

public class SJF implements Planificador {
    @Override
    public void ejecutar(List<Process> procesos) {
        // Ordenar por tiempo de llegada para incorporación determinística a la cola de listos
        procesos.sort(Comparator.comparingInt(Process::getArrivalTime));

        PriorityQueue<Process> ready = new PriorityQueue<>(Comparator.comparingInt(Process::getBurstTime)
                .thenComparing(Process::getArrivalTime));
        int n = procesos.size();
        int completados = 0;
        int tiempo = 0;
        int idx = 0;

        while (completados < n) {
            // Mover a la cola de listos todos los que ya llegaron
            while (idx < n && procesos.get(idx).getArrivalTime() <= tiempo) {
                ready.offer(procesos.get(idx));
                idx++;
            }

            if (ready.isEmpty()) {
                // Si no hay procesos listos, avanzar el tiempo al próximo arribo
                if (idx < n) {
                    tiempo = Math.max(tiempo, procesos.get(idx).getArrivalTime());
                    continue;
                }
            } else {
                Process p = ready.poll();
                if (!p.isStarted()) {
                    p.setResponseTime(tiempo - p.getArrivalTime());
                    p.setStarted(true);
                }

                // SJF no expropiativo: ejecutar el burst completo
                tiempo += p.getBurstTime();
                p.setRemainingTime(0);
                p.setCompletionTime(tiempo);
                p.setTurnaroundTime(p.getCompletionTime() - p.getArrivalTime());
                p.setWaitingTime(p.getTurnaroundTime() - p.getBurstTime());
                p.setCompleted(true);
                completados++;
            }
        }
    }
}
