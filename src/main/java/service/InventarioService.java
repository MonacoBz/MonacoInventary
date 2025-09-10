package service;

import dao.InventarioDao;
import domain.Producto;
import domain.dto.ProductIdDto;
import domain.dto.ProductoDto;

import java.util.List;

public class InventarioService {

    InventarioDao repository;
    public InventarioService(){
        repository = InventarioDao.getInstance();
    }

    public boolean createProduct(ProductoDto p){
        if(p.precio().doubleValue()<=0||p.stock()<=0)return false;
        Producto producto = new Producto(null,p.nombre(),p.precio(),p.stock());
        return repository.createProduct(producto);
    }

    public boolean deleteProduct(String name){
        return repository.deleteProduct(name);
    }

    public boolean deleteProduct(Integer id){
        return repository.deleteProduct(id);
    }

    public List<ProductIdDto> getAll(){
        List<ProductIdDto> products = repository.findAll();
        return products == null ? List.of() : products ;
    }

    public ProductIdDto getById(int id){
        return repository.findById(id);
    }

    public boolean updateProduct(ProductIdDto p){
        return repository.updateProduct(p);
    }

    public ProductIdDto getByNombre(String nombre){
        return repository.findByNombre(nombre);
    }
}
