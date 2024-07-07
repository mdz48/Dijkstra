class CaminoMinimo {
    int[][] pesos;
    int[] ultimo; // array de predecesores
    int[] D; // array de distancias mínimas
    int s, n; // vértice origen y número de vértices
    boolean[] F;

    public CaminoMinimo(Grafo g, int origen) {
        n = g.getCantidadVertices();
        s = origen;
        pesos = g.getMatrizAdyacencia();
        ultimo = new int[n];
        D = new int[n];
        F = new boolean[n];
    }

    void Dijkstra(Grafo g, int origen) {
        // valores iniciales
        for (int i = 0; i < n; i++) {
            F[i] = false;
            D[i] = pesos[s][i];
            ultimo[i] = s;
        }
        F[s] = true;
        D[s] = 0;

        // Pasos para marcar los n-1 vertices
        for (int i = 1; i < n; i++) {
            int v = minimo();
            if (v == 1)
                i = n; // Si no hay más vértices alcanzables

            F[v] = true;
            for (int w = 0; w < n; w++) {
                if (!F[w] && pesos[v][w] != Integer.MAX_VALUE) {
                    if (D[v] + pesos[v][w] < D[w]) {
                        D[w] = D[v] + pesos[v][w];
                        ultimo[w] = v;
                    }
                }
            }
        }
    }

    public int minimo() {
        int mx = Integer.MAX_VALUE;
        int v = 1;
        for (int j = 0; j < n; j++) {
            if (!F[j] && D[j] < mx) {
                mx = D[j];
                v = j;
            }
        }
        return v;
    }

    public void recuperaCamino(int v) {
        int anterior = ultimo[v];
        if (v != s) {
            recuperaCamino(anterior);
            System.out.println(v + " V <-- ");
        } else {
            System.out.println(s);
        }
    }
}