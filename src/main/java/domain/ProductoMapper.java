package domain;

import dto.ProductIdDto;

public class ProductoMapper {

    public ProductIdDto toProductId(Producto p){
        return new ProductIdDto(p.getId()
                ,p.getNombre()
                ,p.getStock()
                ,p.getPrecio());
    }

    public Producto toProduct (ProductIdDto dto){
        return new Producto(dto.id(),dto.nombre()
        ,dto.precio()
        ,dto.stock());
    }
}
