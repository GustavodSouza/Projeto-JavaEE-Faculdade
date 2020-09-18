package br.csi.dao;

import br.csi.model.Entrada;
import br.csi.model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EntradaDAO {
    Conexao cn = new Conexao();
    Connection con;
    PreparedStatement pre;
    ResultSet rs;
    
    public int create(int idProduto, int idFuncionario, String data_entrada, int quantidade) {
        try {
            
            String sql = "INSERT INTO entrada (idProduto, idFuncionario, data_entrada, quantidade) values (?, ?, ?, ?)";
            
            con = cn.getConnection();
            pre = con.prepareStatement(sql);
            
            pre.setInt(1, idProduto);
            pre.setInt(2, idFuncionario);
            pre.setString(3, data_entrada);
            pre.setInt(4, quantidade);
            
            pre.execute();
            
            rs = pre.getGeneratedKeys();
            rs.next();
            
            if (rs.getInt(1) > 0) {
                return rs.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean create(Entrada entrada) {
        try {
            String sql = "INSERT INTO entrada (idProduto, idFuncionario, data_entrada, quantidade) VALUES (?, ?, ?, ?)";
            
            con = cn.getConnection();
            pre = con.prepareStatement(sql);
            
            pre.setInt(1, entrada.getIdProduto());
            pre.setInt(2, entrada.getIdFuncionario());
            pre.setString(3, entrada.getData_entrada());
            pre.setInt(4, entrada.getQuantidade());

            if (pre.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Produto read(int id) {
        try {
            String sql = "SELECT * FROM entrada WHERE entrada.id_produto = ?";
            
            con = cn.getConnection();
            pre = con.prepareStatement(sql);
            
            pre.setInt(1, id);

            rs = pre.executeQuery();

            while (rs.next()) {
                Produto p = new Produto();
                p.setId(id);
                p.setDescricao(rs.getString("descricao"));
                p.setMarca(rs.getString("marca"));
                p.setPreco(rs.getFloat("preco"));
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Entrada> getEntrada() {
        ArrayList<Entrada> entrada = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM entrada";
            
            con = cn.getConnection();
            pre = con.prepareStatement(sql);
            
            rs = pre.executeQuery();
            
            while (rs.next()) {
                Entrada ent = new Entrada();
                ent.setIdProduto(rs.getInt("idProduto"));
                ent.setIdFuncionario(rs.getInt("idFuncionario"));
                ent.setData_entrada(rs.getString("data_entrada"));
                ent.setQuantidade(rs.getInt("quantidade"));

                entrada.add(ent);
            }
            return entrada;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
