package br.csi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    Conexao cn = new Conexao();
    Connection con;
    PreparedStatement pre;
    ResultSet rs;
    
    public boolean autenticar(String login, String senha){
        
       try {
           
           String sql = " SELECT * FROM funcionario WHERE senha = ? AND email = ?";
           
           con = cn.getConnection();
           pre = con.prepareStatement(sql);
           
           pre.setString(1, senha);
           pre.setString(2, login);
           rs = pre.executeQuery();
           
           while(rs.next()){
               return true;
           }
           
       }catch(SQLException e){
           e.printStackTrace();
       }
        
        return false;
    }

}
