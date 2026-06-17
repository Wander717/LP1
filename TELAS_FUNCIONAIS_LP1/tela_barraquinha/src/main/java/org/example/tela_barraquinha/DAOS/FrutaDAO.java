// FrutaDAO.java
package org.example.tela_barraquinha.DAOS;

import org.example.tela_barraquinha.DataBaseConnector;

import java.sql.*;

public class FrutaDAO {

    public int inserirOuBuscarPorNome(String nomeFruta) throws Exception {
        String insertSql = "INSERT IGNORE INTO fruta (nome_fruta) VALUES (?)";
        String selectSql = "SELECT id_fruta FROM fruta WHERE nome_fruta = ?";

        try (Connection conn = DataBaseConnector.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(insertSql)) {
                ps.setString(1, nomeFruta);
                ps.executeUpdate();
            }
            try (PreparedStatement ps = conn.prepareStatement(selectSql)) {
                ps.setString(1, nomeFruta);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) return rs.getInt("id_fruta");
                }
            }
        }
        throw new SQLException("Não foi possível obter o id da fruta: " + nomeFruta);
    }
}