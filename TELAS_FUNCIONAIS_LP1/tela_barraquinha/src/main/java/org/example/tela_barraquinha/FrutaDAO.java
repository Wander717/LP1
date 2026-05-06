package org.example.tela_barraquinha;

import org.example.tela_barraquinha.classes.Cliente;
import org.example.tela_barraquinha.classes.Fruta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FrutaDAO {

    public void inserir(Fruta fruta) throws Exception {
        String sql = "INSERT INTO frutas (nome_cliente, nomeFruta, estado, quantidade) VALUES (?, ?, ?, ?)";

        Connection conn = DataBaseConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, fruta.getCliente().getNome());
        stmt.setString(2, fruta.getNomeFruta());
        stmt.setString(3, fruta.getEstado());
        stmt.setInt(4, fruta.getQuantidade());

        stmt.execute();
        conn.close();
    }

    public List<Fruta> listar() throws Exception {
        List<Fruta> lista = new ArrayList<>();

        String sql = "SELECT nome_cliente, nomeFruta, estado, quantidade FROM frutas";

        Connection conn = DataBaseConnector.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {

            Cliente cliente = new Cliente(rs.getString("nome_cliente"));

            Fruta fruta = new Fruta(
                    cliente,
                    rs.getString("nomeFruta"),
                    rs.getString("estado"),
                    rs.getInt("quantidade")
            );

            lista.add(fruta);
        }

        conn.close();
        return lista;
    }
}