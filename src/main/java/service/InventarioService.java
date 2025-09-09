package service;

import dao.InventarioRepository;
import domain.ProductoDto;

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


}
