package ui;

import domain.dto.ProductIdDto;
import domain.dto.ProductoDto;
import service.InventarioService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private static InventarioService service = new InventarioService();
    private Scanner sc = new Scanner(System.in);
    private final String MENU = "Seleccione una opción:\n0-para ver tabla de productos\n1-buscar producto\n" +
            "2-Agregar Producto\n3-Actualizar Producto\n4-Eliminar Prodcuto\n10-para salir\nopcion: ";
    public void menu(){
        while(true){
            try{
                System.out.println("\t====================MonacoInventary====================");
                System.out.println(MENU);
                int opcion = sc.nextInt();
                sc.nextLine();
                if(opcion == 10) break;
                switch (opcion){
                    case 0 -> muestraProdcutos();
                    case 1 -> muestraProducto();
                    case 2 -> creaProducto();
                    case 3 -> actualizaProducto();
                    case 4 -> eliminaProducto();
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
        System.out.println("\t|| id || nombre ||  stock || precio ||");
        list.forEach(p -> System.out.printf("\t|| %d || %s || %d || %.2f || \n",p.id(),p.nombre(),p.stock(),p.precio()));
        System.out.println("\t=======================================");

    }

    private void muestraProducto(){
        System.out.println("Ingrese el nombre o el id del producto :");
        String busqueda = sc.nextLine();
        ProductIdDto p;
        try{
            int id = Integer.valueOf(busqueda);
            p = service.getById(id);
        }catch (NumberFormatException e){
            p = service.getByNombre(busqueda);
        }
        if(p == null)System.out.println("No se encontro el producto");
        else pintaTabla(List.of(p));
    }

    private void muestraProdcutos(){
        List<ProductIdDto> lista = service.getAll();
        if(!lista.isEmpty())pintaTabla(lista);
        else System.out.println("No existen productos!!!");
    }

    private void creaProducto(){
        String respuesta;
        ProductoDto p = creaProdcutoDto();
        respuesta = service.createProduct(p) ? "Creado Exitosamente" : "No se puedo crear el producto " + p;
        System.out.println(respuesta);
    }

    private void actualizaProducto(){
        System.out.println("Ingrese el nombre del producto o id");
        String busqueda = sc.nextLine();
        ProductIdDto p;
        try{
            int id = Integer.valueOf(busqueda);
            p = service.getById(id);
        }catch (NumberFormatException e){
            p = service.getByNombre(busqueda);
        }
        if(p == null){
            System.out.println("Producto no encontrado");
            return;
        }
        productoActualizacion(p);
    }

    private void productoActualizacion(ProductIdDto p){
        System.out.println("Que deseas actualizar del producto :");
        pintaTabla(List.of(p));
        String nombre = p.nombre();
        Long stock = p.stock();
        BigDecimal precio = p.precio();
        while(true){
            try {
                System.out.println("Ingrese 1-Actualizar nombre\n2-Actualizar stock\n3-Actualizar precio\n4-Confirmar\n0 para cancelar");
                int opcion = sc.nextInt();
                sc.nextLine();
                if (opcion == 0) return;
                else if (opcion == 4) break;
                switch (opcion) {
                    case 1 -> {
                        System.out.println("Nombre nuevo :");
                        nombre = sc.nextLine();
                    }
                    case 2 -> {
                        System.out.println("Stock nuevo :");
                        stock = sc.nextLong();
                    }
                    case 3 -> {
                        System.out.println("Precio nuevo :");
                        precio = sc.nextBigDecimal();
                    }
                    default -> System.out.println("Ingrese una opción valida");
                }
                ;
            } catch (Exception e) {
                System.out.println("Entrada no valida");
            }
        }
        String resultado = service.updateProduct(new ProductIdDto(p.id(),nombre,stock,precio)) ? "Actualización exitosa!!!!":
                "Error en la actualización";
        System.out.println(resultado);
    }

    private ProductoDto creaProdcutoDto(){
        System.out.println("Ingrese los datos del producto");
        System.out.println("Nombre del producto: ");
        String nombre = sc.nextLine();
        System.out.println("Stock del producto: ");
        Long cantidad = sc.nextLong();
        System.out.println("Precio del producto: ");
        BigDecimal precio = sc.nextBigDecimal();
        return new ProductoDto(nombre,cantidad,precio);
    }

    private void eliminaProducto(){
        System.out.println("Ingresa el nombre o id del producto a eliminar : ");
        String busqueda = sc.nextLine();
        boolean result;
        try{
            int id = Integer.valueOf(busqueda);
            result = service.deleteProduct(id);
        }catch (NumberFormatException e){
            result = service.deleteProduct(busqueda);
        }
        String respuesta = result ? "Eliminación Exitosa!!!!!" : "No se pudo eliminar el producto!!!!!";
        System.out.println(respuesta);
    }
}
