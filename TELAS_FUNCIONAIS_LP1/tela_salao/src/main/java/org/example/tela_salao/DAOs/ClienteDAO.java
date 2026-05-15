package org.example.tela_salao.DAOs;

import org.example.tela_salao.DatabaseConnection;
import org.example.tela_salao.entidades.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    //INSERIR
    public void salvar(Cliente cliente) {
        String sql = "INSERT INTO cliente (nome, sexo, idade) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getSexo());
            stmt.setInt(3, cliente.getIdade());

            stmt.executeUpdate();
            System.out.println("Cliente inserido com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao Salvar o Cliente: " + e.getMessage());
        }
    }

    // LISTAR TODOS
    public List<Cliente> listarTodos() {
        String sql = "SELECT * FROM cliente";
        List<Cliente> clientes = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             // O executeQuery() roda o SELECT e guarda o resultado no rs
             ResultSet rs = stmt.executeQuery()) {

            // Enquanto houver uma próxima linha no resultado...
            while (rs.next()) {
                // Criamos um objeto Cliente temporário e preenchemos com os dados do banco
                Cliente cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("sexo"),
                        rs.getInt("idade")
                );
                // Adicionamos esse cliente na nossa lista
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar os clientes: " + e.getMessage());
        }
        return clientes;
    }

    // EDITAR (ATUALIZAR)
    public void atualizar(Cliente cliente) {
        // O WHERE id = ? garante que você só vai alterar o cliente certo
        String sql = "UPDATE cliente SET nome = ?, sexo = ?, idade = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getSexo());
            stmt.setInt(3, cliente.getIdade());
            stmt.setInt(4, cliente.getId()); // Passa o ID para a 4ª interrogação

            stmt.executeUpdate();
            System.out.println("Cliente atualizado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o cliente: " + e.getMessage());
        }
    }

    // EXCLUIR (DELETAR)
    public void deletar(int id) {
        String sql = "DELETE FROM cliente WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id); // Passa o ID recebido para a interrogação

            stmt.executeUpdate();
            System.out.println("Cliente deletado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao deletar o cliente: " + e.getMessage());
        }
    }


}
