package project.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;"
            + "databaseName=CitizenComplaintManagementSystem;"
            + "encrypt=true;"
            + "trustServerCertificate=true;";
    
    private static final String DB_USERNAME = "romeo";
    private static final String DB_PASSWORD = "211708";
    private static Connection connection;
    
    public static Connection getConnection() throws SQLException {  

        return connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
   
    }
    
}
