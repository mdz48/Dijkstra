import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int cantidad, stock = 0;
        System.out.println("Indica la cantidad de vertices del grafo:");
        cantidad = entrada.nextInt();
        while (cantidad < 2) {
            System.out.println("Ingrese una cantidad válida (Mayor a 1):");
            cantidad = entrada.nextInt();
        }
        entrada.nextLine();
        Grafo grafo = new Grafo(cantidad);
        for (int i = 0; i < cantidad; i++) {
            System.out.println("Ingresa el nombre del centro de distribución:");
            String name = entrada.nextLine();
            System.out.println("Ingresa el stock del artículo:");
            boolean stockValido = false;
            while (!stockValido) {
                try {
                    stock = entrada.nextInt();
                    entrada.nextLine();
                    if (stock < 0) {
                        System.out.println("La cantidad debe ser mayor o igual a 0:");
                    } else {
                        stockValido = true;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Ingresa solamente dígitos para el stock:");
                    entrada.next();
                }
            }
//            grafo.agregarVertice(name, i, stock);
            if (!grafo.agregarVertice(name, i, stock)){
                System.out.println("Centro repetido");
                i--;
            }
        }
        grafo.agregarAristas();
        grafo.imprimir();

        System.out.println("Ingrese origen:");
        int seleccion = entrada.nextInt() - 1;
        Vertice cercano = grafo.buscarCentroMasCercano(grafo, seleccion);
        if (cercano == null) {
            System.out.println("No existen artículos disponibles en ningún centro.");
        } else {
            System.out.println("El centro más cercano con disponibilidad es: " + cercano.getNombre());
        }
    }
}
