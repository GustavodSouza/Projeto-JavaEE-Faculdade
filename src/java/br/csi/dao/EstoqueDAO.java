package br.csi.dao;

import br.csi.model.Estoque;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EstoqueDAO {
    public int create(int idProduto, int quantidade) {
        try (Connection conn = new ConectaDB_Postgres().getConexao()) {
            String sql = "INSERT INTO estoque (idProduto, quantidade) values (?, ?)";
            PreparedStatement pre = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pre.setInt(1, idProduto);
            pre.setInt(2, quantidade);
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

    public boolean create(Estoque estoque) {
        //conexao com o banco.
        try (Connection conn = new ConectaDB_Postgres().getConexao()) {
            String sql = "insert into estoque (idProduto, quantidade) values (?, ?)";
            PreparedStatement pre = conn.prepareStatement(sql);
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
        try (Connection conn = new ConectaDB_Postgres().getConexao()) {
            String sql = "select * from estoque where estoque.idProduto = ?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, idProduto);

            ResultSet rs = pre.executeQuery();

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
        try (Connection conn = new ConectaDB_Postgres().getConexao()) {
            String sql = "UPDATE estoque SET quantidade = quantidade + ? WHERE idProduto = ?";
            PreparedStatement pre = conn.prepareStatement(sql);
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
        try (Connection conn = new ConectaDB_Postgres().getConexao()) {
            String sql = "UPDATE estoque SET quantidade = quantidade - ? WHERE idProduto = ?";
            PreparedStatement pre = conn.prepareStatement(sql);
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

    public ArrayList<Estoque> getEstoque() {
        ArrayList<Estoque> estoque = new ArrayList<>();
        //Connection conn = new ConectaDB_Postgres().getConexao();
        try (Connection conn = new ConectaDB_Postgres().getConexao();) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM estoque");
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
