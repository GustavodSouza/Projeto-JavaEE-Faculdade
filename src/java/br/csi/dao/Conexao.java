package br.csi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    Connection con;
    
    public Conexao(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/estoque", "root", "1234");  
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error = " + e);
        }
    }
    
    public Connection getConnection(){
        return con;
    }
}
