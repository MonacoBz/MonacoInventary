package domain;

import domain.dto.ProductoDto;

import java.math.BigDecimal;

public class Validacion {

    private static boolean validarNombre(String nombre){
        System.out.println("nombre del proroducto : " + nombre + !nombre.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ\\s]+$"));
        return !nombre.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ\\s]+$");

    }

    private static boolean validarPrecio(BigDecimal precio){
        return precio.compareTo(BigDecimal.ZERO) <= 0;
    }

    private static boolean validarStock(Long stock){
        return stock <= 0;
    }

    public static boolean validarProducto(ProductoDto p){
        return validarNombre(p.nombre()) || validarPrecio(p.precio()) || validarStock(p.stock());
    }

}
