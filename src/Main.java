import java.io.*;
import java.util.*;
import java.nio.file.*;

public class Main {
    public static void main(String[] args) {
        try {
            // === Cargar archivo de entrada desde argumentos ===
            if (args == null || args.length == 0) {
                System.err.println("Uso: java Main <archivo_entrada>");
                return;
            }
            String inputArg = args[0];
            Path inputPath = resolverRutaEntrada(inputArg);

            // === Generar nombre de archivo de salida dinámicamente ===
            String inputFileName = inputPath.getFileName().toString();
            String outputFile = "solved_" + inputFileName;

            // === Leer procesos del archivo de entrada ===
            Map<Integer, List<Process>> colas = leerProcesos(inputPath);

            // === Configurar estrategias ===
            Map<Integer, Planificador> estrategias = Map.of(
                    1, new RR(1),
                    2, new RR(3),
                    3, new SJF()
            );

            // === Ejecutar MLQ ===
            MLQScheduler mlq = new MLQScheduler(estrategias);
            mlq.ejecutar(colas, outputFile);

            System.out.println("Procesamiento completo. Archivo de salida: " + outputFile);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Path resolverRutaEntrada(String fileName) throws FileNotFoundException {
        String wd = System.getProperty("user.dir");
        List<String> candidatos = List.of(
                fileName,
                "./" + fileName,
                "src/" + fileName,
                "../" + fileName,
                "../src/" + fileName,
                "../../" + fileName,
                "../../src/" + fileName
        );
        for (String c : candidatos) {
            Path p = Paths.get(c).normalize();
            if (Files.exists(p)) {
                return p.toAbsolutePath();
            }
        }
        throw new FileNotFoundException(fileName + " no encontrado. Directorio de trabajo: " + wd +
                ". Prueba pasando la ruta absoluta o relativa como argumento, p.ej.: ./src/" + fileName);
    }

    private static Map<Integer, List<Process>> leerProcesos(Path archivo) throws IOException {
        Map<Integer, List<Process>> colas = new HashMap<>();
        try (BufferedReader br = Files.newBufferedReader(archivo)) {
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
