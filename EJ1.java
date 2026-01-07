
    public class VerificadorPolinomial {

    public static void main(String[] args) {
        // 🔹 Conjunto S
        int[] S = {2, 3, 7, 8, 10};

        // 🔹 Suma objetivo (T)
        int T = 11;

        // 🔹 Solución candidata 1 (correcta)
        int[] Y1 = {3, 8};

        // 🔹 Solución candidata 2 (incorrecta)
        int[] Y2 = {2, 7};

        // 🔹 Solución candidata 3 (inválida: usa número que no está en S)
        int[] Y3 = {4, 7};

        // 🔹 Verificación de cada caso
        System.out.println("Verificando subconjunto Y1 = {3, 8}: " + verificarSubsetSum(S, T, Y1));
        System.out.println("Verificando subconjunto Y2 = {2, 7}: " + verificarSubsetSum(S, T, Y2));
        System.out.println("Verificando subconjunto Y3 = {4, 7}: " + verificarSubsetSum(S, T, Y3));
    }

    /**
     * Verificador polinomial del problema Subset Sum
     * Verifica si el subconjunto Y pertenece a S y si su suma es igual a T
     */
    public static boolean verificarSubsetSum(int[] S, int T, int[] Y) {
        // 🔸 Verificar que todos los elementos de Y estén en S
        for (int y : Y) {
            boolean encontrado = false;
            for (int x : S) {
                if (y == x) {
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                System.out.println("❌ El número " + y + " no pertenece al conjunto S.");
                return false;
            }
        }

        // 🔸 Calcular la suma de los elementos de Y
        int suma = 0;
        for (int y : Y) {
            suma += y;
        }

        // 🔸 Comprobar si la suma coincide con la suma objetivo T
        if (suma == T) {
            System.out.println("✅ La suma de Y es " + suma + ", coincide con T = " + T);
            return true;
        } else {
            System.out.println("❌ La suma de Y es " + suma + ", no coincide con T = " + T);
            return false;
        }
    }
}
