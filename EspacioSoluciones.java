public class EspacioSoluciones {

    static int[] conjunto = { 2, 3, 4, 1 };

    public static void main(String[] args) {

        System.out.println("Soluciones para valor = 7");
        generarSoluciones(7);

        System.out.println("\nSoluciones para valor = 5");
        generarSoluciones(5);
    }

    public static void generarSoluciones(int valor) {

        // Recorre todas las combinaciones binarias posibles (2^4 = 16)
        for (int i = 0; i < (1 << conjunto.length); i++) {

            int suma = 0;
            int[] solucion = new int[conjunto.length];

            for (int j = 0; j < conjunto.length; j++) {
                // Verifica si el bit está activo
                if ((i & (1 << j)) != 0) {
                    solucion[j] = 1;
                    suma += conjunto[j];
                }
            }

            // Si la suma coincide con el valor buscado
            if (suma == valor) {
                imprimirSolucion(solucion);
            }
        }
    }

    public static void imprimirSolucion(int[] solucion) {
        System.out.print("{ ");
        for (int i = 0; i < solucion.length; i++) {
            System.out.print(solucion[i]);
            if (i < solucion.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println(" }");
    }
}
