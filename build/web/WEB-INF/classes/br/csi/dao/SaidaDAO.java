package br.csi.dao;

import br.csi.model.Produto;
import br.csi.model.Saida;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SaidaDAO {

    public int create(int idProduto, int idFuncionario, String data_saida, int quantidade) {
        try (Connection conn = new ConectaDB_Postgres().getConexao()) {
            String sql = "INSERT INTO saida (idProduto, idFuncionario, data_saida, quantidade) values (?, ?, ?, ?)";
            PreparedStatement pre = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pre.setInt(1, idProduto);
            pre.setInt(2, idFuncionario);
            pre.setString(3, data_saida);
            pre.setInt(4, quantidade);
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

    public boolean create(Saida saida) {
        //conexao com o banco.
        try (Connection conn = new ConectaDB_Postgres().getConexao()) {
            String sql = "insert into saida (idProduto, idFuncionario, data_saida, quantidade) values (?, ?, ?, ?)";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, saida.getIdProduto());
            pre.setInt(2, saida.getIdFuncionario());
            pre.setString(3, saida.getData_saida());
            pre.setInt(4, saida.getQuantidade());

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
            String sql = "select * from entrada where entrada.id_produto = ?";
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

    public ArrayList<Saida> getSaida() {
        ArrayList<Saida> saida = new ArrayList<>();
        //Connection conn = new ConectaDB_Postgres().getConexao();
        try (Connection conn = new ConectaDB_Postgres().getConexao();) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM saida");
            while (rs.next()) {
                Saida sd = new Saida();
                sd.setIdProduto(rs.getInt("idProduto"));
                sd.setIdFuncionario(rs.getInt("idFuncionario"));
                sd.setData_saida(rs.getString("data_saida"));
                sd.setQuantidade(rs.getInt("quantidade"));

                saida.add(sd);
            }
            return saida;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
