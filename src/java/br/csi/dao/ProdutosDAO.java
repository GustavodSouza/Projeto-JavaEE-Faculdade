package br.csi.dao;

import br.csi.model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProdutosDAO {

    public int create(int idFuncionario, String descricao, String marca, float preco) {
        try (Connection conn = new ConectaDB_Postgres().getConexao()) {
            String sql = "INSERT INTO produto (idFuncionario, descricao, marca, preco) values (?, ?, ?, ?)";
            PreparedStatement pre = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pre.setInt(1, idFuncionario);
            pre.setString(2, descricao);
            pre.setString(3, marca);
            pre.setFloat(4, preco);
            pre.execute();
            ResultSet rs = pre.getGeneratedKeys();
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
        //conexao com o banco.
        try (Connection conn = new ConectaDB_Postgres().getConexao()) {
            String sql = "insert into produto (idFuncionario, descricao, marca, preco) values (?, ?, ?, ?)";
            PreparedStatement pre = conn.prepareStatement(sql);
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
        try (Connection conn = new ConectaDB_Postgres().getConexao()) {
            String sql = "select * from produto where produto.id_produto = ?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, id);

            ResultSet rs = pre.executeQuery();

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
        try (Connection conn = new ConectaDB_Postgres().getConexao()) {
            String sql = "UPDATE produto SET descricao = ?, marca = ?, preco = ?, unidade = ? WHERE produto.id_produto = ?";
            PreparedStatement pre = conn.prepareStatement(sql);
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
        try (Connection conn = new ConectaDB_Postgres().getConexao()) {
            String sql = "DELETE FROM produto WHERE id_produto = ?";
            PreparedStatement pre = conn.prepareStatement(sql);
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
        //Connection conn = new ConectaDB_Postgres().getConexao();
        try (Connection conn = new ConectaDB_Postgres().getConexao();) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM produto");
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
