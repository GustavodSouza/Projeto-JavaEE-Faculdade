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
    public int create(int idProduto, int idFuncionario, String data_entrada, int quantidade) {
        try (Connection conn = new ConectaDB_Postgres().getConexao()) {
            String sql = "INSERT INTO entrada (idProduto, idFuncionario, data_entrada, quantidade) values (?, ?, ?, ?)";
            PreparedStatement pre = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pre.setInt(1, idProduto);
            pre.setInt(2, idFuncionario);
            pre.setString(3, data_entrada);
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

    public boolean create(Entrada entrada) {
        //conexao com o banco.
        try (Connection conn = new ConectaDB_Postgres().getConexao()) {
            String sql = "insert into entrada (idProduto, idFuncionario, data_entrada, quantidade) values (?, ?, ?, ?)";
            PreparedStatement pre = conn.prepareStatement(sql);
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

    public ArrayList<Entrada> getEntrada() {
        ArrayList<Entrada> entrada = new ArrayList<>();
        //Connection conn = new ConectaDB_Postgres().getConexao();
        try (Connection conn = new ConectaDB_Postgres().getConexao();) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM entrada");
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
