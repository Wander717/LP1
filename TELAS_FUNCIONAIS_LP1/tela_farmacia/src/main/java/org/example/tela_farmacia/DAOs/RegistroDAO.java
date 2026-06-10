package org.example.tela_farmacia.DAOs;

import org.example.tela_farmacia.classes.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistroDAO {
    private Connection conexao;

    public RegistroDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void salvarTudoNoBanco(Registro registro) throws SQLException {
        String sqlCli = "INSERT INTO cliente (nome_cliente, idade_cliente) VALUES (?, ?)";
        String sqlFun = "INSERT INTO funcionario (nome_funcionario, cargo_funcionario) VALUES (?, ?)";
        String sqlRem = "INSERT INTO remedio (nome_remedio, tipo_remedio, quantidade_remedio) VALUES (?, ?, ?)";
        String sqlReg = "INSERT INTO registros (fk_id_cliente, fk_id_funcionario, fk_id_remedio, quantidade_vendida) VALUES (?, ?, ?, ?)";

        try {
            this.conexao.setAutoCommit(false);

            int idCliente = executarEObterId(sqlCli, registro.getCliente().getNome_cliente(), registro.getCliente().getIdade_cliente());
            int idFuncionario = executarEObterId(sqlFun, registro.getFuncionario().getNome_funcionario(), registro.getFuncionario().getCargo_funcionario());
            int idRemedio = executarEObterId(sqlRem, registro.getRemedio().getNome_remedio(), registro.getRemedio().getTipo_remedio(), registro.getRemedio().getQuantidade_remedio());

            try (PreparedStatement stmt = this.conexao.prepareStatement(sqlReg)) {
                stmt.setInt(1, idCliente);
                stmt.setInt(2, idFuncionario);
                stmt.setInt(3, idRemedio);
                stmt.setInt(4, registro.getRemedio().getQuantidade_remedio());
                stmt.executeUpdate();
            }

            this.conexao.commit();
        } catch (SQLException e) {
            if (this.conexao != null) this.conexao.rollback();
            throw e; // REPASSA o erro para o Controller
        } finally {
            if (this.conexao != null) this.conexao.setAutoCommit(true);
        }
    }

    private int executarEObterId(String sql, Object... params) throws SQLException {
        try (PreparedStatement stmt = this.conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        throw new SQLException("Erro ao recuperar ID gerado.");
    }

    public List<Registro> listarTodos() throws SQLException {
        List<Registro> lista = new ArrayList<>();
        String sql = "SELECT * FROM registros " +
                "JOIN cliente ON fk_id_cliente = id_cliente " +
                "JOIN funcionario ON fk_id_funcionario = id_funcionario " +
                "JOIN remedio ON fk_id_remedio = id_remedio";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {

                int idRegistro = rs.getInt("id_registro"); // 👈 AQUI está o valor correto

                Cliente c = new Cliente(
                        rs.getString("nome_cliente"),
                        rs.getInt("idade_cliente"),
                        rs.getInt("id_cliente")
                );

                Funcionario f = new Funcionario(
                        rs.getString("nome_funcionario"),
                        rs.getString("cargo_funcionario"),
                        rs.getInt("id_funcionario")
                );

                Remedio r = new Remedio(
                        rs.getString("nome_remedio"),
                        rs.getString("tipo_remedio"),
                        rs.getInt("quantidade_remedio"),
                        rs.getInt("id_remedio")
                );

                Registro reg = new Registro(c, f, r);
                reg.setId_registro(idRegistro);

                lista.add(reg);
            }
        }
        return lista;
    }

    public void deletar(int idRegistro) throws SQLException {
        String sql = "DELETE FROM registros WHERE id_registro = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idRegistro);
            stmt.executeUpdate();
        }
    }
}