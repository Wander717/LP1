package org.example.tela_farmacia.dao;

import org.example.tela_farmacia.classes.Remedio;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RemedioDAO {

    private Connection connection;

    public RemedioDAO(Connection connection) {
        this.connection = connection;
    }

    // CREATE
    public void inserir(Remedio remedio) throws SQLException {
        String sql = "INSERT INTO remedio (nome_remedio, tipo_remedio) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, remedio.getNome_remedio());
            stmt.setString(2, remedio.getTipo_remedio());
            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    remedio.setId_remedio(keys.getInt(1));
                }
            }
        }
    }

    // READ — por ID
    public Remedio buscarPorId(int id) throws SQLException {
        String sql = "SELECT id_remedio, nome_remedio, tipo_remedio FROM remedio WHERE id_remedio = ?";

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
    public List<Remedio> listarTodos() throws SQLException {
        String sql = "SELECT id_remedio, nome_remedio, tipo_remedio FROM remedio ORDER BY nome_remedio";
        List<Remedio> lista = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    // UPDATE
    public void atualizar(Remedio remedio) throws SQLException {
        String sql = "UPDATE remedio SET nome_remedio = ?, tipo_remedio = ? WHERE id_remedio = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, remedio.getNome_remedio());
            stmt.setString(2, remedio.getTipo_remedio());
            stmt.setInt(3, remedio.getId_remedio());
            stmt.executeUpdate();
        }
    }

    // DELETE
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM remedio WHERE id_remedio = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // MAPPER
    private Remedio mapear(ResultSet rs) throws SQLException {
        return new Remedio(
                rs.getString("nome_remedio"),
                rs.getString("tipo_remedio"),
                rs.getInt("id_remedio")
        );
    }
}