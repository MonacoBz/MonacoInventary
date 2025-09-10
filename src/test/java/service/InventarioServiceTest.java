package service;

import domain.dto.ProductIdDto;
import domain.dto.ProductoDto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventarioServiceTest {

    InventarioService service = new InventarioService();
//    @Test
    void agregarProducto(){
        ProductoDto p = new ProductoDto("Xbox",20L,new BigDecimal(1250.50));
        ProductoDto p2 = new ProductoDto("Play",20L,new BigDecimal(3530.55));
            assertEquals(true,service.createProduct(p));
            assertEquals(true,service.createProduct(p2));
    }

//    @Test
    void eliminaProducto(){
        String nombre = "Play";

        assertEquals(true,service.deleteProduct(2));
        assertEquals(true,service.deleteProduct(3));
        assertEquals(true,service.deleteProduct(nombre));
    }

    //@Test
    void leerProductos(){
        List<ProductIdDto> p = service.getAll();
        p.forEach(System.out::println);
        assertFalse(p.isEmpty());
    }

    @Test
    void actualizarProducto(){
        ProductIdDto p = new ProductIdDto(2L,"Xbox",5L,new BigDecimal(5530.55));
        assertTrue(service.updateProduct(p));
    }
}