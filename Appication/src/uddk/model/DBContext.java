/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uddk.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author USER
 */
public class DBContext {
    private final String serverName = "localhost";
    private final String dbName = "BookingAssignment";
    private final String portNumber = "5432";
    private final String username = "postgres";
    private final String password = "123456";
    
    public DBContext() {
        
    }
    
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        //Class.forName("com.postgresql.jdbc.Driver");
        String url = "jdbc:postgresql://" + serverName + ":" + portNumber + "/" + dbName;
        return DriverManager.getConnection(url, username, password);
    }
    
    public void closeConnection(ResultSet rs, PreparedStatement ps, Connection connection) throws SQLException {
        if (rs != null && !rs.isClosed()) {
            rs.close();
        }
        if (ps != null && !ps.isClosed()) {
            ps.close();
        }
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
