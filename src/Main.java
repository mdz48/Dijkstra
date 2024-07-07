import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int cantidad = 0, stock = 0;
        boolean next = false;
        String name = "";
        System.out.println("Indica la cantidad de vertices del grafo:");
        while (!next) {
            try{
                cantidad = entrada.nextInt();
                if (cantidad < 2){
                    System.out.println("Ingrese una cantidad válida (Mayor a 1):");
                } else {
                    next = true;
                }
            } catch (InputMismatchException e){
                System.out.println("Ingrese solamente dígitos");
                entrada.next();
            }
        }
        entrada.nextLine();
        Grafo grafo = new Grafo(cantidad);
        for (int i = 0; i < cantidad; i++) {
            System.out.println("Ingresa el nombre del centro de distribución:");
            next = false;
            while (!next) {
                name = entrada.nextLine();
                if (name.isEmpty()){
                    System.out.println("Ingrese el nombre del centro");
                } else {
                    next = true;
                }

            }
            System.out.println("Ingresa el stock del artículo:");
            next = false;
            while (!next) {
                try {
                    stock = entrada.nextInt();
                    entrada.nextLine();
                    if (stock < 0) {
                        System.out.println("La cantidad debe ser mayor o igual a 0:");
                    } else {
                        next = true;
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

        System.out.println("Ingrese el índice del origen:");
        for (int i = 0; i < grafo.listaVertices.length; i++) {
            System.out.println((i + 1) + ") " + grafo.listaVertices[i].getNombre());
        }
        int seleccion = 0;
        next = false;
        while (!next) {
            try {
                seleccion = entrada.nextInt();
                entrada.nextLine();
                if (seleccion < 1 || seleccion > grafo.listaVertices.length) {
                    System.out.println("Índice no válido. Debe ser entre 1 y " + grafo.listaVertices.length);
                } else {
                    next = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Ingrese solamente dígitos");
                entrada.next();
            }
        }
        Vertice cercano = grafo.buscarCentroMasCercano(grafo, seleccion - 1);
        if (cercano == null) {
            System.out.println("No existen artículos disponibles en ningún centro.");
        } else {
            System.out.println("El centro más cercano con disponibilidad es: " + cercano.getNombre());
        }
    }
}
