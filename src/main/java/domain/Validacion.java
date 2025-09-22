package domain;

import domain.dto.ProductIdDto;
import domain.dto.ProductoDto;

import java.math.BigDecimal;

public class Validacion {

    public static boolean validarNombre(String nombre){
        return !nombre.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ\\s]+$");
    }
    public static boolean validarId(int id){
        return id < 0;
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

    public static boolean validarProducto(ProductIdDto p){
        return validarNombre(p.nombre()) || validarPrecio(p.precio()) || validarStock(p.stock());
    }


}
