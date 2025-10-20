# Multilevel Queue Scheduler (MLQ)

Implementación del algoritmo **Multilevel Queue Scheduling (MLQ)** en Java, aplicando el patrón **Strategy** para separar las políticas de planificación.  
Cada cola utiliza una estrategia distinta:
- Cola 1: Round Robin (quantum = 1)
- Cola 2: Round Robin (quantum = 3)
- Cola 3: Shortest Job First (SJF)

## Ejecución

```bash
javac src/*.java
java -cp src Main
```

El archivo de entrada (mlq001.txt) debe estar en la raíz del proyecto.
El resultado se genera en out.txt.

## Video de demostración
Enlace al video [Video sustentación](https://youtu.be/hlf7ez7I2h0)

## Informe
Ver [informe](Informe.md)

Proyecto realizado por Gerardo González
Curso: Sistemas Operativos — Universidad del Valle (2025-II)
