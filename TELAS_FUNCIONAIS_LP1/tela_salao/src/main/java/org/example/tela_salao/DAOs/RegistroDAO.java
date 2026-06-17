package org.example.tela_salao.DAOs;

import org.example.tela_salao.DatabaseConnection;
import org.example.tela_salao.entidades.Registro;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegistroDAO {

    //INSERE O REGISTRO NA TABELA
    public void inserir(Registro registro) {
        String sql = "INSERT INTO registro (data_registro, id_produto, id_funcionario) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setDate(1, registro.getDataRegistro() != null
                    ? Date.valueOf(registro.getDataRegistro()) : null);
            stmt.setInt(2, registro.getIdProduto());
            stmt.setInt(3, registro.getIdFuncionario());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    registro.setIdRegistro(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir registro: " + e.getMessage(), e);
        }
    }

    //BUSCA POR ID
    public Registro buscarPorId(int idRegistro) {
        String sql = "SELECT * FROM registro WHERE id_registro = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idRegistro);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar registro: " + e.getMessage(), e);
        }

        return null;
    }

    //LISTA TODOS OS REGISTROS
    public List<Registro> listarTodos() {
        String sql = "SELECT * FROM registro";
        List<Registro> registros = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                registros.add(mapear(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar registros: " + e.getMessage(), e);
        }

        return registros;
    }

    //LISTA POR FUNCIONÁRIO
    public List<Registro> listarPorFuncionario(int idFuncionario) {
        String sql = "SELECT * FROM registro WHERE id_funcionario = ?";
        List<Registro> registros = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFuncionario);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    registros.add(mapear(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar registros por funcionário: " + e.getMessage(), e);
        }

        return registros;
    }

    //LISTA POR PRODUTO
    public List<Registro> listarPorProduto(int idProduto) {
        String sql = "SELECT * FROM registro WHERE id_produto = ?";
        List<Registro> registros = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProduto);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    registros.add(mapear(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar registros por produto: " + e.getMessage(), e);
        }

        return registros;
    }

    //EXCLUI REGISTRO
    public void deletar(int idRegistro) {
        String sql = "DELETE FROM registro WHERE id_registro = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idRegistro);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar registro: " + e.getMessage(), e);
        }
    }

    private Registro mapear(ResultSet rs) throws SQLException {
        Date dataSql = rs.getDate("data_registro");
        LocalDate data = dataSql != null ? dataSql.toLocalDate() : null;

        return new Registro(
                rs.getInt("id_registro"),
                data,
                rs.getInt("id_produto"),
                rs.getInt("id_funcionario")
        );
    }
}
