# Tercera-plactica-de-implementacion-de-datos
1. ¿QUÉ ES EL ALGORITMO DE DIJKSTRA?
El algoritmo de Dijkstra es un algoritm clásico de búsqueda de caminos
mínimos en grafos ponderados (con pesos en sus aristas). Dado un nodo de
origen, encuentra la ruta de menor costo hacia todos los demás nodos del
grafo, siempre y cuando los pesos de las aristas sean no negativos.

Su aplicación es amplia: sistemas de navegación GPS, enrutamiento de
paquetes en redes de computadoras (protocolo OSPF), videojuegos, y
planificación logística, entre muchos otros.


2. FUNCIONAMIENTO BÁSICO DE DIJKSTRA
El algoritmo mantiene un conjunto de nodos "ya visitados" con su
distancia mínima definitiva desde el origen, y un conjunto de nodos
"por visitar" con una estimación provisional de su distancia mínima.

Pasos generales:
  a) Asignar distancia 0 al nodo de origen y distancia infinita a todos
     los demás.
  b) Seleccionar el nodo no visitado con la menor distancia provisional.
  c) Actualizar las distancias de sus vecinos si se encuentra una ruta
     más corta a través del nodo actual (proceso llamado "relajación").
  d) Marcar el nodo actual como visitado.
  e) Repetir desde el paso (b) hasta haber visitado todos los nodos
     o hasta que la distancia mínima sea infinita (nodo inalcanzable).

El paso (b) seleccionar el nodo con la menor distancia provisional—
es el más crítico en términos de rendimiento.


3. ¿DÓNDE ENTRA EL MIN-HEAP?
La operación de "extraer el mínimo" en el paso (b) es precisamente lo
que hace el método eliminarMin() de un montículo mínimo.

Sin una estructura de datos eficiente, buscar el nodo de menor
distancia requeriría recorrer todos los nodos no visitados en cada
iteración, resultando en una complejidad de O(V²), donde V es el número
de vértices del grafo.

Al utilizar un min-heap como "cola de prioridad", la operación de
extracción del mínimo se realiza en tiempo O(log V), ya que el
montículo mantiene siempre el nodo de menor distancia en su raíz.
La inserción y actualización de distancias también se realiza en
O(log V) gracias a upHeapify().

Esto reduce la complejidad total del algoritmo de Dijkstra de
  O(V²)      (sin min-heap, con lista simple)
a
  O((V + E) log V)   (con min-heap, donde E = número de aristas)

Esta mejora es enorme en grafos dispersos (con muchas aristas), que
son los más comunes en aplicaciones reales.


4. INTEGRACIÓN CONCRETA EN DIJKSTRA
A continuación se describe cómo se usa el min-heap dentro del
algoritmo:

  - Cada elemento del montículo es un par (distancia, nodo), donde
    la distancia es la llave de ordenamiento.

  - Se inserta el nodo de origen con distancia 0: insertar(0, origen).

  - En cada iteración se llama a eliminarMin() para obtener el nodo
    no visitado más cercano al origen.

  - Cuando se relaja una arista y se encuentra una distancia menor
    a un vecino, se inserta ese vecino en el montículo con su nueva
    distancia actualizada.

  - El proceso continúa hasta que el montículo quede vacío.

  Ejemplo simplificado en pseudocódigo:
  heap.insertar(0, origen)
  dist[origen] = 0

  mientras heap no esté vacío:
      (d, u) = heap.eliminarMin()
      si u ya fue visitado: continuar
      marcar u como visitado

      para cada vecino v de u con peso w:
          si dist[u] + w < dist[v]:
              dist[v] = dist[u] + w
              heap.insertar(dist[v], v)


5. RESUMEN
El min-heap es fundamental para que Dijkstra sea eficiente. Sin él, el
algoritmo sería demasiado lento para grafos grandes. Gracias al
montículo mínimo, la operación de encontrar el siguiente nodo a procesar
es casi instantánea (O(log V)), lo que hace viable el uso de Dijkstra en
sistemas reales con millones de nodos, como los mapas de navegación o
las redes de telecomunicaciones.

En síntesis: el min-heap actúa como la "cola de prioridad" del algoritmo
de Dijkstra, garantizando que siempre se procese primero el nodo más
cercano al origen, lo cual es la clave de la correctitud y eficiencia
del algoritmo.
