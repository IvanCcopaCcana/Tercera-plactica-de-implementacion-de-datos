import java.util.ArrayList;

public class MinHeap {

    // ---------------------------------------------------------------
    // Atributos
    // ---------------------------------------------------------------

    /** Arreglo interno que almacena los elementos del montículo. */
    private ArrayList<Integer> heap;

    // ---------------------------------------------------------------
    // Constructor
    // ---------------------------------------------------------------

    /**
     * Crea un MinHeap vacío.
     */
    public MinHeap() {
        heap = new ArrayList<>();
    }

    // ---------------------------------------------------------------
    // Métodos auxiliares de índices
    // ---------------------------------------------------------------

    /**
     * Retorna el índice del hijo izquierdo del nodo en la posición i.
     */
    private int leftChild(int i) {
        return 2 * i + 1;
    }

    /**
     * Retorna el índice del hijo derecho del nodo en la posición i.
     */
    private int rightChild(int i) {
        return 2 * i + 2;
    }

    /**
     * Retorna el índice del padre del nodo en la posición i.
     */
    private int parent(int i) {
        return (i - 1) / 2;
    }

    /**
     * Intercambia los elementos en las posiciones i y j del arreglo.
     */
    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    // ---------------------------------------------------------------
    // upHeapify — sube un nodo hasta su posición correcta
    // ---------------------------------------------------------------

    /**
     * Restaura la propiedad del montículo subiendo el nodo en la posición i
     * hacia la raíz mientras sea menor que su padre.
     */
    private void upHeapify(int i) {
        // Mientras no sea la raíz y el valor sea menor que el de su padre
        while (i > 0 && heap.get(i) < heap.get(parent(i))) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    // ---------------------------------------------------------------
    // downHeapify — baja un nodo hasta su posición correcta
    // ---------------------------------------------------------------

    /**
     * Restaura la propiedad del montículo bajando el nodo en la posición i
     * hacia las hojas mientras sea mayor que alguno de sus hijos.
     */
    private void downHeapify(int i) {
        int size = heap.size();

        while (true) {
            int smallest = i;
            int left     = leftChild(i);
            int right    = rightChild(i);

            if (left < size && heap.get(left) < heap.get(smallest)) {
                smallest = left;
            }

            if (right < size && heap.get(right) < heap.get(smallest)) {
                smallest = right;
            }

            if (smallest == i) {
                break;
            }

            swap(i, smallest);
            i = smallest;
        }
    }

    // ---------------------------------------------------------------
    // insertar — agrega un nuevo valor al montículo
    // ---------------------------------------------------------------

    /**
     * Inserta un nuevo valor en el montículo.
     */
    public void insertar(int valor) {
        heap.add(valor);                         // 1. Agregar al final
        upHeapify(heap.size() - 1);              // 2. Subir a su lugar
        System.out.println("  ✔ Valor " + valor + " insertado correctamente.");
    }

    // ---------------------------------------------------------------
    // eliminarMin — extrae y retorna la raíz (valor mínimo)
    // ---------------------------------------------------------------

    public int eliminarMin() {
        if (heap.isEmpty()) {
            System.out.println("  ✖ El montículo está vacío. No hay nada que eliminar.");
            return Integer.MIN_VALUE;
        }

        int min  = heap.get(0);                  // 1. Guardar la raíz
        int last = heap.remove(heap.size() - 1); // 2. Extraer el último elemento

        if (!heap.isEmpty()) {
            heap.set(0, last);                   // 3. Poner el último en la raíz
            downHeapify(0);                      // 4. Bajar a su lugar
        }

        System.out.println("  ✔ Mínimo eliminado: " + min);
        return min;
    }

    // ---------------------------------------------------------------
    // peek — consulta el mínimo sin eliminarlo
    // ---------------------------------------------------------------

    public int peek() {
        if (heap.isEmpty()) {
            System.out.println("  ✖ El montículo está vacío.");
            return Integer.MIN_VALUE;
        }
        System.out.println("  ✔ Valor mínimo (raíz): " + heap.get(0));
        return heap.get(0);
    }

    // ---------------------------------------------------------------
    // heapify — convierte un arreglo desordenado en un montículo válido
    // ---------------------------------------------------------------

    public void heapify(ArrayList<Integer> arreglo) {
        if (arreglo == null || arreglo.isEmpty()) {
            System.out.println("  ✖ El arreglo proporcionado está vacío.");
            return;
        }

        // Reemplazar el contenido actual por el arreglo recibido
        heap = new ArrayList<>(arreglo);

        // Índice del último nodo interno (que tiene al menos un hijo)
        int lastInternal = (heap.size() / 2) - 1;

        // Aplicar downHeapify desde el último nodo interno hacia la raíz
        for (int i = lastInternal; i >= 0; i--) {
            downHeapify(i);
        }

        System.out.println("  ✔ Arreglo convertido a montículo mínimo correctamente.");
    }

    // ---------------------------------------------------------------
    // Métodos de utilidad
    // ---------------------------------------------------------------

    public boolean estaVacio() {
        return heap.isEmpty();
    }

    public int tamanio() {
        return heap.size();
    }

    public void mostrarHeap() {
        if (heap.isEmpty()) {
            System.out.println("  (montículo vacío)");
            return;
        }
        System.out.print("  Heap: [ ");
        for (int i = 0; i < heap.size(); i++) {
            System.out.print(heap.get(i));
            if (i < heap.size() - 1) System.out.print(", ");
        }
        System.out.println(" ]");
        System.out.println("  Tamaño: " + heap.size() + " elemento(s).");
    }

    /**
     * Imprime el árbol del montículo de forma visual por niveles.
     */
    public void mostrarArbol() {
        if (heap.isEmpty()) {
            System.out.println("  (montículo vacío)");
            return;
        }

        System.out.println("  Representación por niveles:");
        int nivel = 0;
        int inicio = 0;
        int nodosEnNivel = 1;

        while (inicio < heap.size()) {
            System.out.print("  Nivel " + nivel + ": ");
            for (int i = inicio; i < Math.min(inicio + nodosEnNivel, heap.size()); i++) {
                System.out.print(heap.get(i) + "  ");
            }
            System.out.println();
            inicio += nodosEnNivel;
            nodosEnNivel *= 2;
            nivel++;
        }
    }
}
