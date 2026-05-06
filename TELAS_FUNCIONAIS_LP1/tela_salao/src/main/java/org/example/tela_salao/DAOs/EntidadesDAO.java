package org.example.tela_salao.DAOs;

import org.example.tela_salao.DatabaseConnection;
import org.example.tela_salao.entidades.Salao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntidadesDAO {

    public void inserir(Salao salao) {
        String sql = "INSERT INTO registros (nome_funcionario, nome_cliente, produto, tipo_produto, quantidade_produto) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, salao.getNome_funcionario());
            stmt.setString(2, salao.getNome_cliente());
            stmt.setString(3, salao.getProduto());
            stmt.setString(4, salao.getTipo_produto());
            stmt.setInt(5, salao.getQuantidade_produto());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Salao> listar() {
        List<Salao> lista = new ArrayList<>();

        String sql = "SELECT * FROM registros";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Salao e = new Salao(
                        rs.getString("nome_funcionario"),
                        rs.getString("nome_cliente"),
                        rs.getString("produto"),
                        rs.getString("tipo_produto"),
                        rs.getInt("quantidade_produto")
                );

                lista.add(e);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }
}