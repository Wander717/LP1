package org.example.tela_farmacia.dao;

import org.example.tela_farmacia.DatabaseConnection;
import org.example.tela_farmacia.entities.Funcionario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

    // -------------------------------------------------------------------------
    // INSERT
    // -------------------------------------------------------------------------
    public int inserir(Funcionario funcionario) throws SQLException {
        String sql = "INSERT INTO funcionario (nome_funcionario, cargo_funcionario) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, funcionario.getNome_funcionario());
            ps.setString(2, funcionario.getCargo_funcionario());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGerado = rs.getInt(1);
                    funcionario.setId_funcionario(idGerado);
                    return idGerado;
                }
            }
        }
        return -1;
    }

    // -------------------------------------------------------------------------
    // SELECT ALL
    // -------------------------------------------------------------------------
    public List<Funcionario> listarTodos() throws SQLException {
        String sql = "SELECT id_funcionario, nome_funcionario, cargo_funcionario FROM funcionario";
        List<Funcionario> lista = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Funcionario f = new Funcionario(
                        rs.getInt("id_funcionario"),
                        rs.getString("nome_funcionario"),
                        rs.getString("cargo_funcionario")
                );
                lista.add(f);
            }
        }
        return lista;
    }

    // -------------------------------------------------------------------------
    // SELECT BY ID
    // -------------------------------------------------------------------------
    public Funcionario buscarPorId(int id_funcionario) throws SQLException {
        String sql = "SELECT id_funcionario, nome_funcionario, cargo_funcionario FROM funcionario WHERE id_funcionario = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_funcionario);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Funcionario(
                            rs.getInt("id_funcionario"),
                            rs.getString("nome_funcionario"),
                            rs.getString("cargo_funcionario")
                    );
                }
            }
        }
        return null;
    }

    // -------------------------------------------------------------------------
    // SELECT BY NOME (útil para buscar pelo nome digitado no campo do FXML)
    // -------------------------------------------------------------------------
    public Funcionario buscarPorNome(String nome_funcionario) throws SQLException {
        String sql = "SELECT id_funcionario, nome_funcionario, cargo_funcionario FROM funcionario WHERE nome_funcionario = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nome_funcionario);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Funcionario(
                            rs.getInt("id_funcionario"),
                            rs.getString("nome_funcionario"),
                            rs.getString("cargo_funcionario")
                    );
                }
            }
        }
        return null;
    }

    // -------------------------------------------------------------------------
    // UPDATE
    // -------------------------------------------------------------------------
    public boolean atualizar(Funcionario funcionario) throws SQLException {
        String sql = "UPDATE funcionario SET nome_funcionario = ?, cargo_funcionario = ? WHERE id_funcionario = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, funcionario.getNome_funcionario());
            ps.setString(2, funcionario.getCargo_funcionario());
            ps.setInt(3, funcionario.getId_funcionario());

            return ps.executeUpdate() > 0;
        }
    }

    // -------------------------------------------------------------------------
    // DELETE
    // -------------------------------------------------------------------------
    public boolean deletar(int id_funcionario) throws SQLException {
        String sql = "DELETE FROM funcionario WHERE id_funcionario = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_funcionario);
            return ps.executeUpdate() > 0;
        }
    }
}