import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del Algoritmo Voraz para el Problema de Cambio de Moneda
 * 
 * @author Sistema de Algoritmos Voraces
 * @version 1.0
 */
public class CambioMonedaVoraz {

    /**
     * Clase interna para almacenar el resultado del algoritmo
     */
    static class ResultadoCambio {
        private int totalMonedas;
        private List<Integer> monedasUtilizadas;
        private boolean cambioExacto;

        public ResultadoCambio() {
            this.totalMonedas = 0;
            this.monedasUtilizadas = new ArrayList<>();
            this.cambioExacto = true;
        }

        public void agregarMoneda(int valor) {
            totalMonedas++;
            monedasUtilizadas.add(valor);
        }

        public int getTotalMonedas() {
            return totalMonedas;
        }

        public List<Integer> getMonedasUtilizadas() {
            return monedasUtilizadas;
        }

        public void setCambioExacto(boolean exacto) {
            this.cambioExacto = exacto;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Total de monedas: ").append(totalMonedas).append("\n");
            sb.append("Monedas utilizadas: ").append(monedasUtilizadas).append("\n");
            sb.append("Cambio exacto: ").append(cambioExacto ? "Sí" : "No");
            return sb.toString();
        }
    }

    /**
     * Método principal: Algoritmo Voraz para Cambio de Moneda
     * 
     * @param monedas Array con las denominaciones disponibles
     * @param monto   Cantidad objetivo a devolver
     * @return ResultadoCambio con las monedas utilizadas
     */
    public static ResultadoCambio calcularCambio(int[] monedas, int monto) {
        // PASO 1: Ordenar monedas de mayor a menor
        Integer[] monedasOrdenadas = new Integer[monedas.length];
        for (int i = 0; i < monedas.length; i++) {
            monedasOrdenadas[i] = monedas[i];
        }
        Arrays.sort(monedasOrdenadas, (a, b) -> b - a);

        ResultadoCambio resultado = new ResultadoCambio();
        int montoRestante = monto;

        // PASO 2: Selección voraz
        for (int moneda : monedasOrdenadas) {
            // Calcular cuántas monedas de esta denominación podemos usar
            while (montoRestante >= moneda) {
                resultado.agregarMoneda(moneda);
                montoRestante -= moneda;
            }

            // Si ya completamos el monto, terminar
            if (montoRestante == 0) {
                break;
            }
        }

        // Verificar si se logró dar cambio exacto
        if (montoRestante > 0) {
            resultado.setCambioExacto(false);
        }

        return resultado;
    }

    /**
     * Versión con salida detallada para propósitos educativos
     */
    public static ResultadoCambio calcularCambioDetallado(int[] monedas, int monto) {
        // Ordenar monedas
        Integer[] monedasOrdenadas = new Integer[monedas.length];
        for (int i = 0; i < monedas.length; i++) {
            monedasOrdenadas[i] = monedas[i];
        }
        Arrays.sort(monedasOrdenadas, (a, b) -> b - a);

        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║    ALGORITMO VORAZ - PROCESO          ║");
        System.out.println("╚═══════════════════════════════════════╝");
        System.out.println("\nMonto objetivo: " + monto);
        System.out.println("Monedas disponibles (ordenadas): " + Arrays.toString(monedasOrdenadas));
        System.out.println("\n--- SELECCIÓN VORAZ ---\n");

        ResultadoCambio resultado = new ResultadoCambio();
        int montoRestante = monto;

        for (int moneda : monedasOrdenadas) {
            int cantidad = 0;

            while (montoRestante >= moneda) {
                resultado.agregarMoneda(moneda);
                montoRestante -= moneda;
                cantidad++;
            }

            if (cantidad > 0) {
                System.out.printf("► Usar %d moneda(s) de %d → Subtotal: %d\n",
                        cantidad, moneda, cantidad * moneda);
                System.out.printf("  Monto restante: %d\n\n", montoRestante);
            }

            if (montoRestante == 0) {
                break;
            }
        }

        if (montoRestante > 0) {
            System.out.println("⚠ ADVERTENCIA: No se pudo dar cambio exacto.");
            System.out.println("  Faltan: " + montoRestante);
            resultado.setCambioExacto(false);
        } else {
            System.out.println("✓ Cambio completado exitosamente");
        }

        System.out.println("\n--- RESULTADO FINAL ---");
        System.out.println(resultado);

        return resultado;
    }

    /**
     * Método auxiliar para ejecutar casos de prueba
     */
    public static void ejecutarPrueba(String nombre, int[] monedas, int monto) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("PRUEBA: " + nombre);
        System.out.println("=".repeat(50));

        ResultadoCambio resultado = calcularCambioDetallado(monedas, monto);
    }

    /**
     * Método main con casos de prueba
     */
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║  ALGORITMO VORAZ - CAMBIO DE MONEDA           ║");
        System.out.println("║  Implementación en Java                        ║");
        System.out.println("╚════════════════════════════════════════════════╝");

        // PRUEBA 1: Sistema estadounidense
        int[] monedasUS = { 1, 5, 10, 25, 50 };
        ejecutarPrueba("Sistema Estadounidense - 67 centavos", monedasUS, 67);

        // PRUEBA 2: Cantidad pequeña
        ejecutarPrueba("Sistema Estadounidense - 11 centavos", monedasUS, 11);

        // PRUEBA 3: Sistema problemático (NO óptimo con voraz)
        int[] monedasProblema = { 1, 3, 4 };
        ejecutarPrueba("Sistema Problemático - 6 centavos", monedasProblema, 6);
        System.out.println("\n⚠ NOTA: En este caso, la solución óptima sería 3+3 (2 monedas)");
        System.out.println("        pero el algoritmo voraz da 4+1+1 (3 monedas)");

        // PRUEBA 4: Sistema europeo
        int[] monedasEuro = { 1, 2, 5, 10, 20, 50, 100 };
        ejecutarPrueba("Sistema Europeo - 87 céntimos", monedasEuro, 87);

        // PRUEBA 5: Uso simple del método sin detalles
        System.out.println("\n" + "=".repeat(50));
        System.out.println("PRUEBA RÁPIDA (sin detalles)");
        System.out.println("=".repeat(50));
        ResultadoCambio resultado = calcularCambio(monedasUS, 99);
        System.out.println("\nMonto: 99 centavos");
        System.out.println(resultado);
    }
}