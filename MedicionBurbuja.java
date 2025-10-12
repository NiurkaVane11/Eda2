public class MedicionBurbuja {

    public static void main(String[] args) {
        // Crear arreglo de prueba
        int tamaño = 10000;
        int[] datos1 = generarArreglo(tamaño);
        int[] datos2 = datos1.clone(); // Copia para medir dos veces

        System.out.println("=== Medición de Algoritmo de Burbuja ===\n");

        // Medición con nanoTime()
        long inicioNano = System.nanoTime();
        ordenamientoBurbuja(datos1);
        long finNano = System.nanoTime();
        long duracionNano = finNano - inicioNano;

        // Medición con currentTimeMillis()
        long inicioMilis = System.currentTimeMillis();
        ordenamientoBurbuja(datos2);
        long finMilis = System.currentTimeMillis();
        long duracionMilis = finMilis - inicioMilis;

        // Mostrar resultados
        System.out.println("Tamaño del arreglo: " + tamaño);
        System.out.println("\nResultados:");
        System.out.println("- System.nanoTime(): " + duracionNano + " nanosegundos");
        System.out.println("- System.nanoTime(): " + (duracionNano / 1_000_000.0) + " milisegundos");
        System.out.println("\n- System.currentTimeMillis(): " + duracionMilis + " milisegundos");
    }

    // Algoritmo de burbuja
    public static void ordenamientoBurbuja(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Intercambiar
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // Generar arreglo aleatorio
    public static int[] generarArreglo(int tamaño) {
        int[] arr = new int[tamaño];
        for (int i = 0; i < tamaño; i++) {
            arr[i] = (int) (Math.random() * 10000);
        }
        return arr;
    }
}