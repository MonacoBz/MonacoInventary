package dao;

import domain.ProductoDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InventarioRepository {
    private static InventarioRepository repository;
    Connection connection;
    private final String URL ="jdbc:mysql://localhost:3306/MonacoInventaory";
    private final String USERNAME = "root";
    private final String PASSWORD = "Rocobz16";
    private InventarioRepository(){
        try{
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        }catch (SQLException e){
            System.out.println(e);
        }
    }
    public static InventarioRepository getInstance(){
        if(repository == null){
            repository = new InventarioRepository();
        }
        return repository;
    }

    public boolean createProduct(ProductoDto producto){
        try{
            String sqlInsert = "INSERT INTO producto(nombre,stock,precio)" +
                    "VALUES (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sqlInsert);
            ps.setString(1,producto.nombre());
            ps.setLong(2,producto.stock());
            ps.setBigDecimal(3,producto.precio());
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
}
