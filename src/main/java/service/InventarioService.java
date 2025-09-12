package service;

import dao.InventarioDao;
import domain.Producto;
import domain.Validacion;
import domain.dto.ProductIdDto;
import domain.dto.ProductoDto;

import java.math.BigDecimal;
import java.util.List;

public class InventarioService {

    InventarioDao inventarioDao;
    public InventarioService(){
        inventarioDao = InventarioDao.getInstance();
    }

    public boolean createProduct(ProductoDto p){
        if(Validacion.validarProducto(p)) return false;
        return inventarioDao.createProduct(new Producto(p.nombre(),p.precio(),p.stock()));
    }

    public boolean deleteProduct(String name){
        return inventarioDao.deleteProduct(name);
    }

    public boolean deleteProduct(Integer id){
        return inventarioDao.deleteProduct(id);
    }

    public List<ProductIdDto> getAll(){
        return inventarioDao.findAll();
    }

    public ProductIdDto getById(int id){
        return inventarioDao.findById(id);
    }

    public boolean updateProduct(ProductIdDto p){
        return inventarioDao.updateProduct(p);
    }

    public ProductIdDto getByNombre(String nombre){
        return inventarioDao.findByNombre(nombre);
    }

    public List<ProductIdDto> getByLowStock(){
        return inventarioDao.getByLowStack();
    }
}
