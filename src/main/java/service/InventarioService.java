package service;

import dao.InventarioRepository;
import domain.dto.ProductIdDto;
import domain.dto.ProductoDto;

import java.util.List;

public class InventarioService {

    InventarioRepository repository;
    public InventarioService(){
        repository = InventarioRepository.getInstance();
    }

    public boolean createProduct(ProductoDto p){
        return repository.createProduct(p);
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
    public ProductIdDto getByNombre(String nombre){
        return repository.findByNombre(nombre);
    }
}
