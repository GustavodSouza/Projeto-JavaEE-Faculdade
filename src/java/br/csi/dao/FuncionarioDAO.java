package br.csi.dao;

import br.csi.model.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FuncionarioDAO {
    Conexao cn = new Conexao();
    Connection con;
    PreparedStatement pre;
    ResultSet rs;
    
    public Funcionario read(int id) {
        try {
            String sql = "SELECT * FROM funcionario WHERE funcionario.id_funcionario = ?";
            
            con = cn.getConnection();
            pre = con.prepareStatement(sql);
            
            pre.setInt(1, id);

            rs = pre.executeQuery();

            while (rs.next()) {
                Funcionario f = new Funcionario();
                f.setId(id);
                f.setNome(rs.getString("nome"));
                f.setSenha(rs.getString("senha"));
                return f;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Funcionario read(String email, String senha) {
        try {
            String sql = "SELECT * FROM funcionario WHERE email = ? AND senha=?";
            
            con = cn.getConnection();
            pre = con.prepareStatement(sql);
            
            pre.setString(1, email);
            pre.setString(2, senha);
            
            rs = pre.executeQuery();
            
            while (rs.next()) {
                Funcionario f = new Funcionario();
                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setEmail(rs.getString("email"));
                f.setSenha(rs.getString("senha"));
                return f;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
