package org.example.tela_farmacia.dao;

import org.example.tela_farmacia.classes.Funcionario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

    private Connection connection;

    public FuncionarioDAO(Connection connection) {
        this.connection = connection;
    }

    // CREATE
    public void inserir(Funcionario funcionario) throws SQLException {
        String sql = "INSERT INTO funcionario (nome_funcionario, cargo_funcionario) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, funcionario.getNome_funcionario());
            stmt.setString(2, funcionario.getCargo_funcionario());
            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    funcionario.setId_funcionario(keys.getInt(1));
                }
            }
        }
    }

    // READ — por ID
    public Funcionario buscarPorId(int id) throws SQLException {
        String sql = "SELECT id_funcionario, nome_funcionario, cargo_funcionario FROM funcionario WHERE id_funcionario = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        }
        return null;
    }

    // READ — todos
    public List<Funcionario> listarTodos() throws SQLException {
        String sql = "SELECT id_funcionario, nome_funcionario, cargo_funcionario FROM funcionario ORDER BY nome_funcionario";
        List<Funcionario> lista = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    // UPDATE
    public void atualizar(Funcionario funcionario) throws SQLException {
        String sql = "UPDATE funcionario SET nome_funcionario = ?, cargo_funcionario = ? WHERE id_funcionario = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, funcionario.getNome_funcionario());
            stmt.setString(2, funcionario.getCargo_funcionario());
            stmt.setInt(3, funcionario.getId_funcionario());
            stmt.executeUpdate();
        }
    }

    // DELETE
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM funcionario WHERE id_funcionario = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // MAPPER
    private Funcionario mapear(ResultSet rs) throws SQLException {
        return new Funcionario(
                rs.getString("nome_funcionario"),
                rs.getString("cargo_funcionario"),
                rs.getInt("id_funcionario")
        );
    }
}