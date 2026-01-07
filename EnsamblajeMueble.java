import java.util.Random;

// Clase que representa una pieza del mueble
class Pieza {
    String nombre;
    boolean ensamblada;

    Pieza(String nombre) {
        this.nombre = nombre;
        this.ensamblada = false; // al inicio la pieza no está ensamblada
    }
}

// Clase que representa un mueble con varias piezas
class Mueble {
    String nombre;
    Pieza[] piezas;

    Mueble(String nombre, Pieza[] piezas) {
        this.nombre = nombre;
        this.piezas = piezas;
    }
}

// Clase que representa un trabajador que realiza las tareas
class Trabajador {
    String nombre;
    Random random = new Random();

    // Método para ensamblar todas las piezas del mueble
    void ensamblar(Mueble mueble) {
        System.out.println(nombre + " empieza a ensamblar " + mueble.nombre);
        for (Pieza p : mueble.piezas) {
            p.ensamblada = true; // pieza ensamblada
            System.out.println("  Ensamblada la pieza: " + p.nombre);
            sleepRandom(); // simula tiempo de trabajo
        }
    }

    // Método para revisar la calidad del mueble
    boolean revisarCalidad(Mueble mueble) {
        System.out.println(nombre + " revisa calidad de " + mueble.nombre);
        sleepRandom(); // simula tiempo de revisión
        boolean aprobado = random.nextInt(100) < 90; // 90% chance de pasar
        System.out.println("  Calidad " + (aprobado ? "aprobada" : "rechazada"));
        return aprobado;
    }

    // Método para empacar el mueble
    void empacar(Mueble mueble) {
        System.out.println(nombre + " empaca " + mueble.nombre);
        sleepRandom(); // simula tiempo de empaque
    }

    // Método auxiliar para simular tiempo de tarea
    private void sleepRandom() {
        try {
            Thread.sleep(200 + random.nextInt(300)); // entre 200ms y 500ms
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class EnsamblajeMueble {

    public static void main(String[] args) {
        // Inicio de medición de tiempo
        long startTime = System.currentTimeMillis();

        // Paso 1: Crear piezas y mueble (simula "tomar piezas del inventario")
        Pieza[] piezas = { new Pieza("Tabla"), new Pieza("Puerta"), new Pieza("Tornillos") };
        Mueble mueble = new Mueble("Mueble Supermaxi", piezas);

        // Paso 2: Crear trabajador
        Trabajador trabajador = new Trabajador();
        trabajador.nombre = "Juan";

        // Paso 3: Ensamblaje de piezas
        trabajador.ensamblar(mueble);

        // Paso 4: Revisión de calidad, si falla se reensambla (bucle while)
        while (!trabajador.revisarCalidad(mueble)) {
            System.out.println("Reparando mueble...");
            trabajador.ensamblar(mueble);
        }

        // Paso 5: Empaque del mueble
        trabajador.empacar(mueble);

        // Fin: Métricas
        long endTime = System.currentTimeMillis();
        System.out.println("Tiempo total de ejecución: " + (endTime - startTime) + " ms");

        // Métrica de memoria usada
        Runtime runtime = Runtime.getRuntime();
        long memoryUsed = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Memoria usada: " + memoryUsed / 1024 + " KB");
    }
}
