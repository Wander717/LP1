package org.example.tela_farmacia.dao;

import org.example.tela_farmacia.DatabaseConnection;
import org.example.tela_farmacia.entities.Remedio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RemedioDAO {

    // -------------------------------------------------------------------------
    // INSERT
    // -------------------------------------------------------------------------
    public int inserir(Remedio remedio) throws SQLException {
        String sql = "INSERT INTO remedio (nome_remedio, tipo_remedio) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, remedio.getNome_remedio());
            ps.setString(2, remedio.getTipo_remedio());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGerado = rs.getInt(1);
                    remedio.setId_remedio(idGerado);
                    return idGerado;
                }
            }
        }
        return -1;
    }

    // -------------------------------------------------------------------------
    // SELECT ALL
    // -------------------------------------------------------------------------
    public List<Remedio> listarTodos() throws SQLException {
        String sql = "SELECT id_remedio, nome_remedio, tipo_remedio FROM remedio";
        List<Remedio> lista = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Remedio r = new Remedio(
                        rs.getInt("id_remedio"),
                        rs.getString("nome_remedio"),
                        rs.getString("tipo_remedio")
                );
                lista.add(r);
            }
        }
        return lista;
    }

    // -------------------------------------------------------------------------
    // SELECT BY ID
    // -------------------------------------------------------------------------
    public Remedio buscarPorId(int id_remedio) throws SQLException {
        String sql = "SELECT id_remedio, nome_remedio, tipo_remedio FROM remedio WHERE id_remedio = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_remedio);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Remedio(
                            rs.getInt("id_remedio"),
                            rs.getString("nome_remedio"),
                            rs.getString("tipo_remedio")
                    );
                }
            }
        }
        return null;
    }

    // -------------------------------------------------------------------------
    // UPDATE
    // -------------------------------------------------------------------------
    public boolean atualizar(Remedio remedio) throws SQLException {
        String sql = "UPDATE remedio SET nome_remedio = ?, tipo_remedio = ? WHERE id_remedio = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, remedio.getNome_remedio());
            ps.setString(2, remedio.getTipo_remedio());
            ps.setInt(3, remedio.getId_remedio());

            return ps.executeUpdate() > 0;
        }
    }

    // -------------------------------------------------------------------------
    // DELETE
    // -------------------------------------------------------------------------
    public boolean deletar(int id_remedio) throws SQLException {
        String sql = "DELETE FROM remedio WHERE id_remedio = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_remedio);
            return ps.executeUpdate() > 0;
        }
    }
}