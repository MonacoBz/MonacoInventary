package service;

import domain.ProductoDto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class InventarioServiceTest {

    InventarioService service = new InventarioService();

    void agregarProducto(){
        ProductoDto p = new ProductoDto("Xbox",20L,new BigDecimal(1250.50));
        ProductoDto p2 = new ProductoDto("Play",20L,new BigDecimal(1250.50));
            assertEquals(true,service.createProduct(p));
            assertEquals(true,service.createProduct(p2));
    }

    @Test
    void eliminaProducto(){
        String nombre = "Play";

        assertEquals(true,service.deleteProduct(2));
        assertEquals(true,service.deleteProduct(3));
        assertEquals(true,service.deleteProduct(nombre));
    }
}