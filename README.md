# 🎓 Red de Telecomunicaciones Universitaria - Algoritmo de Dijkstra

## 📋 Descripción del Proyecto

Este proyecto implementa el **Algoritmo de Dijkstra** aplicado a un caso práctico real: una **red de fibra óptica** que conecta 10 edificios de un campus universitario. Los pesos de las aristas representan la **latencia en milisegundos** entre edificios.

## 🏛️ Arquitectura del Proyecto

```
Tele_Univ/
│
├── src/
│   ├── modelo/
│   │   ├── Edificio.java       # Clase que representa un vértice (edificio)
│   │   └── Conexion.java       # Clase que representa una arista (conexión)
│   │
│   ├── grafo/
│   │   └── GrafoRedUniversidad.java  # Estructura del grafo con matriz de adyacencia
│   │
│   ├── algoritmo/
│   │   ├── AlgoritmoDijkstra.java    # Implementación del algoritmo
│   │   └── ResultadoDijkstra.java    # Encapsula el resultado del algoritmo
│   │
│   └── aplicacion/
│       └── RedUniversidadApp.java    # Aplicación principal con menú interactivo
│
└── README.md
```

## 🗺️ Topología de la Red

```
                    [0] Rectoría
                    /    |    \
                 2.5   1.8    3.2
                 /      |       \
     [1] Biblioteca--[2] Ing.--[3] Ciencias
            |    \     |    /       |
           1.5   2.0  1.2  2.8     1.9
            |      \   |  /         |
     [4] Medicina--[5] Data Center--[6] Derecho
            |           |    \        |
           2.2         0.8   1.5     2.1
            |           |      \      |
     [7] Deportes--[8] Artes--[9] Admin
                1.7      2.3
```

## 📚 Edificios del Campus

| ID | Edificio | Descripción |
|----|----------|-------------|
| 0 | Rectoría | Edificio administrativo principal |
| 1 | Biblioteca Central | Recursos académicos y estudio |
| 2 | Facultad de Ingeniería | Carreras de ingeniería |
| 3 | Facultad de Ciencias | Ciencias básicas y laboratorios |
| 4 | Facultad de Medicina | Ciencias de la salud |
| 5 | Data Center | Centro de datos y servidores (Hub central) |
| 6 | Facultad de Derecho | Ciencias jurídicas |
| 7 | Complejo Deportivo | Instalaciones deportivas |
| 8 | Facultad de Artes | Bellas artes y humanidades |
| 9 | Edificio Administrativo | Servicios administrativos |

## 🔬 El Algoritmo de Dijkstra

### ¿Qué es?
El algoritmo de Dijkstra encuentra el **camino más corto** desde un vértice origen hacia todos los demás vértices en un grafo con pesos no negativos.

### Pasos del Algoritmo

1. **Inicialización:**
   - `distancia[origen] = 0`
   - `distancia[resto] = ∞`
   - Todos los vértices están "no visitados"

2. **Iteración:**
   - Seleccionar el vértice **no visitado** con menor distancia
   - Marcarlo como **visitado**
   - Para cada vecino no visitado:
     - Si `distancia_actual + peso_arista < distancia[vecino]`:
       - Actualizar `distancia[vecino]`
       - Guardar predecesor

3. **Resultado:**
   - Array de distancias mínimas
   - Array de predecesores para reconstruir caminos

### Complejidad
- **Tiempo:** O(V²) con matriz de adyacencia
- **Espacio:** O(V)

## 🚀 Cómo Compilar y Ejecutar

### Opción 1: Desde línea de comandos

```bash
# Navegar al directorio del proyecto
cd Tele_Univ

# Compilar todos los archivos
javac -d bin src/modelo/*.java src/grafo/*.java src/algoritmo/*.java src/aplicacion/*.java

# Ejecutar la aplicación
java -cp bin aplicacion.RedUniversidadApp
```

### Opción 2: Crear directorio bin primero

```bash
# Crear directorio de salida
mkdir bin

# Compilar
javac -d bin src/modelo/*.java src/grafo/*.java src/algoritmo/*.java src/aplicacion/*.java

# Ejecutar
java -cp bin aplicacion.RedUniversidadApp
```

## 🎮 Funcionalidades

1. **Ver edificios del campus** - Lista todos los edificios con sus descripciones
2. **Ver conexiones de fibra óptica** - Muestra todas las conexiones y sus latencias
3. **Ver matriz de adyacencia** - Visualiza la estructura del grafo
4. **Encontrar ruta óptima** - Calcula el camino con menor latencia entre dos edificios
5. **Ver todas las distancias** - Muestra la tabla de distancias mínimas desde un origen
6. **Modo educativo** - Ejecuta Dijkstra mostrando cada paso del algoritmo

## 📊 Ejemplo de Uso

```
╔══════════════════════════════════════════════════════════════╗
║                  RUTA ÓPTIMA ENCONTRADA                      ║
╚══════════════════════════════════════════════════════════════╝

  📍 Origen:  Biblioteca Central
  🎯 Destino: Edificio Administrativo
  ⏱️  Latencia total: 4.3 ms

  RECORRIDO:
  ──────────────────────────────────────────────────
  🏁 [INICIO] Biblioteca Central
       │ (2.0 ms)
       ▼
  📌 [1]      Data Center
       │ (1.5 ms)
       ▼
  🏆 [FIN]    Edificio Administrativo
  ──────────────────────────────────────────────────

  ✅ Número de saltos: 2
```

## 💡 Conceptos Clave

- **Grafo:** Estructura que modela relaciones entre objetos
- **Vértice/Nodo:** Cada edificio del campus
- **Arista/Edge:** Conexión de fibra óptica entre edificios
- **Peso:** Latencia en milisegundos
- **Matriz de Adyacencia:** Representación del grafo en forma matricial
- **Algoritmo Voraz (Greedy):** Selecciona siempre la mejor opción local

## 👨‍💻 Autor

Universidad - Estructuras de Datos
Proyecto educativo sobre Grafos y Algoritmo de Dijkstra

## 📝 Licencia

Este proyecto es de uso educativo y libre distribución.
