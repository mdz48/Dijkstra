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
            listaVertices[i] = new Vertice("", 0);
            for (int j = 0; j < cantidadVertices; j++) {
                if (i == j) {
                    matrizAdyacencia[i][j] = 0;
                } else {
                    matrizAdyacencia[i][j] = Integer.MAX_VALUE;
                }
            }
        }
    }

    public Vertice buscarCentroMasCercano(Grafo g, int origen) {
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

    public boolean agregarVertice(String etiqueta, int numero, int stock) {
        for (int i = 0; i < listaVertices.length; i++) {
            if (etiqueta.equals(listaVertices[i].getNombre())) {
                return false;
            }
        }
        Vertice nuevoVertice = new Vertice(etiqueta, stock);
        nuevoVertice.asigVert(numero);
        listaVertices[numero] = nuevoVertice;
        return true;

    }

    public void agregarAristas() {
        Scanner entrada = new Scanner(System.in);
        int origen, destino, peso, opcion = 0;
        boolean valido = false;
        do {
            for (int i = 0; i < listaVertices.length; i++) {
                System.out.println((i + 1) + ") " + listaVertices[i].getNombre());
            }
            try {
                System.out.println("Ingresa el centro de origen:");
                origen = entrada.nextInt() - 1;
                System.out.println("Ingresa el centro de destino:");
                destino = entrada.nextInt() - 1;
                if (origen == destino){
                    System.out.println("Porfavor, escoja otro centro de distribución");
                    opcion = 1;
                } else if (matrizAdyacencia[origen][destino] == Integer.MAX_VALUE ) {
                    System.out.println("Ingresa la distancia entre los centros:");
                    peso = entrada.nextInt();
                    while (peso < 1) {
                        System.out.println("Ingrese una distancia positiva");
                        peso = entrada.nextInt();
                    }
                    matrizAdyacencia[origen][destino] = peso;
                    System.out.println("Agregar más aristas 1) SI \t2) NO");
                    while (!valido) {
                        try {
                            opcion = entrada.nextInt();
                            if (opcion == 1 || opcion == 2) {
                                valido = true;
                            } else {
                                System.out.println("Ingrese una opción válida (1 o 2)");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Ingrese una opción válida (1 o 2)");
                            entrada.next();
                        }
                    }
                    valido = false;
                } else {
                    System.out.println("Ya existe una arista entre estos vértices.");
                    opcion = 1;
                }
            } catch (Exception e) {
                System.out.println("Intenta de nuevo:");
                entrada.next();
                opcion = 1;
            }
        } while (opcion == 1);
    }

    public void imprimir() {
        System.out.println("Matriz de adyacencia:");
        for (int i = 0; i < matrizAdyacencia.length; i++) {
            for (int j = 0; j < matrizAdyacencia[i].length; j++) {
                if (matrizAdyacencia[i][j] == Integer.MAX_VALUE) {
                    System.out.print("0\t");
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