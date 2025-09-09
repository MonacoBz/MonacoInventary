package service;

import domain.ProductoDto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class InventarioServiceTest {

    InventarioService service = new InventarioService();
    @Test
    void agregarProducto(){
        ProductoDto p = new ProductoDto("Xbox",20L,new BigDecimal(1250.50));
            assertEquals(true,service.createProducto(p));
    }
}