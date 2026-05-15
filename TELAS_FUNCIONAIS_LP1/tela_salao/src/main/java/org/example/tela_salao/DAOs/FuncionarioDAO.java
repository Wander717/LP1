package org.example.tela_salao.DAOs;

import org.example.tela_salao.DatabaseConnection;
import org.example.tela_salao.entidades.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

    // INSERIR
    public void salvar(Funcionario funcionario) {
        String sql = "INSERT INTO funcionario (nome, sexo, cargo, idade) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getSexo());
            stmt.setString(3, funcionario.getCargo());
            stmt.setInt(4, funcionario.getIdade());

            stmt.executeUpdate();
            System.out.println("Funcionário salvo com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao salvar funcionário: " + e.getMessage());
        }
    }

    // LISTAR TODOS
    public List<Funcionario> listarTodos() {
        String sql = "SELECT * FROM funcionario";
        List<Funcionario> funcionarios = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Funcionario funcionario = new Funcionario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("sexo"),
                        rs.getString("cargo"),
                        rs.getInt("idade")
                );
                funcionarios.add(funcionario);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar funcionários: " + e.getMessage());
        }
        return funcionarios;
    }

    // ATUALIZAR
    public void atualizar(Funcionario funcionario) {
        String sql = "UPDATE funcionario SET nome = ?, sexo = ?, cargo = ?, idade = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getSexo());
            stmt.setString(3, funcionario.getCargo());
            stmt.setInt(4, funcionario.getIdade());
            stmt.setInt(5, funcionario.getId());

            stmt.executeUpdate();
            System.out.println("Funcionário atualizado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar funcionário: " + e.getMessage());
        }
    }

    // DELETAR
    public void deletar(int id) {
        String sql = "DELETE FROM funcionario WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Funcionário deletado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao deletar funcionario: " + e.getMessage());
        }
    }
}
