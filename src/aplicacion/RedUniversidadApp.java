package aplicacion;

import algoritmo.AlgoritmoDijkstra;
import grafo.GrafoRedUniversidad;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Edificio;

/**
 * RED DE FIBRA OPTICA - CAMPUS UNIVERSITARIO
 * 10 edificios conectados. Pesos = latencia en ms.
 */
public class RedUniversidadApp {
    
    private static final Logger LOGGER = Logger.getLogger(RedUniversidadApp.class.getName());
    
    private GrafoRedUniversidad red;
    private final AlgoritmoDijkstra dijkstra;
    private final Scanner sc = new Scanner(System.in);

    public RedUniversidadApp() {
        inicializarRed();
        dijkstra = new AlgoritmoDijkstra(red);
    }

    private void inicializarRed() {
        red = new GrafoRedUniversidad(10);
        
        // Edificios B1-B10 (indices 0-9)
        String[] nombres = {
            "Rectoria", "Ingenieria", "Biblioteca", "Laboratorios",
            "Aulas Generales", "Data Center", "Medicina", "Deportes",
            "Ciencias", "Administrativo"
        };
        for (int i = 0; i < 10; i++) {
            red.agregarEdificio(new Edificio(i, nombres[i]));
        }

        // ═══════════════════════════════════════════════════════════════
        // CONEXIONES DE FIBRA OPTICA (según diagrama real)
        // Índices: B1=0, B2=1, B3=2, B4=3, B5=4, B6=5, B7=6, B8=7, B9=8, B10=9
        // ═══════════════════════════════════════════════════════════════
        
        // Conexiones desde B1 (Rectoria)
        red.agregarConexion(0, 9, 3.1);   // B1 ↔ B10 (3.1 ms)
        red.agregarConexion(0, 4, 4.0);   // B1 ↔ B5  (4.0 ms)
        red.agregarConexion(0, 1, 2.1);   // B1 ↔ B2  (2.1 ms)
        
        // Conexiones desde B2 (Ingenieria)
        red.agregarConexion(1, 4, 3.6);   // B2 ↔ B5  (3.6 ms)
        red.agregarConexion(1, 2, 1.8);   // B2 ↔ B3  (1.8 ms)
        
        // Conexiones desde B3 (Biblioteca)
        red.agregarConexion(2, 3, 2.5);   // B3 ↔ B4  (2.5 ms)
        red.agregarConexion(2, 6, 4.2);   // B3 ↔ B7  (4.2 ms)
        
        // Conexiones desde B4 (Laboratorios)
        red.agregarConexion(3, 4, 3.0);   // B4 ↔ B5  (3.0 ms)
        red.agregarConexion(3, 7, 3.8);   // B4 ↔ B8  (3.8 ms)
        
        // Conexiones desde B5 (Aulas Generales)
        red.agregarConexion(4, 5, 2.2);   // B5 ↔ B6  (2.2 ms)
        red.agregarConexion(4, 8, 5.0);   // B5 ↔ B9  (5.0 ms)
        
        // Conexiones desde B6 (Data Center)
        red.agregarConexion(5, 9, 4.5);   // B6 ↔ B10 (4.5 ms)
        red.agregarConexion(5, 6, 2.9);   // B6 ↔ B7  (2.9 ms)
        
        // Conexiones desde B7 (Medicina)
        red.agregarConexion(6, 7, 2.7);   // B7 ↔ B8  (2.7 ms)
        red.agregarConexion(6, 8, 3.0);   // B7 ↔ B9  (3.0 ms)
        
        // Conexiones desde B8 (Deportes)
        red.agregarConexion(7, 8, 3.5);   // B8 ↔ B9  (3.5 ms)
        
        // Conexiones desde B9 (Ciencias)
        red.agregarConexion(8, 9, 2.4);   // B9 ↔ B10 (2.4 ms)
    }

    public void ejecutar() {
        int op;
        do {
            mostrarMenu();
            op = leerInt();
            procesarOpcion(op);
        } while (op != 5);
    }
    
    private void mostrarMenu() {
        LOGGER.info("""
            
            ========================================
              RED FIBRA OPTICA - UNIVERSIDAD
            ========================================
              1. Ver edificios
              2. Ver matriz de adyacencia
              3. Buscar ruta optima (Dijkstra)
              4. Distancias desde un edificio
              5. Salir
            ========================================
            Opcion:\s""");
    }
    
    private void procesarOpcion(int op) {
        switch (op) {
            case 1 -> mostrarEdificios();
            case 2 -> red.imprimirMatriz();
            case 3 -> buscarRuta();
            case 4 -> verDistancias();
            case 5 -> LOGGER.info("Adios!");
            default -> LOGGER.info("Opcion invalida");
        }
    }

    private void mostrarEdificios() {
        StringBuilder sb = new StringBuilder("\n=== EDIFICIOS DEL CAMPUS ===\n");
        for (int i = 0; i < 10; i++) {
            sb.append("  ").append(red.getNombre(i)).append("\n");
        }
        if (LOGGER.isLoggable(Level.INFO)) {
            LOGGER.info(sb.toString());
        }
    }

    private void buscarRuta() {
        mostrarEdificios();
        LOGGER.info("Origen (1-10): ");
        int origen = leerInt() - 1;
        LOGGER.info("Destino (1-10): ");
        int destino = leerInt() - 1;
        
        if (origen < 0 || origen > 9 || destino < 0 || destino > 9) {
            LOGGER.info("IDs invalidos!");
            return;
        }
        
        dijkstra.ejecutar(origen);
        dijkstra.imprimirRuta(destino);
    }

    private void verDistancias() {
        mostrarEdificios();
        LOGGER.info("Edificio origen (1-10): ");
        int origen = leerInt() - 1;
        
        if (origen < 0 || origen > 9) {
            LOGGER.info("ID invalido!");
            return;
        }
        
        dijkstra.ejecutar(origen);
        dijkstra.imprimirDistancias();
    }

    private int leerInt() {
        try { 
            return Integer.parseInt(sc.nextLine().trim()); 
        } catch (NumberFormatException e) { 
            return -1; 
        }
    }

    public static void main(String[] args) {
        LOGGER.info("\n*** ALGORITMO DE DIJKSTRA ***\nRed de Telecomunicaciones Universitaria\n");
        new RedUniversidadApp().ejecutar();
    }
}
