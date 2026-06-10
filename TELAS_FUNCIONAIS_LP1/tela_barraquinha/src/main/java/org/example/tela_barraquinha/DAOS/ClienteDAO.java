package org.example.tela_barraquinha.DAOS;

import org.example.tela_barraquinha.DataBaseConnector;
import org.example.tela_barraquinha.classes.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteDAO {

    // ── CREATE ────────────────────────────────────────────────────────────────

    public Cliente inserir(Cliente cliente) throws Exception {
        String sql = "INSERT INTO cliente (nome_cliente) VALUES (?)";

        try (Connection conn = DataBaseConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, cliente.getNome_cliente());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    cliente.setId_cliente(rs.getInt(1));
                }
            }
            return cliente;
        }
    }

    /**
     * Insere o cliente se não existir; devolve sempre o id.
     * Usado pelo PedidoDAO ao cadastrar um pedido.
     */
    public int inserirOuBuscarPorNome(String nomeCliente) throws Exception {
        String insertSql = "INSERT IGNORE INTO cliente (nome_cliente) VALUES (?)";
        String selectSql = "SELECT id_cliente FROM cliente WHERE nome_cliente = ?";

        try (Connection conn = DataBaseConnector.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(insertSql)) {
                ps.setString(1, nomeCliente);
                ps.executeUpdate();
            }
            try (PreparedStatement ps = conn.prepareStatement(selectSql)) {
                ps.setString(1, nomeCliente);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) return rs.getInt("id_cliente");
                }
            }
        }
        throw new SQLException("Não foi possível obter o id do cliente: " + nomeCliente);
    }

    // ── READ ──────────────────────────────────────────────────────────────────

    public List<Cliente> listarTodos() throws Exception {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT id_cliente, nome_cliente FROM cliente ORDER BY nome_cliente";

        try (Connection conn = DataBaseConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nome_cliente")
                ));
            }
        }
        return lista;
    }

    public Optional<Cliente> buscarPorId(int idCliente) throws Exception {
        String sql = "SELECT id_cliente, nome_cliente FROM cliente WHERE id_cliente = ?";

        try (Connection conn = DataBaseConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Cliente(
                            rs.getInt("id_cliente"),
                            rs.getString("nome_cliente")
                    ));
                }
            }
        }
        return Optional.empty();
    }

    public Optional<Cliente> buscarPorNome(String nomeCliente) throws Exception {
        String sql = "SELECT id_cliente, nome_cliente FROM cliente WHERE nome_cliente = ?";

        try (Connection conn = DataBaseConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nomeCliente);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Cliente(
                            rs.getInt("id_cliente"),
                            rs.getString("nome_cliente")
                    ));
                }
            }
        }
        return Optional.empty();
    }

    // ── UPDATE ────────────────────────────────────────────────────────────────

    public void atualizar(Cliente cliente) throws Exception {
        String sql = "UPDATE cliente SET nome_cliente = ? WHERE id_cliente = ?";

        try (Connection conn = DataBaseConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cliente.getNome_cliente());
            ps.setInt(2, cliente.getId_cliente());
            ps.executeUpdate();
        }
    }

    // ── DELETE ────────────────────────────────────────────────────────────────

    public void excluir(int idCliente) throws Exception {
        String sql = "DELETE FROM cliente WHERE id_cliente = ?";

        try (Connection conn = DataBaseConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCliente);
            ps.executeUpdate();
        }
    }
}