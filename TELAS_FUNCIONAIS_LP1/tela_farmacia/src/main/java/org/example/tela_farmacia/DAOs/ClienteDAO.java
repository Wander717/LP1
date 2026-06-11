package org.example.tela_farmacia.dao;

import org.example.tela_farmacia.DatabaseConnection;
import org.example.tela_farmacia.classes.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // CREATE
    public void inserir(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO cliente (nome_cliente, idade_cliente) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, cliente.getNome_cliente());
            stmt.setInt(2, cliente.getIdade_cliente());
            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    cliente.setId_cliente(keys.getInt(1));
                }
            }
        }
    }

    // READ — por ID
    public Cliente buscarPorId(int id) throws SQLException {
        String sql = "SELECT id_cliente, nome_cliente, idade_cliente FROM cliente WHERE id_cliente = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

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
    public List<Cliente> listarTodos() throws SQLException {
        String sql = "SELECT id_cliente, nome_cliente, idade_cliente FROM cliente ORDER BY nome_cliente";
        List<Cliente> lista = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    // UPDATE
    public void atualizar(Cliente cliente) throws SQLException {
        String sql = "UPDATE cliente SET nome_cliente = ?, idade_cliente = ? WHERE id_cliente = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome_cliente());
            stmt.setInt(2, cliente.getIdade_cliente());
            stmt.setInt(3, cliente.getId_cliente());
            stmt.executeUpdate();
        }
    }

    // DELETE
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM cliente WHERE id_cliente = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // MAPPER
    private Cliente mapear(ResultSet rs) throws SQLException {
        return new Cliente(
                rs.getString("nome_cliente"),
                rs.getInt("idade_cliente"),
                rs.getInt("id_cliente")
        );
    }
}