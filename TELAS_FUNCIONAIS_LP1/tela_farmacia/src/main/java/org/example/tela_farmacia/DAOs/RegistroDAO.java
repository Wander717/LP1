package org.example.tela_farmacia.DAOs;

import org.example.tela_farmacia.DatabaseConnection;
import org.example.tela_farmacia.classes.Registro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistroDAO {

    // -------------------------------------------------------------------------
    // INSERT
    // -------------------------------------------------------------------------
    public int inserir(Registro registro) throws SQLException {
        String sql = "INSERT INTO registro (id_cliente, id_remedio, id_funcionario, quantidade) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, registro.getId_cliente());
            ps.setInt(2, registro.getId_remedio());
            ps.setInt(3, registro.getId_funcionario());
            ps.setInt(4, registro.getQuantidade());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGerado = rs.getInt(1);
                    registro.setId_registro(idGerado);
                    return idGerado;
                }
            }
        }
        return -1;
    }

    // -------------------------------------------------------------------------
    // SELECT ALL
    // -------------------------------------------------------------------------
    public List<Registro> listarTodos() throws SQLException {
        String sql = """
            SELECT
                r.id_registro,
                r.id_cliente,     c.nome_cliente,     c.idade_cliente,
                r.id_remedio,     rm.nome_remedio,    rm.tipo_remedio,
                r.id_funcionario, f.nome_funcionario,
                r.quantidade
            FROM registro r
            JOIN cliente     c  ON c.id_cliente     = r.id_cliente
            JOIN remedio     rm ON rm.id_remedio     = r.id_remedio
            JOIN funcionario f  ON f.id_funcionario  = r.id_funcionario
            ORDER BY r.id_registro
            """;

        List<Registro> lista = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    public Registro buscarPorId(int id_registro) throws SQLException {
        String sql = """
            SELECT
                r.id_registro,
                r.id_cliente,     c.nome_cliente,     c.idade_cliente,
                r.id_remedio,     rm.nome_remedio,    rm.tipo_remedio,
                r.id_funcionario, f.nome_funcionario,
                r.quantidade
            FROM registro r
            JOIN cliente     c  ON c.id_cliente     = r.id_cliente
            JOIN remedio     rm ON rm.id_remedio     = r.id_remedio
            JOIN funcionario f  ON f.id_funcionario  = r.id_funcionario
            WHERE r.id_registro = ?
            """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_registro);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        }
        return null;
    }


    // -------------------------------------------------------------------------
    // ATUALIZA SOMENTE A QUANTIDADE
    // -------------------------------------------------------------------------
    public void atualizarQuantidade(int id_registro, int novaQuantidade) throws SQLException {
        String sql = "UPDATE registro SET quantidade = ? WHERE id_registro = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, novaQuantidade);
            ps.setInt(2, id_registro);
            ps.executeUpdate();
        }
    }

    // -------------------------------------------------------------------------
    // DELETE
    // -------------------------------------------------------------------------
    public boolean deletar(int id_registro) throws SQLException {
        String sql = "DELETE FROM registro WHERE id_registro = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_registro);
            return ps.executeUpdate() > 0;
        }
    }

    // -------------------------------------------------------------------------
    // MAPPER — monta um Registro completo a partir do ResultSet
    // -------------------------------------------------------------------------
    private Registro mapear(ResultSet rs) throws SQLException {
        Registro reg = new Registro(
                rs.getInt("id_registro"),
                rs.getString("nome_cliente"),
                rs.getInt("idade_cliente"),
                rs.getString("nome_remedio"),
                rs.getString("tipo_remedio"),
                rs.getString("nome_funcionario"),
                rs.getInt("quantidade")
        );

        // IDs das entidades relacionadas — necessários para UPDATE via DAOs auxiliares
        reg.setId_cliente(rs.getInt("id_cliente"));
        reg.setId_remedio(rs.getInt("id_remedio"));
        reg.setId_funcionario(rs.getInt("id_funcionario"));

        return reg;
    }
}