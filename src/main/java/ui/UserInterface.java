package ui;

import domain.dto.ProductIdDto;
import service.InventarioService;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private static InventarioService service = new InventarioService();
    public void menu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\t====================Bienvenido a MonacoInventary====================");
        while(true){
            try{
                System.out.println("Seleccione una opción:\n0 para ver tabla de productos\n1 buscar producto por nombre\n2 buscar producto por id\n10 para salir");
                int opcion = sc.nextInt();
                sc.nextLine();
                if(opcion == 10) break;
                switch (opcion){
                    case 0 -> muestraProdcutos();
                    case 1 -> {
                        String nombre = sc.nextLine();
                        muestraProducto(nombre);
                    }
                    case 2 -> {
                        int id = sc.nextInt();
                        muestraProducto(id);
                    }
                    default -> System.out.println("Seleccióna una opción valida");
                };
            }catch (Exception e){
                System.out.println("Error, elija una opción valida");
                sc.nextLine();
            }
        }
        System.out.println("Hasta luego ;:D");
    }
    private void pintaTabla(List<ProductIdDto> list){
        System.out.println("\t=======================================");
        System.out.println("\t|| id || nombre || producto || stock || precio ||");
        list.forEach(p -> System.out.printf("\t|| %d || %s || %d || %.2f || \n",p.id(),p.nombre(),p.stock(),p.precio()));
        System.out.println("\t=======================================");

    }

    private void muestraProducto(String nombre){
        pintaTabla(List.of(service.getByNombre(nombre)));
    }

    private void muestraProducto(int id){
        pintaTabla(List.of(service.getById(id)));
    }

    private void muestraProdcutos(){
        pintaTabla(service.getAll());
    }
}
