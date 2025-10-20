import java.util.*;
import java.io.*;

public class MLQScheduler {
    private Map<Integer, Planificador> estrategias;

    public MLQScheduler(Map<Integer, Planificador> estrategias) {
        this.estrategias = estrategias;
    }

    public void ejecutar(Map<Integer, List<Process>> colas, String outputFile) throws IOException {
        List<Process> resultados = new ArrayList<>();

        for (int nivel : estrategias.keySet()) {
            Planificador plan = estrategias.get(nivel);
            List<Process> lista = colas.get(nivel);
            if (lista != null && !lista.isEmpty()) {
                System.out.println("\n=== Ejecutando Cola " + nivel + " ===");
                plan.ejecutar(lista);
                resultados.addAll(lista);
            }
        }

        escribirSalida(resultados, outputFile);
    }

    private void escribirSalida(List<Process> procesos, String nombreArchivo) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
            double totalWT = 0, totalCT = 0, totalRT = 0, totalTAT = 0;

            bw.write("# etiqueta; BT; AT; Q; P; WT; CT; RT; TAT\n");
            for (Process p : procesos) {
                bw.write(String.format("%s;%d;%d;%d;%d;%d;%d;%d;%d\n",
                        p.getId(), p.getBurstTime(), p.getArrivalTime(), p.getQueue(), p.getPriority(),
                        p.getWaitingTime(), p.getCompletionTime(), p.getResponseTime(), p.getTurnaroundTime()));

                totalWT += p.getWaitingTime();
                totalCT += p.getCompletionTime();
                totalRT += p.getResponseTime();
                totalTAT += p.getTurnaroundTime();
            }

            int n = procesos.size();
            bw.write(String.format("WT=%.2f; CT=%.2f; RT=%.2f; TAT=%.2f\n",
                    totalWT / n, totalCT / n, totalRT / n, totalTAT / n));
        }

        System.out.println("\nArchivo de salida generado: " + nombreArchivo);
    }
}
