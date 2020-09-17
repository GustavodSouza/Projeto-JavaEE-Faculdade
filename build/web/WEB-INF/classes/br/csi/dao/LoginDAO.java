package br.csi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    public boolean autenticar(String login, String senha){
        
       try(Connection conn = new ConectaDB_Postgres().getConexao() ){
           
           String sql = " SELECT * FROM funcionario "
                   + " WHERE senha = ? AND email = ?";
           PreparedStatement pStmt = conn.prepareStatement(sql);
           pStmt.setString(1, senha);
           pStmt.setString(2, login);
           ResultSet rs = pStmt.executeQuery();
           while(rs.next()){
               return true;
           }
           
       }catch(SQLException e){
           e.printStackTrace();
       }
        
        return false;
    }

}
