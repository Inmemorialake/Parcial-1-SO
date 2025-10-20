# Informe Parcial Práctico – Sistemas Operativos
## Multilevel Queue Scheduler (MLQ)

**Estudiante:** Gerardo González  
**Curso:** Sistemas Operativos — Universidad del Valle  
**Periodo:** 2025-II  
**Docente:** Jefferson Amado Peña Torres  

---

## 1. Objetivo

Implementar un planificador de CPU utilizando el algoritmo **Multilevel Queue Scheduling (MLQ)** en el lenguaje Java, aplicando principios de **Programación Orientada a Objetos (POO)** y el **patrón de diseño Strategy**, con el fin de simular colas de ejecución con diferentes políticas de planificación.

---

## 2. Descripción general

El proyecto simula la ejecución de procesos distribuidos en tres colas, cada una con una política distinta:

- **Cola 1:** Round Robin (quantum = 1)  
- **Cola 2:** Round Robin (quantum = 3)  
- **Cola 3:** Shortest Job First (SJF)

Cada cola se ejecuta de forma secuencial, respetando la prioridad de las colas y manteniendo una línea de tiempo global de ejecución.  
El sistema calcula para cada proceso las métricas de desempeño:  
**Completion Time (CT), Waiting Time (WT), Response Time (RT)** y **Turnaround Time (TAT)**, además de sus promedios generales.

---

## 3. Diseño e implementación

El diseño sigue el patrón **Strategy**, definiendo una interfaz `Planificador` que permite intercambiar dinámicamente los algoritmos de planificación sin modificar la estructura principal.

### Clases principales
- **Process:** Representa un proceso con sus atributos (BT, AT, Q, P) y métricas calculadas.  
- **Planificador:** Interfaz que define el método `ejecutar(List<Process>)`.  
- **RR:** Implementa Round Robin con quantum variable.  
- **SJF:** Implementa Shortest Job First no expropiativo.  
- **MLQScheduler:** Coordina la ejecución de las colas y genera el archivo de salida.  
- **Main:** Controla la lectura del archivo de entrada, configuración de colas y ejecución del simulador.

El programa lee un archivo de entrada (`mlq00X.txt`), simula la ejecución de los procesos y genera un archivo `out.txt` con los resultados y promedios de las métricas.

---

## 4. Casos de prueba

### **Caso 1 – mlq001.txt**
**Entrada:**

# etiqueta; BT; AT; Q; P

A; 6; 0; 1; 5
B; 9; 0; 1; 4
C; 10; 0; 2; 3
D; 15; 0; 2; 3
E; 8; 0; 3; 2

**Salida:**


# etiqueta; BT; AT; Q; P; WT; CT; RT; TAT

A;6;0;1;5;5;11;0;11
B;9;0;1;4;6;15;1;15
C;10;0;2;3;9;19;0;19
D;15;0;2;3;10;25;3;25
E;8;0;3;2;0;8;0;8
WT=6.00; CT=15.60; RT=0.80; TAT=15.60

---

### **Caso 2 – mlq002.txt**
**Entrada:**


# Archivo: mlq002.txt

# 09102024

p1; 20; 0; 1; 5
p2; 10; 2; 1; 4
p3; 15; 4; 2; 3
p4; 5; 6; 3; 2
p5; 8; 8; 3; 1

**Salida:**


# etiqueta; BT; AT; Q; P; WT; CT; RT; TAT

p1;20;0;1;5;15;35;0;35
p2;10;2;1;4;12;24;3;22
p3;15;4;2;3;10;25;0;25
p4;5;6;3;2;2;11;0;11
p5;8;8;3;1;1;9;0;9
WT=8.00; CT=20.80; RT=0.60; TAT=20.40

---

### **Caso 3 – mlq003.txt**
**Entrada:**

# Archivo: mlq003.txt

# 02102024

p1; 30; 0; 1; 5
p2; 12; 1; 2; 4
p3; 18; 3; 2; 3
p4; 25; 5; 3; 2
p5; 10; 7; 3; 1

**Salida:**

# etiqueta; BT; AT; Q; P; WT; CT; RT; TAT

p1;30;0;1;5;27;57;0;57
p2;12;1;2;4;10;22;0;22
p3;18;3;2;3;11;29;2;29
p4;25;5;3;2;3;28;0;28
p5;10;7;3;1;2;12;0;12
WT=10.60; CT=29.60; RT=0.40; TAT=29.60

---

## 5. Conclusiones

- El patrón Strategy permitió mantener un diseño modular y flexible, facilitando el cambio o adición de algoritmos de planificación.  
- La simulación maneja correctamente tiempos de llegada, quantums y prioridades entre colas.  
- El sistema calcula métricas individuales y globales, cumpliendo los requisitos del enunciado del parcial.  
- La implementación demuestra el uso práctico de la POO para resolver problemas típicos de planificación de CPU.
