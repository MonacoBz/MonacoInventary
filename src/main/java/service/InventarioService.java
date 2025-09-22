package service;

import dao.InventarioDao;
import domain.Producto;
import domain.ProductoMapper;
import domain.Validacion;
import dto.ProductIdDto;
import dto.ProductoDto;

import java.util.List;

public class InventarioService {

    InventarioDao inventarioDao;
    private final ProductoMapper mapper;
    public InventarioService(){
        inventarioDao = InventarioDao.getInstance();
        mapper = new ProductoMapper();
    }

    public boolean createProduct(ProductoDto p){
        if(Validacion.validarProducto(p)) return false;
        return inventarioDao.createProduct(new Producto(p.nombre(),p.precio(),p.stock()));
    }

    public boolean deleteProduct(String name){
        if(Validacion.validarNombre(name))return false;
        return inventarioDao.findByNombre(name) != null && inventarioDao.deleteProduct(name);
    }

    public boolean deleteProduct(Integer id){
        if(Validacion.validarId(id))return false;
        return inventarioDao.findById(id) != null && inventarioDao.deleteProduct(id);
    }

    public List<ProductIdDto> getAll(){
        return inventarioDao.findAll().
                stream()
                .map(mapper::toProductId)
                .toList();
    }

    public ProductIdDto getById(int id){
        if(Validacion.validarId(id)) return null;
        var producto =  inventarioDao.findById(id);
        return mapper.toProductId(producto);
    }

    public boolean updateProduct(ProductIdDto p){
        if(Validacion.validarProducto(p))return false;
        return inventarioDao.updateProduct(mapper.toProduct(p));
    }

    public ProductIdDto getByNombre(String nombre){
        if(Validacion.validarNombre(nombre))return null;
        var producto =  inventarioDao.findByNombre(nombre);
        return mapper.toProductId(producto);
    }

    public List<ProductIdDto> getByLowStock(){
        return inventarioDao.getByLowStack()
                .stream()
                .map(mapper::toProductId)
                .toList();
    }
}
