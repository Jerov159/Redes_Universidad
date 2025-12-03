## 🎓 Red de Telecomunicaciones Universitaria – Algoritmo de Dijkstra

## 📋 Descripción del proyecto

Este proyecto implementa el **Algoritmo de Dijkstra** aplicado a una **red de fibra óptica** que conecta 10 edificios de un campus universitario.  
Cada arista del grafo representa una conexión de fibra con un **peso en milisegundos (latencia)**, y el objetivo es encontrar rutas con la menor latencia posible entre dos edificios.

## 🏛️ Arquitectura del proyecto

Estructura real del código en este repositorio (`Dijkstra`):

```text
Dijkstra/
│
├── src/
│   ├── modelo/
│   │   └── Edificio.java            # Representa un vértice (edificio del campus)
│   │
│   ├── grafo/
│   │   └── GrafoRedUniversidad.java # Grafo ponderado no dirigido (matriz de adyacencia)
│   │
│   ├── algoritmo/
│   │   └── AlgoritmoDijkstra.java   # Implementación del algoritmo de Dijkstra
│   │
│   └── aplicacion/
│       └── RedUniversidadApp.java   # Aplicación de consola con menú interactivo
│
└── README.md
```

## 📚 Edificios del campus (según el código)

En el grafo se modelan 10 edificios, identificados como B1…B10, con los nombres definidos en `RedUniversidadApp`:

| ID | Etiqueta en consola | Nombre lógico           |
|----|---------------------|-------------------------|
| 0  | B1 - Rectoria       | Rectoría                |
| 1  | B2 - Ingenieria     | Ingeniería              |
| 2  | B3 - Biblioteca     | Biblioteca              |
| 3  | B4 - Laboratorios   | Laboratorios            |
| 4  | B5 - Aulas Generales| Aulas Generales         |
| 5  | B6 - Data Center    | Data Center             |
| 6  | B7 - Medicina       | Medicina                |
| 7  | B8 - Deportes       | Deportes                |
| 8  | B9 - Ciencias       | Ciencias                |
| 9  | B10 - Administrativo| Administrativo          |

## 🔗 Conexiones de fibra (latencias en ms)

Las conexiones definidas en `RedUniversidadApp.inicializarRed()` son:

- B1 ↔ B10: 3.1 ms  
- B1 ↔ B5: 4.0 ms  
- B1 ↔ B2: 2.1 ms  
- B2 ↔ B5: 3.6 ms  
- B2 ↔ B3: 1.8 ms  
- B3 ↔ B4: 2.5 ms  
- B3 ↔ B7: 4.2 ms  
- B4 ↔ B5: 3.0 ms  
- B4 ↔ B8: 3.8 ms  
- B5 ↔ B6: 2.2 ms  
- B5 ↔ B9: 5.0 ms  
- B6 ↔ B10: 4.5 ms  
- B6 ↔ B7: 2.9 ms  
- B7 ↔ B8: 2.7 ms  
- B7 ↔ B9: 3.0 ms  
- B8 ↔ B9: 3.5 ms  
- B9 ↔ B10: 2.4 ms  

## 🔬 El algoritmo de Dijkstra

El algoritmo de Dijkstra encuentra el **camino más corto** desde un vértice origen hacia todos los demás vértices en un grafo con pesos no negativos.  
En este proyecto se implementa con **matriz de adyacencia**, lo que da una complejidad temporal de \\(O(V^2)\\).

**Pasos principales:**

1. **Inicialización**
   - `distancia[origen] = 0`
   - `distancia[resto] = ∞`
   - Todos los vértices están no visitados.
2. **Iteración**
   - Seleccionar el vértice no visitado con menor distancia acumulada.
   - Marcarlo como visitado.
   - Relajar sus aristas: si `distancia[u] + peso(u,v) < distancia[v]`, se actualiza `distancia[v]` y el predecesor.
3. **Resultado**
   - Arreglo de distancias mínimas `dist`.
   - Arreglo de predecesores `pred` para reconstruir caminos.

**Complejidad:**
- **Tiempo:** \\(O(V^2)\\)  
- **Espacio:** \\(O(V)\\) para distancias y predecesores.

## 🚀 Cómo compilar y ejecutar

### 1. Desde línea de comandos (Java instalado en el PATH)

```bash
# Ubicarse en el directorio del proyecto
cd Dijkstra

# (Opcional) Crear el directorio de salida
mkdir bin

# Compilar todas las clases
javac -d bin src/modelo/*.java src/grafo/*.java src/algoritmo/*.java src/aplicacion/*.java

# Ejecutar la aplicación de consola
java -cp bin aplicacion.RedUniversidadApp
```

En Windows PowerShell puedes usar los mismos comandos (ajustando la ruta según corresponda).

### 2. Desde un IDE (IntelliJ IDEA, Eclipse, VS Code, etc.)

- **Importar** el proyecto como proyecto Java existente.  
- Asegurarse de que la carpeta `src` esté marcada como *Source Root*.  
- Configurar la clase principal (`main`) como `aplicacion.RedUniversidadApp`.  
- Ejecutar desde el IDE.

## 🎮 Funcionalidades reales del menú

La clase `RedUniversidadApp` ofrece un menú de consola con estas opciones:

1. **Ver edificios**: muestra la lista B1…B10 con sus nombres.  
2. **Ver matriz de adyacencia**: imprime la matriz de latencias entre edificios.  
3. **Buscar ruta óptima (Dijkstra)**: pide origen y destino (1–10) y muestra el camino con menor latencia y su costo total.  
4. **Distancias desde un edificio**: ejecuta Dijkstra desde un origen y lista la distancia mínima a todos los demás.  
5. **Salir**: termina la aplicación.

## 💡 Conceptos clave

- **Grafo:** estructura que modela relaciones entre objetos (en este caso, edificios del campus).  
- **Vértice / nodo:** cada edificio (`Edificio`).  
- **Arista:** conexión de fibra óptica entre dos edificios.  
- **Peso:** latencia en milisegundos.  
- **Matriz de adyacencia:** representación matricial de las conexiones y sus pesos.  
- **Algoritmo voraz (greedy):** estrategia que elige siempre la mejor opción local en cada paso.

## 👨‍💻 Autor

Universidad – Estructuras de Datos.  
Proyecto educativo sobre grafos y algoritmo de Dijkstra aplicado a redes de telecomunicaciones.

## 📝 Licencia

Proyecto de uso educativo y de libre distribución.
