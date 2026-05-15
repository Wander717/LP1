package org.example.tela_salao.DAOs;

import org.example.tela_salao.DatabaseConnection;
import org.example.tela_salao.entidades.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    // INSERIR
    public void salvar(Produto produto) {
        String sql = "INSERT INTO produto (nome, tipo, quantidade) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getTipo());
            stmt.setInt(3, produto.getQuantidade());

            stmt.executeUpdate();
            System.out.println("Produto salvo com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao salvar produto: " + e.getMessage());
        }
    }

    // LISTAR TODOS
    public List<Produto> listarTodos() {
        String sql = "SELECT * FROM produto";
        List<Produto> produtos = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Produto produto = new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("tipo"),
                        rs.getInt("quantidade")
                );
                produtos.add(produto);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar produtos: " + e.getMessage());
        }
        return produtos;
    }

    // ATUALIZAR
    public void atualizar(Produto produto) {
        String sql = "UPDATE produto SET nome = ?, tipo = ?, quantidade = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getTipo());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setInt(4, produto.getId());

            stmt.executeUpdate();
            System.out.println("Produto atualizado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar produto: " + e.getMessage());
        }
    }

    // DELETAR
    public void deletar(int id) {
        String sql = "DELETE FROM produto WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Produto deletado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao deletar produto: " + e.getMessage());
        }
    }
}