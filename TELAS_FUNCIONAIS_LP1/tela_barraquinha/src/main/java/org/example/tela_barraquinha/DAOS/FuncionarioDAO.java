// FuncionarioDAO.java
package org.example.tela_barraquinha.DAOS;

import org.example.tela_barraquinha.DataBaseConnector;
import org.example.tela_barraquinha.classes.Funcionario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

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
}