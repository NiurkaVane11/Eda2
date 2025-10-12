import java.util.Arrays;
import java.util.Random;

public class MedirBusquedas {
    public static void main(String[] args) {
        Random random = new Random();

        // Arrays de distintos tamaños
        int[] array10 = new int[10];
        int[] array100 = new int[100];
        int[] array500 = new int[500];

        // Llenamos con valores aleatorios (0 a 999)
        for (int i = 0; i < array10.length; i++)
            array10[i] = random.nextInt(1000);
        for (int i = 0; i < array100.length; i++)
            array100[i] = random.nextInt(1000);
        for (int i = 0; i < array500.length; i++)
            array500[i] = random.nextInt(1000);

        // Ordenamos los arrays (para búsqueda binaria)
        Arrays.sort(array10);
        Arrays.sort(array100);
        Arrays.sort(array500);

        // Número a buscar (puede o no estar en el array)
        int target = array10[random.nextInt(array10.length)];
        System.out.println("Número a buscar: " + target + "\n");

        // ----- Pruebas -----
        medirTiempos(array10, target, "Array de 10 elementos");
        medirTiempos(array100, target, "Array de 100 elementos");
        medirTiempos(array500, target, "Array de 500 elementos");
    }

    // Función para medir los tiempos de búsqueda
    public static void medirTiempos(int[] array, int target, String nombre) {
        System.out.println(nombre);

        // Búsqueda lineal
        long inicio = System.currentTimeMillis();
        int resLineal = busquedaLineal(array, target);
        long fin = System.currentTimeMillis();
        System.out.println("  Búsqueda lineal -> posición: " + resLineal + " | tiempo: " + (fin - inicio) + " ms");

        // Búsqueda binaria
        inicio = System.currentTimeMillis();
        int resBinaria = busquedaBinaria(array, target);
        fin = System.currentTimeMillis();
        System.out.println("  Búsqueda binaria -> posición: " + resBinaria + " | tiempo: " + (fin - inicio) + " ms\n");
    }

    // Búsqueda lineal
    public static int busquedaLineal(int[] array, int valor) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == valor)
                return i;
        }
        return -1;
    }

    // Búsqueda binaria (requiere array ordenado)
    public static int busquedaBinaria(int[] array, int valor) {
        int inicio = 0, fin = array.length - 1;
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            if (array[medio] == valor)
                return medio;
            else if (array[medio] < valor)
                inicio = medio + 1;
            else
                fin = medio - 1;
        }
        return -1;
    }
}
