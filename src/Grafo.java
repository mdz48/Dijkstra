import java.util.InputMismatchException;
import java.util.Scanner;

class Grafo {
    Vertice[] listaVertices;
    private int[][] matrizAdyacencia;
    private int cantidadVertices;

    public Grafo(int cantidadVertices) {
        listaVertices = new Vertice[cantidadVertices];
        this.cantidadVertices = cantidadVertices;
        matrizAdyacencia = new int[cantidadVertices][cantidadVertices];
        for (int i = 0; i < cantidadVertices; i++) {
            for (int j = 0; j < cantidadVertices; j++) {
                matrizAdyacencia[i][j] = 0;
            }
        }
    }

    public Vertice buscarCentroMasCercano(Grafo g,int origen) {
        CaminoMinimo caminoMinimo = new CaminoMinimo(g, origen);
        caminoMinimo.Dijkstra(g, origen);
        int minDistancia = Integer.MAX_VALUE;
        Vertice centroCercano = null;
        for (int i = 0; i < listaVertices.length; i++) {
            if (i != origen && listaVertices[i].getStock() > 0 && caminoMinimo.D[i] < minDistancia) {
                minDistancia = caminoMinimo.D[i];
                centroCercano = listaVertices[i];
            }
        }
        return centroCercano;
    }

    public void agregarVertice(String etiqueta, int numero, int stock) {
        Vertice nuevoVertice = new Vertice(etiqueta, stock);
        nuevoVertice.asigVert(numero);
        listaVertices[numero] = nuevoVertice;
    }

    public void agregarAristas() {
        Scanner entrada = new Scanner(System.in);
        int origen, destino, peso, opcion = 0;
        do {
            for (int i = 0; i < listaVertices.length; i++) {
                System.out.println((i + 1) + ") " + listaVertices[i].getNombre());
            }
            try {
                System.out.println("Ingresa el vértice origen:");
                origen = entrada.nextInt();
                System.out.println("Ingresa el vértice destino:");
                destino = entrada.nextInt();
                if (matrizAdyacencia[origen - 1][destino - 1] == 0) {
                    System.out.println("Ingresa la distancia entre los centros:");
                    peso = entrada.nextInt();
                    matrizAdyacencia[origen - 1][destino - 1] = peso;
                    System.out.println("Agregar más aristas 1) SI \t2) NO");
                    opcion = entrada.nextInt();
                } else {
                    System.out.println("Ya existe una arista entre estos vértices.");
                    opcion = 1;
                }
            } catch (InputMismatchException e) {
                System.out.println("Intenta de nuevo:");
                entrada.next();  // Clear invalid input
                opcion = 1;
            }
        } while (opcion == 1);
    }

    public void imprimir() {
        System.out.println("Matriz de adyacencia:");
        for (int i = 0; i < matrizAdyacencia.length; i++) {
            for (int j = 0; j < matrizAdyacencia[i].length; j++) {
                if (matrizAdyacencia[i][j] == Integer.MAX_VALUE) {
                    System.out.print("∞\t");
                } else {
                    System.out.print(matrizAdyacencia[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }

    public int getCantidadVertices() {
        return cantidadVertices;
    }

    public int[][] getMatrizAdyacencia() {
        return matrizAdyacencia;
    }

    public Vertice[] getListaVertices() {
        return listaVertices;
    }

    public int getStock(int vertice) {
        return listaVertices[vertice].getStock();
    }
}
