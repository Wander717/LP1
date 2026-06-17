package org.example.tela_farmacia.DAOs;

import org.example.tela_farmacia.DatabaseConnection;
import org.example.tela_farmacia.classes.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // -------------------------------------------------------------------------
    // INSERT — insere um novo cliente e devolve o id gerado
    // -------------------------------------------------------------------------
    public int inserir(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO cliente (nome_cliente, idade_cliente) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, cliente.getNome_cliente());
            ps.setInt(2, cliente.getIdade_cliente());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGerado = rs.getInt(1);
                    cliente.setId_cliente(idGerado);
                    return idGerado;
                }
            }
        }
        return -1;
    }

    // -------------------------------------------------------------------------
    // UPDATE
    // -------------------------------------------------------------------------
    public boolean atualizar(Cliente cliente) throws SQLException {
        String sql = "UPDATE cliente SET nome_cliente = ?, idade_cliente = ? WHERE id_cliente = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cliente.getNome_cliente());
            ps.setInt(2, cliente.getIdade_cliente());
            ps.setInt(3, cliente.getId_cliente());

            return ps.executeUpdate() > 0;
        }
    }

}