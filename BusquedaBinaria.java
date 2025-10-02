package Eda2;

import java.util.Scanner;

public class BusquedaBinaria {

    static long totTime = 0;

    // Método de búsqueda binaria
    static int busquedaBinaria(int[] arr, int x) {
        int inicio = 0;
        int fin = arr.length - 1;

        while (inicio <= fin) {
            int medio = inicio + (fin - inicio) / 2;

            if (arr[medio] == x) {
                return medio; // encontrado
            }
            if (arr[medio] < x) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }
        return -1; // no encontrado
    }

    public static void main(String args[]) {
        Scanner keyboard = new Scanner(System.in);

        // Tres arrays ordenados de distintos tamaños
        int[] array1 = { 1, 3, 5, 7, 9 };
        int[] array2 = { 2, 4, 6, 8, 10, 12, 14 };
        int[] array3 = { 0, 5, 10, 15, 20, 25, 30, 35 };

        System.out.println("Ingrese el número a buscar: ");
        int buscar = keyboard.nextInt();

        // Buscar en array1
        long startTime = System.currentTimeMillis();
        int resultado1 = busquedaBinaria(array1, buscar);
        totTime += (System.currentTimeMillis() - startTime);
        System.out.println("Resultado en array1: "
                + (resultado1 != -1 ? "Encontrado en posición " + resultado1 : "No encontrado"));

        // Buscar en array2
        startTime = System.currentTimeMillis();
        int resultado2 = busquedaBinaria(array2, buscar);
        totTime += (System.currentTimeMillis() - startTime);
        System.out.println("Resultado en array2: "
                + (resultado2 != -1 ? "Encontrado en posición " + resultado2 : "No encontrado"));

        // Buscar en array3
        startTime = System.currentTimeMillis();
        int resultado3 = busquedaBinaria(array3, buscar);
        totTime += (System.currentTimeMillis() - startTime);
        System.out.println("Resultado en array3: "
                + (resultado3 != -1 ? "Encontrado en posición " + resultado3 : "No encontrado"));

        // Tiempo total
        System.out.println("Tiempo total de ejecución en milisegundos: " + totTime);

        keyboard.close();
    }
}
