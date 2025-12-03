package algoritmo;

import grafo.GrafoRedUniversidad;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ALGORITMO DE DIJKSTRA - Camino mas corto en grafo ponderado.
 * 
 * Complejidad: O(V^2) con matriz de adyacencia
 * 
 * PASOS:
 * 1. distancia[origen]=0, resto=INF
 * 2. Repetir V veces:
 *    a) Seleccionar vertice no visitado con menor distancia
 *    b) Marcar como visitado
 *    c) Relajar aristas: si dist[actual]+peso < dist[vecino] -> actualizar
 */
public class AlgoritmoDijkstra {
    
    private static final Logger LOGGER = Logger.getLogger(AlgoritmoDijkstra.class.getName());
    
    private final GrafoRedUniversidad grafo;
    private double[] dist;
    private int[] pred;
    private int origen;

    public AlgoritmoDijkstra(GrafoRedUniversidad grafo) {
        this.grafo = grafo;
    }

    public void ejecutar(int origen) {
        this.origen = origen;
        int n = grafo.getN();
        dist = new double[n];
        pred = new int[n];
        boolean[] visitado = new boolean[n];

        inicializarDistancias(n, origen);
        procesarVertices(n, visitado);
    }
    
    private void inicializarDistancias(int n, int origen) {
        for (int i = 0; i < n; i++) {
            dist[i] = GrafoRedUniversidad.INF;
            pred[i] = -1;
        }
        dist[origen] = 0;
    }
    
    private void procesarVertices(int n, boolean[] visitado) {
        for (int count = 0; count < n; count++) {
            int u = encontrarMinimo(n, visitado);
            if (u == -1) {
                break;
            }
            visitado[u] = true;
            relajarAristas(n, u, visitado);
        }
    }
    
    private int encontrarMinimo(int n, boolean[] visitado) {
        int u = -1;
        double minDist = GrafoRedUniversidad.INF;
        for (int i = 0; i < n; i++) {
            if (!visitado[i] && dist[i] < minDist) {
                minDist = dist[i];
                u = i;
            }
        }
        return u;
    }
    
    private void relajarAristas(int n, int u, boolean[] visitado) {
        for (int v = 0; v < n; v++) {
            double peso = grafo.getLatencia(u, v);
            if (!visitado[v] && peso != GrafoRedUniversidad.INF) {
                double nuevaDist = dist[u] + peso;
                if (nuevaDist < dist[v]) {
                    dist[v] = nuevaDist;
                    pred[v] = u;
                }
            }
        }
    }

    public List<Integer> getCamino(int destino) {
        List<Integer> camino = new ArrayList<>();
        if (dist[destino] == GrafoRedUniversidad.INF) {
            return camino;
        }
        
        for (int v = destino; v != -1; v = pred[v]) {
            camino.add(v);
        }
        Collections.reverse(camino);
        return camino;
    }

    public void imprimirRuta(int destino) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n=== RUTA OPTIMA ===\n");
        sb.append("Origen:  ").append(grafo.getNombre(origen)).append("\n");
        sb.append("Destino: ").append(grafo.getNombre(destino)).append("\n");
        
        if (dist[destino] == GrafoRedUniversidad.INF) {
            sb.append("No existe ruta!");
            if (LOGGER.isLoggable(Level.INFO)) {
                LOGGER.info(sb.toString());
            }
            return;
        }
        
        sb.append(String.format("Latencia total: %.1f ms%n%n", dist[destino]));
        
        List<Integer> camino = getCamino(destino);
        sb.append("Recorrido:\n");
        for (int i = 0; i < camino.size(); i++) {
            sb.append("  ").append(grafo.getNombre(camino.get(i)));
            if (i < camino.size() - 1) {
                double lat = grafo.getLatencia(camino.get(i), camino.get(i+1));
                sb.append(String.format(" --[%.1f ms]--> ", lat));
            }
            sb.append("\n");
        }
        sb.append("\nSaltos: ").append(camino.size() - 1);
        if (LOGGER.isLoggable(Level.INFO)) {
            LOGGER.info(sb.toString());
        }
    }

    public void imprimirDistancias() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n=== DISTANCIAS MINIMAS desde ").append(grafo.getNombre(origen)).append(" ===\n");
        for (int i = 0; i < dist.length; i++) {
            String d = (dist[i] == GrafoRedUniversidad.INF) ? "INF" : String.format("%.1f", dist[i]);
            sb.append(String.format("  -> %s : %s ms%n", grafo.getNombre(i), d));
        }
        if (LOGGER.isLoggable(Level.INFO)) {
            LOGGER.info(sb.toString());
        }
    }

    public double getDistancia(int destino) { return dist[destino]; }
}
