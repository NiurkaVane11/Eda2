import java.util.Scanner;
import java.util.ArrayList;

public class MainBFMultipatron {

    public static int totalComp = 0;
    public static int totalDesp = 0;
    public static int totalFails = 0;
    static long totTime = 0;

    static class ResultadoPatron {
        String patron;
        int posicion;
        int comparaciones;
        int desplazamientos;
        int fallos;

        ResultadoPatron(String patron, int posicion, int comp, int desp, int fallos) {
            this.patron = patron;
            this.posicion = posicion;
            this.comparaciones = comp;
            this.desplazamientos = desp;
            this.fallos = fallos;
        }
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Scanner leer = new Scanner(System.in);

        System.out.println("=== BÚSQUEDA MULTIPATRÓN - FUERZA BRUTA ===\n");

        System.out.println("Ingrese la cadena del Texto: ");
        char[] text = leer.next().toCharArray();

        System.out.println("¿Cuántos patrones desea buscar?: ");
        int numPatrones = leer.nextInt();

        ArrayList<char[]> patrones = new ArrayList<>();

        for (int i = 0; i < numPatrones; i++) {
            System.out.println("Ingrese el patrón " + (i + 1) + ": ");
            patrones.add(leer.next().toCharArray());
        }

        // Buscar todos los patrones
        ArrayList<ResultadoPatron> resultados = findMultiPatron(text, patrones);

        // Mostrar resultados
        System.out.println("\n=== RESULTADOS ===");
        System.out.println("Texto: " + new String(text));
        System.out.println();

        for (ResultadoPatron res : resultados) {
            System.out.println("Patrón: " + res.patron);
            if (res.posicion >= 0) {
                System.out.println("  ✓ ENCONTRADO en posición: " + res.posicion);
                System.out.println("  - Comparaciones: " + res.comparaciones);
                System.out.println("  - Desplazamientos: " + res.desplazamientos);
                System.out.println("  - Fallos: " + res.fallos);
            } else {
                System.out.println("  ✗ NO ENCONTRADO");
                System.out.println("  - Comparaciones: " + res.comparaciones);
            }
            System.out.println();
        }

        System.out.println("=== ESTADÍSTICAS TOTALES ===");
        System.out.println("Total Comparaciones: " + totalComp);
        System.out.println("Total Desplazamientos: " + totalDesp);
        System.out.println("Total Fallos: " + totalFails);

        totTime += (System.currentTimeMillis() - startTime);
        System.out.println("Tiempo de cómputo: " + totTime + " [ms]");

        leer.close();
    }

    public static ArrayList<ResultadoPatron> findMultiPatron(char[] text, ArrayList<char[]> patrones) {
        ArrayList<ResultadoPatron> resultados = new ArrayList<>();

        for (char[] pattern : patrones) {
            int comp = 0;
            int fails = 0;
            int desp = 0;
            int resultado = findBruteF(text, pattern, comp, fails, desp);

            // Calcular estadísticas para este patrón
            int n = text.length;
            int m = pattern.length;
            comp = 0;
            fails = 0;
            desp = 0;

            for (int i = 0; i <= n - m; i++) {
                int k = 0;
                while (k < m && text[i + k] == pattern[k]) {
                    k++;
                }
                comp += k;
                if (k == m) {
                    desp = i;
                    break;
                }
                fails = i;
                desp = fails;
            }

            totalComp += comp;
            totalDesp += (resultado >= 0 ? desp + 1 : desp);
            totalFails += (resultado >= 0 ? fails + 1 : fails);

            resultados.add(new ResultadoPatron(
                    new String(pattern),
                    resultado,
                    comp,
                    resultado >= 0 ? desp + 1 : desp,
                    resultado >= 0 ? fails + 1 : fails));
        }

        return resultados;
    }

    public static int findBruteF(char[] text, char[] pattern, int comp, int fails, int desp) {
        int n = text.length;
        int m = pattern.length;

        for (int i = 0; i <= n - m; i++) {
            int k = 0;
            while (k < m && text[i + k] == pattern[k]) {
                k++;
            }
            if (k == m) {
                return i;
            }
        }
        return -1;
    }
}
