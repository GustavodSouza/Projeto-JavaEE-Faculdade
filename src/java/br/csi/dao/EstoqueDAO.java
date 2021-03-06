package br.csi.dao;

import br.csi.model.Estoque;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EstoqueDAO {
    Conexao cn = new Conexao();
    Connection con;
    PreparedStatement pre;
    ResultSet rs;
    
    public int create(int idProduto, int quantidade) {
        try {
            String sql = "INSERT INTO estoque (idProduto, quantidade) VALUES (?, ?)";
            
            con = cn.getConnection();
            pre = con.prepareStatement(sql);
            
            pre.setInt(1, idProduto);
            pre.setInt(2, quantidade);
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

    public boolean create(Estoque estoque) {
        try {
            String sql = "INSERT INTO estoque (idProduto, quantidade) VALUES (?, ?)";
            
            con = cn.getConnection();
            pre = con.prepareStatement(sql);
            
            pre.setInt(1, estoque.getIdProduto());
            pre.setInt(2, estoque.getQuantidade());

            if (pre.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Estoque read(int idProduto) {
        try {
            String sql = "SELECT * FROM estoque WHERE estoque.idProduto = ?";
                        
            con = cn.getConnection();
            pre = con.prepareStatement(sql);
            
            pre.setInt(1, idProduto);

            rs = pre.executeQuery();

            while (rs.next()) {
                Estoque e = new Estoque();
                e.setIdProduto(idProduto);
                e.setQuantidade(rs.getInt("quantidade"));
                return e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateEntrada(Estoque estoque) {
        try {
            String sql = "UPDATE estoque SET quantidade = quantidade + ? WHERE idProduto = ?";
            
            con = cn.getConnection();
            pre = con.prepareStatement(sql);
            
            pre.setInt(1, estoque.getQuantidade());
            pre.setInt(2, estoque.getIdProduto());
            
            if (pre.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean updateSaida(Estoque estoque) {
        try {
            String sql = "UPDATE estoque SET quantidade = quantidade - ? WHERE idProduto = ?";
            
            con = cn.getConnection();
            pre = con.prepareStatement(sql);
            
            pre.setInt(1, estoque.getQuantidade());
            pre.setInt(2, estoque.getIdProduto());
            
            if (pre.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) {
        try {
            String sql = "DELETE FROM produto WHERE id_produto = ?";
            
            con = cn.getConnection();
            pre = con.prepareStatement(sql);
            
            pre.setInt(1, id);
            
            if (pre.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Estoque> getEstoque() {
        ArrayList<Estoque> estoque = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM estoque";
            
            con = cn.getConnection();
            pre = con.prepareStatement(sql);
            
            rs = pre.executeQuery();
            
            while (rs.next()) {
                Estoque est = new Estoque();
                est.setIdProduto(rs.getInt("idProduto"));
                est.setQuantidade(rs.getInt("quantidade"));

                estoque.add(est);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return estoque;
    }
}
