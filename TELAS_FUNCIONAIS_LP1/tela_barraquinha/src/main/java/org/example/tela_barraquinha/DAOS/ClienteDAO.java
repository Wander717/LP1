// ClienteDAO.java
package org.example.tela_barraquinha.DAOS;

import org.example.tela_barraquinha.DataBaseConnector;
import org.example.tela_barraquinha.classes.Cliente;

import java.sql.*;

public class ClienteDAO {

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
}