public class BoyerMoore {

    // Tamaño del alfabeto (caracteres ASCII)
    private static final int ALPHABET_SIZE = 256;

    /**
     * Preprocesamiento: Crea la tabla de Bad Character
     * 
     * @param patron El patrón a buscar
     * @return Arreglo con las posiciones de cada carácter
     */
    private static int[] calcularBadCharacter(String patron) {
        int m = patron.length();
        int[] badChar = new int[ALPHABET_SIZE];

        // Inicializar todos con -1 (carácter no está en el patrón)
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            badChar[i] = -1;
        }

        // Llenar con la última posición de cada carácter en el patrón
        for (int i = 0; i < m; i++) {
            badChar[patron.charAt(i)] = i;
        }

        return badChar;
    }

    /**
     * Preprocesamiento: Crea la tabla de Good Suffix
     * 
     * @param patron El patrón a buscar
     * @return Arreglo con los desplazamientos del good suffix
     */
    private static int[] calcularGoodSuffix(String patron) {
        int m = patron.length();
        int[] shift = new int[m + 1];
        int[] border = new int[m + 1];

        // Inicializar
        for (int i = 0; i < m + 1; i++) {
            shift[i] = 0;
            border[i] = 0;
        }

        // Calcular los bordes
        int i = m, j = m + 1;
        border[i] = j;

        while (i > 0) {
            while (j <= m && patron.charAt(i - 1) != patron.charAt(j - 1)) {
                if (shift[j] == 0) {
                    shift[j] = j - i;
                }
                j = border[j];
            }
            i--;
            j--;
            border[i] = j;
        }

        // Caso: sufijo completo no aparece en el patrón
        j = border[0];
        for (i = 0; i <= m; i++) {
            if (shift[i] == 0) {
                shift[i] = j;
            }
            if (i == j) {
                j = border[j];
            }
        }

        return shift;
    }

    /**
     * Algoritmo de Boyer-Moore completo
     * 
     * @param texto  El texto donde buscar
     * @param patron El patrón a buscar
     */
    public static void buscar(String texto, String patron) {
        int n = texto.length();
        int m = patron.length();

        if (m == 0 || m > n) {
            System.out.println("Patrón no válido o más largo que el texto");
            return;
        }

        // Preprocesamiento
        int[] badChar = calcularBadCharacter(patron);
        int[] goodSuffix = calcularGoodSuffix(patron);

        System.out.println("=== BÚSQUEDA BOYER-MOORE ===");
        System.out.println("Texto: " + texto);
        System.out.println("Patrón: " + patron);
        System.out.println("\nProceso de búsqueda:");
        System.out.println("-------------------");

        int desplazamiento = 0;
        int comparaciones = 0;
        int coincidenciasEncontradas = 0;

        while (desplazamiento <= n - m) {
            int j = m - 1;

            // Imprimir alineación actual
            imprimirAlineacion(texto, patron, desplazamiento);

            // Comparar de derecha a izquierda
            while (j >= 0 && patron.charAt(j) == texto.charAt(desplazamiento + j)) {
                j--;
                comparaciones++;
            }

            if (j < 0) {
                // Patrón encontrado
                coincidenciasEncontradas++;
                System.out.println("✓ ¡COINCIDENCIA en posición " + desplazamiento + "!");

                // Usar good suffix para el siguiente desplazamiento
                desplazamiento += goodSuffix[0];
            } else {
                comparaciones++;

                // Calcular desplazamiento usando ambas heurísticas
                char charMalo = texto.charAt(desplazamiento + j);
                int bcShift = j - badChar[charMalo];
                int gsShift = goodSuffix[j + 1];

                int salto = Math.max(bcShift, gsShift);

                System.out.println("✗ Desajuste en posición " + j + " (carácter '" + charMalo + "')");
                System.out.println("  Bad Character sugiere saltar: " + bcShift);
                System.out.println("  Good Suffix sugiere saltar: " + gsShift);
                System.out.println("  → Saltando " + salto + " posiciones\n");

                desplazamiento += salto;
            }
        }

        // Resumen
        System.out.println("\n=== RESUMEN ===");
        System.out.println("Coincidencias encontradas: " + coincidenciasEncontradas);
        System.out.println("Comparaciones realizadas: " + comparaciones);
        System.out.println(
                "Eficiencia: " + String.format("%.2f", (double) comparaciones / n) + " comparaciones por carácter");
    }

    /**
     * Imprime la alineación actual del patrón con el texto
     */
    private static void imprimirAlineacion(String texto, String patron, int desplazamiento) {
        System.out.println("\nTexto:  " + texto);
        System.out.print("Patrón: ");
        for (int i = 0; i < desplazamiento; i++) {
            System.out.print(" ");
        }
        System.out.println(patron);
    }

    /**
     * Método main con ejemplos de prueba
     */
    public static void main(String[] args) {
        // Ejemplo 1: Del ejercicio
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║         EJEMPLO 1                      ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        buscar("ABABDABACADABABCABAB", "ABABC");

        System.out.println("\n\n");

        // Ejemplo 2: Múltiples coincidencias
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║         EJEMPLO 2                      ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        buscar("AABAACAADAABAABA", "AABA");

        System.out.println("\n\n");

        // Ejemplo 3: Sin coincidencias
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║         EJEMPLO 3                      ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        buscar("ABCDEFGHIJKLMNOP", "XYZ");

        System.out.println("\n\n");

        // Ejemplo 4: Patrón largo
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║         EJEMPLO 4                      ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        buscar("GCATCGCAGAGAGTATACAGTACG", "GCAGAGAG");
    }
}s