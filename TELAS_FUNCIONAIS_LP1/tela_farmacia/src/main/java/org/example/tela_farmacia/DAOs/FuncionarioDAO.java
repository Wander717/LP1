package org.example.tela_farmacia.DAOs;

import org.example.tela_farmacia.DatabaseConnection;
import org.example.tela_farmacia.classes.Funcionario;

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
}