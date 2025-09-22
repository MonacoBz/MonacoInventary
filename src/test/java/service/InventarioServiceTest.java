package service;

import domain.dto.ProductIdDto;
import domain.dto.ProductoDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventarioServiceTest {

    private InventarioService service;
    
    @BeforeEach
    void setUp(){
        service = new InventarioService();
    }
    @Test
    void agregarProducto(){
        ProductoDto p = new ProductoDto("Producto caro dos",3L,new BigDecimal(1250.50));
        ProductoDto p2 = new ProductoDto("Producto caro tres",2L,new BigDecimal(3530.55));
            assertTrue(service.createProduct(p));
            assertTrue(service.createProduct(p2));
    }

//    @Test
    void eliminaProducto(){
        String nombre = "Play";

        assertEquals(true,service.deleteProduct(2));
        assertEquals(true,service.deleteProduct(3));
        assertEquals(true,service.deleteProduct(nombre));
    }

    @Test
    void leerProductos(){
        List<ProductIdDto> p = service.getAll();
        ProductIdDto pr2 = service.getByNombre("Pochoclos");
        ProductIdDto pr3 = service.getById(1);
        p.forEach(System.out::println);
        assertFalse(p.isEmpty());
        assertNotNull(pr2);
        assertNotNull(pr3);
    }

    //@Test
    void actualizarProducto(){
        ProductIdDto p = new ProductIdDto(2L,"Xbox",5L,new BigDecimal(5530.55));
        assertTrue(service.updateProduct(p));
    }

    //@Test
    void stockBajo(){
        List<ProductIdDto> p = service.getByLowStock();
        p.forEach(System.out::println);
        assertTrue(!p.isEmpty());
    }
}