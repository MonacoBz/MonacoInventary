package dao;

import domain.Producto;
import domain.dto.ProductIdDto;
import domain.dto.ProductoDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventarioDao {
    private static InventarioDao repository;
    Connection connection;
    private final String URL ="jdbc:mysql://localhost:3306/MonacoInventaory";
    private final String USERNAME = "root";
    private final String PASSWORD = "Rocobz16";
    private InventarioDao(){
        try{
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        }catch (SQLException e){
            System.out.println(e);
        }
    }
    public static InventarioDao getInstance(){
        if(repository == null){
            repository = new InventarioDao();
        }
        return repository;
    }

    public boolean createProduct(Producto producto){
        try{
            String sqlInsert = "INSERT INTO producto(nombre,stock,precio)" +
                    "VALUES (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sqlInsert);
            ps.setString(1,producto.getNombre());
            ps.setLong(2,producto.getStock());
            ps.setBigDecimal(3,producto.getPrecio());
            ps.executeUpdate();
            return true;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;

        }
    }

    public boolean deleteProduct(String name){
        String SQLstatement = "DELETE FROM producto WHERE nombre=?";
        try(PreparedStatement ps = connection.prepareStatement(SQLstatement)){
            ps.setString(1,name);
            ps.executeUpdate();
            return true;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteProduct(Integer id){
        String SQLstatement = "DELETE FROM producto WHERE id=?";
        try(PreparedStatement ps = connection.prepareStatement(SQLstatement)){
            ps.setInt(1,id);
            ps.executeUpdate();
            return true;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<ProductIdDto> findAll(){
        String SQLstatement = "SELECT * FROM producto";
        try(PreparedStatement ps = connection.prepareStatement(SQLstatement)){
            ResultSet rs = ps.executeQuery();
            List<ProductIdDto> products = new ArrayList<>();
            while(rs.next()){
             ProductIdDto p = new ProductIdDto(
                     Long.valueOf(rs.getInt(1)),
                     rs.getString(2),
                     Long.valueOf(rs.getInt(3)),
                     rs.getBigDecimal(4)
             );
             products.add(p);
            }
            return products;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return List.of();
        }
    }

    public ProductIdDto findById(int id){
        String sqlStatement = "SELECT id, nombre, stock, precio FROM producto WHERE id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sqlStatement)){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            ProductIdDto p = null;
            if(rs.next()) {
                p = new ProductIdDto(rs.getLong(1),
                        rs.getString(2),
                        rs.getLong(3),
                        rs.getBigDecimal(4));
            }
            return p;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean updateProduct(ProductIdDto p){
        String sqlStatement = "UPDATE producto SET nombre = ? , stock = ?, precio = ? WHERE id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sqlStatement)){
            ps.setString(1,p.nombre());
            ps.setLong(2,p.stock());
            ps.setBigDecimal(3,p.precio());
            ps.setLong(4,p.id());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public ProductIdDto findByNombre(String nombre){
        String sqlStatement = "SELECT id, nombre, stock, precio FROM producto WHERE nombre = ?";
        try(PreparedStatement ps = connection.prepareStatement(sqlStatement)){
            ps.setString(1,nombre);
            ResultSet rs = ps.executeQuery();
            ProductIdDto p = null;
            if(rs.next()) {
                p =  new ProductIdDto(rs.getLong(1),
                        rs.getString(2),
                        rs.getLong(3),
                        rs.getBigDecimal(4));
            }
            return p;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    public List<ProductIdDto> getByLowStack(){
        String sqlStatement = "SELECT * FROM producto WHERE stock <= ?";
        List<ProductIdDto> productos = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement(sqlStatement)){
            ps.setInt(1,4);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ProductIdDto p =  new ProductIdDto(rs.getLong(1),
                        rs.getString(2),
                        rs.getLong(3),
                        rs.getBigDecimal(4));
                productos.add(p);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return productos;
    }
}
