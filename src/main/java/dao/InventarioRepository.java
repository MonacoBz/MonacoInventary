package dao;

import java.sql.Connection;
import java.sql.DriverManager;
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
}
