import java.util.*;

public class RR implements Planificador {
    private int quantum;

    public RR(int quantum) {
        this.quantum = quantum;
    }

    @Override
    public void ejecutar(List<Process> procesos) {
        // Ordenar inicialmente por tiempo de llegada
        procesos.sort(Comparator.comparingInt(Process::getArrivalTime));

        Deque<Process> ready = new ArrayDeque<>();
        int n = procesos.size();
        int completados = 0;
        int tiempo = 0;
        int idx = 0; // índice de próximos procesos por llegar

        // Encolar los que ya llegaron al tiempo inicial
        while (idx < n && procesos.get(idx).getArrivalTime() <= tiempo) {
            ready.addLast(procesos.get(idx));
            idx++;
        }

        while (completados < n) {
            if (ready.isEmpty()) {
                // Avanzar tiempo al próximo arribo si no hay listos
                if (idx < n) {
                    tiempo = Math.max(tiempo, procesos.get(idx).getArrivalTime());
                    // Encolar los que ya llegaron tras avanzar el tiempo
                    while (idx < n && procesos.get(idx).getArrivalTime() <= tiempo) {
                        ready.addLast(procesos.get(idx));
                        idx++;
                    }
                    continue;
                }
            } else {
                Process p = ready.removeFirst();

                if (!p.isStarted()) {
                    p.setResponseTime(tiempo - p.getArrivalTime());
                    p.setStarted(true);
                }

                int slice = Math.min(quantum, p.getRemainingTime());
                tiempo += slice;
                p.setRemainingTime(p.getRemainingTime() - slice);

                // Encolar procesos que llegaron durante este slice
                while (idx < n && procesos.get(idx).getArrivalTime() <= tiempo) {
                    ready.addLast(procesos.get(idx));
                    idx++;
                }

                if (p.getRemainingTime() > 0) {
                    // Todavía le falta CPU, vuelve al final
                    ready.addLast(p);
                } else {
                    // Proceso finalizado, calcular métricas
                    p.setCompletionTime(tiempo);
                    p.setTurnaroundTime(p.getCompletionTime() - p.getArrivalTime());
                    p.setWaitingTime(p.getTurnaroundTime() - p.getBurstTime());
                    p.setCompleted(true);
                    completados++;
                }
            }
        }
    }
}
