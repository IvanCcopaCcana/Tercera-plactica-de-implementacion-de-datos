import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // Scanner compartido para toda la aplicación
    private static final Scanner scanner = new Scanner(System.in);

    // ---------------------------------------------------------------
    // Punto de entrada
    // ---------------------------------------------------------------

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap();  // Instancia principal del montículo

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║     IMPLEMENTACIÓN DE MIN-HEAP EN JAVA   ║");
        System.out.println("║         Universidad CENFOTEC — 2026      ║");
        System.out.println("╚══════════════════════════════════════════╝");

        menu(minHeap);  // Lanzar el menú principal

        System.out.println("\n¡Hasta luego!");
        scanner.close();
    }

    // ---------------------------------------------------------------
    // Menú principal
    // ---------------------------------------------------------------
    public static void menu(MinHeap minHeap) {
        int opcion = -1;

        while (opcion != 0) {
            System.out.println();
            System.out.println("┌─────────────────────────────────────┐");
            System.out.println("│           MENÚ  —  MIN-HEAP          │");
            System.out.println("├─────────────────────────────────────┤");
            System.out.println("│  1. Insertar valor                   │");
            System.out.println("│  2. Eliminar mínimo (eliminarMin)    │");
            System.out.println("│  3. Ver mínimo sin eliminar (peek)   │");
            System.out.println("│  4. Convertir arreglo a heap         │");
            System.out.println("│  5. Mostrar heap (arreglo)           │");
            System.out.println("│  6. Mostrar heap (árbol por niveles) │");
            System.out.println("│  0. Salir                            │");
            System.out.println("└─────────────────────────────────────┘");
            System.out.print("  Ingrese una opción: ");

            opcion = leerEntero();

            System.out.println();

            switch (opcion) {
                case 1 -> opcionInsertar(minHeap);
                case 2 -> minHeap.eliminarMin();
                case 3 -> minHeap.peek();
                case 4 -> opcionHeapify(minHeap);
                case 5 -> minHeap.mostrarHeap();
                case 6 -> minHeap.mostrarArbol();
                case 0 -> System.out.println("  Saliendo del programa...");
                default -> System.out.println("  ✖ Opción inválida. Por favor elija entre 0 y 6.");
            }
        }
    }

    // ---------------------------------------------------------------
    // Subrutinas de cada opción del menú
    // ---------------------------------------------------------------

    private static void opcionInsertar(MinHeap minHeap) {
        System.out.println("── Insertar valor(es) ──");
        System.out.println("  (Puede ingresar varios números separados por espacios)");
        System.out.print("  Valor(es): ");
        String linea = scanner.nextLine().trim();

        if (linea.isEmpty()) {
            System.out.println("  ✖ No se ingresó ningún valor.");
            return;
        }

        String[] partes = linea.split("\\s+");
        for (String parte : partes) {
            try {
                int valor = Integer.parseInt(parte);
                minHeap.insertar(valor);
            } catch (NumberFormatException e) {
                System.out.println("  ✖ \"" + parte + "\" no es un entero válido, se omitió.");
            }
        }
    }

    private static void opcionHeapify(MinHeap minHeap) {
        System.out.println("── Convertir arreglo a min-heap (heapify) ──");
        System.out.println("  Ingrese los números del arreglo separados por espacios:");
        System.out.print("  Arreglo: ");
        String linea = scanner.nextLine().trim();

        if (linea.isEmpty()) {
            System.out.println("  ✖ No se ingresó ningún valor.");
            return;
        }

        ArrayList<Integer> arreglo = new ArrayList<>();
        String[] partes = linea.split("\\s+");

        for (String parte : partes) {
            try {
                arreglo.add(Integer.parseInt(parte));
            } catch (NumberFormatException e) {
                System.out.println("  ✖ \"" + parte + "\" no es un entero válido, se omitió.");
            }
        }

        if (!arreglo.isEmpty()) {
            System.out.print("  Arreglo ingresado: [ ");
            for (int i = 0; i < arreglo.size(); i++) {
                System.out.print(arreglo.get(i));
                if (i < arreglo.size() - 1) System.out.print(", ");
            }
            System.out.println(" ]");
            minHeap.heapify(arreglo);
            minHeap.mostrarHeap();
        }
    }

    // ---------------------------------------------------------------
    // Utilidad de lectura segura
    // ---------------------------------------------------------------

    private static int leerEntero() {
        try {
            String linea = scanner.nextLine().trim();
            return Integer.parseInt(linea);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
