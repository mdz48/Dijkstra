class Vertice {
    private String nombre;
    private int stock;
    private int numVertice;

    public Vertice(String nombre, int stock) {
        this.nombre = nombre;
        this.stock = stock;
        this.numVertice = -1;
    }

    public String getNombre() {
        return nombre;
    }

    public void asigVert(int numVertice) {
        this.numVertice = numVertice;
    }

    public int getNumVertice() {
        return numVertice;
    }

    public int getStock() {
        return stock;
    }
}