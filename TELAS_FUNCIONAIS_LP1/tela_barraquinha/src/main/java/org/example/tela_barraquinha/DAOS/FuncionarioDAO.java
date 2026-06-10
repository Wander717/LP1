package org.example.tela_barraquinha.DAOS;

import org.example.tela_barraquinha.DataBaseConnector;
import org.example.tela_barraquinha.classes.Funcionario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FuncionarioDAO {

    // ── CREATE ────────────────────────────────────────────────────────────────

    public Funcionario inserir(Funcionario funcionario) throws Exception {
        String sql = "INSERT INTO funcionario (nome_funcionario) VALUES (?)";

        try (Connection conn = DataBaseConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, funcionario.getNome_funcionario());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    funcionario.setId_funcionario(rs.getInt(1));
                }
            }
            return funcionario;
        }
    }

    // ── READ ──────────────────────────────────────────────────────────────────

    public List<Funcionario> listarTodos() throws Exception {
        List<Funcionario> lista = new ArrayList<>();
        String sql = "SELECT id_funcionario, nome_funcionario FROM funcionario ORDER BY nome_funcionario";

        try (Connection conn = DataBaseConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Funcionario(
                        rs.getInt("id_funcionario"),
                        rs.getString("nome_funcionario")
                ));
            }
        }
        return lista;
    }

    public Optional<Funcionario> buscarPorId(int idFuncionario) throws Exception {
        String sql = "SELECT id_funcionario, nome_funcionario FROM funcionario WHERE id_funcionario = ?";

        try (Connection conn = DataBaseConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idFuncionario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Funcionario(
                            rs.getInt("id_funcionario"),
                            rs.getString("nome_funcionario")
                    ));
                }
            }
        }
        return Optional.empty();
    }

    public Optional<Funcionario> buscarPorNome(String nomeFuncionario) throws Exception {
        String sql = "SELECT id_funcionario, nome_funcionario FROM funcionario WHERE nome_funcionario = ?";

        try (Connection conn = DataBaseConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nomeFuncionario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Funcionario(
                            rs.getInt("id_funcionario"),
                            rs.getString("nome_funcionario")
                    ));
                }
            }
        }
        return Optional.empty();
    }

    // ── UPDATE ────────────────────────────────────────────────────────────────

    public void atualizar(Funcionario funcionario) throws Exception {
        String sql = "UPDATE funcionario SET nome_funcionario = ? WHERE id_funcionario = ?";

        try (Connection conn = DataBaseConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, funcionario.getNome_funcionario());
            ps.setInt(2, funcionario.getId_funcionario());
            ps.executeUpdate();
        }
    }

    // ── DELETE ────────────────────────────────────────────────────────────────

    public void excluir(int idFuncionario) throws Exception {
        String sql = "DELETE FROM funcionario WHERE id_funcionario = ?";

        try (Connection conn = DataBaseConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idFuncionario);
            ps.executeUpdate();
        }
    }
}