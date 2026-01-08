public class Mochila01 {

    public static void main(String[] args) {

        // Capacidad de la mochila
        int W = 50;

        // Pesos y valores de los objetos
        int[] pesos = { 10, 20, 30, 15 };
        int[] valores = { 60, 100, 120, 45 };

        int n = pesos.length;

        // Matriz DP
        int[][] dp = new int[n + 1][W + 1];

        // Construcción de la tabla DP
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= W; w++) {

                if (pesos[i - 1] <= w) {
                    dp[i][w] = Math.max(
                            valores[i - 1] + dp[i - 1][w - pesos[i - 1]],
                            dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // Resultado óptimo
        System.out.println("Valor máximo obtenido: " + dp[n][W]);

        // Traza de objetos seleccionados
        int w = W;
        System.out.println("Objetos seleccionados:");

        for (int i = n; i > 0 && w > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                System.out.println("Objeto " + i +
                        " (Peso: " + pesos[i - 1] +
                        ", Valor: " + valores[i - 1] + ")");
                w -= pesos[i - 1];
            }
        }
    }
}
