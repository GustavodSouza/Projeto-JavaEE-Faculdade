package br.csi.dao;

import br.csi.model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {
    Conexao cn = new Conexao();
    Connection con;
    PreparedStatement pre;
    ResultSet rs;
    
    public int create(int idFuncionario, String descricao, String marca, float preco) {
        System.out.println("Chamou");
        try {
            String sql = "INSERT INTO produto (idFuncionario, descricao, marca, preco) VALUES (?, ?, ?, ?)";
            
            con = cn.getConnection();
            pre = con.prepareStatement(sql);
            
            pre.setInt(1, idFuncionario);
            pre.setString(2, descricao);
            pre.setString(3, marca);
            pre.setFloat(4, preco);
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

    public boolean create(Produto produto) {
        try {
            String sql = "INSERT INTO produto (idFuncionario, descricao, marca, preco) VALUES (?, ?, ?, ?)";
            
            con = cn.getConnection();
            pre = con.prepareStatement(sql);
            
            pre.setInt(1, produto.getIdFuncionario());
            pre.setString(2, produto.getDescricao());
            pre.setString(3, produto.getMarca());
            pre.setFloat(4, produto.getPreco());

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
            String sql = "SELECT * FROM produto WHERE produto.id_produto = ?";
            
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

    public boolean update(Produto produto) {
        try {
            String sql = "UPDATE produto SET descricao = ?, marca = ?, preco = ?, unidade = ? WHERE produto.id_produto = ?";
            
            con = cn.getConnection();
            pre = con.prepareStatement(sql);
            
            pre.setString(1, produto.getDescricao());
            pre.setString(2, produto.getMarca());
            pre.setFloat(3, produto.getPreco());

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

    public ArrayList<Produto> getProdutos() {
        ArrayList<Produto> produtos = new ArrayList<>();
       
        try {
            String sql = "SELECT * FROM produto";
            
            con = cn.getConnection();
            pre = con.prepareStatement(sql);
            
            rs = pre.executeQuery();
            
            while (rs.next()) {
                Produto pro = new Produto();
                pro.setId(rs.getInt("id"));
                pro.setIdFuncionario(rs.getInt("idFuncionario"));
                pro.setDescricao(rs.getString("descricao"));
                pro.setMarca(rs.getString("marca"));
                pro.setPreco(rs.getFloat("preco"));

                produtos.add(pro);
            }
            return produtos;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
