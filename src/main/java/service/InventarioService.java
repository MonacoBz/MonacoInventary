package service;

import dao.InventarioRepository;
import domain.ProductoDto;

public class InventarioService {

    InventarioRepository repository;
    public InventarioService(){
        repository = InventarioRepository.getInstance();
    }

    public boolean createProducto(ProductoDto p){
        return repository.createProduct(p);
    }


}
