package org.example.tela_farmacia.DAOs;

import org.example.tela_farmacia.DatabaseConnection;
import org.example.tela_farmacia.classes.Remedio;

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
    // ATUALIZA
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
}