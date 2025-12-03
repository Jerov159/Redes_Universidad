package grafo;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Edificio;

/**
 * Grafo ponderado no dirigido - Red de fibra optica universitaria.
 * Usa matriz de adyacencia para almacenar latencias (ms).
 */
public class GrafoRedUniversidad {
    
    private static final Logger LOGGER = Logger.getLogger(GrafoRedUniversidad.class.getName());
    public static final double INF = Double.MAX_VALUE;
    
    private final int n;
    private final double[][] matriz;
    private final List<Edificio> edificios;

    public GrafoRedUniversidad(int n) {
        this.n = n;
        this.matriz = new double[n][n];
        this.edificios = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matriz[i][j] = (i == j) ? 0 : INF;
            }
        }
    }

    public void agregarEdificio(Edificio e) { 
        edificios.add(e); 
    }

    public void agregarConexion(int u, int v, double latencia) {
        matriz[u][v] = latencia;
        matriz[v][u] = latencia;
    }

    public double getLatencia(int u, int v) { return matriz[u][v]; }
    public int getN() { return n; }
    
    public String getNombre(int id) { 
        return (id < edificios.size()) ? edificios.get(id).toString() : "B" + (id+1); 
    }

    public void imprimirMatriz() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n=== MATRIZ DE ADYACENCIA (Latencias ms) ===\n");
        sb.append("     ");
        for (int i = 0; i < n; i++) {
            sb.append(String.format(" B%-4d", i+1));
        }
        sb.append("\n");
        
        for (int i = 0; i < n; i++) {
            sb.append(String.format("B%-3d|", i+1));
            for (int j = 0; j < n; j++) {
                if (matriz[i][j] == INF) {
                    sb.append("  -   ");
                } else {
                    sb.append(String.format("%5.1f ", matriz[i][j]));
                }
            }
            sb.append("\n");
        }
        if (LOGGER.isLoggable(Level.INFO)) {
            LOGGER.info(sb.toString());
        }
    }
}
