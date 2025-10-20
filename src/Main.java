import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            // === Cargar archivo de entrada ===
            String inputFile = "mlq001.txt";
            String outputFile = "mlq001_out.txt";
            Map<Integer, List<Process>> colas = leerProcesos(inputFile);

            // === Configurar estrategias ===
            Map<Integer, Planificador> estrategias = Map.of(
                    1, new RR(1),
                    2, new RR(3),
                    3, new SJF()
            );

            // === Ejecutar MLQ ===
            MLQScheduler mlq = new MLQScheduler(estrategias);
            mlq.ejecutar(colas, outputFile);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Map<Integer, List<Process>> leerProcesos(String archivo) throws IOException {
        Map<Integer, List<Process>> colas = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("#") || linea.trim().isEmpty()) continue;

                String[] partes = linea.split(";");
                String id = partes[0].trim();
                int burst = Integer.parseInt(partes[1].trim());
                int arrival = Integer.parseInt(partes[2].trim());
                int queue = Integer.parseInt(partes[3].trim());
                int priority = Integer.parseInt(partes[4].trim());

                Process p = new Process(id, burst, arrival, queue, priority);
                colas.computeIfAbsent(queue, k -> new ArrayList<>()).add(p);
            }
        }
        return colas;
    }
}
