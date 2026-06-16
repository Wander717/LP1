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
    // SELECT ALL
    // -------------------------------------------------------------------------
    public List<Cliente> listarTodos() throws SQLException {
        String sql = "SELECT id_cliente, nome_cliente, idade_cliente FROM cliente";
        List<Cliente> lista = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente c = new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nome_cliente"),
                        rs.getInt("idade_cliente")
                );
                lista.add(c);
            }
        }
        return lista;
    }

    // -------------------------------------------------------------------------
    // SELECT BY ID
    // -------------------------------------------------------------------------
    public Cliente buscarPorId(int id_cliente) throws SQLException {
        String sql = "SELECT id_cliente, nome_cliente, idade_cliente FROM cliente WHERE id_cliente = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_cliente);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Cliente(
                            rs.getInt("id_cliente"),
                            rs.getString("nome_cliente"),
                            rs.getInt("idade_cliente")
                    );
                }
            }
        }
        return null;
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

    // -------------------------------------------------------------------------
    // DELETE
    // -------------------------------------------------------------------------
    public boolean deletar(int id_cliente) throws SQLException {
        String sql = "DELETE FROM cliente WHERE id_cliente = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_cliente);
            return ps.executeUpdate() > 0;
        }
    }
}