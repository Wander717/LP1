package org.example.tela_barraquinha.DAOS;

import org.example.tela_barraquinha.DAOS.ClienteDAO;
import org.example.tela_barraquinha.DAOS.FrutaDAO;
import org.example.tela_barraquinha.DataBaseConnector;
import org.example.tela_barraquinha.classes.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    private final ClienteDAO clienteDAO     = new ClienteDAO();
    private final FrutaDAO       frutaDAO       = new FrutaDAO();

    // ── CREATE ────────────────────────────────────────────────────────────────

    public void inserirPedido(String nomeCliente, String nomeFruta,
                              int idFuncionario, int quantidade) throws Exception {

        int idCliente = clienteDAO.inserirOuBuscarPorNome(nomeCliente);
        int idFruta   = frutaDAO.inserirOuBuscarPorNome(nomeFruta);

        String sql = "INSERT INTO pedido (id_cliente, id_fruta, id_funcionario, quantidade) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conn = DataBaseConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCliente);
            ps.setInt(2, idFruta);
            ps.setInt(3, idFuncionario);
            ps.setInt(4, quantidade);
            ps.executeUpdate();
        }
    }

    // ── READ ──────────────────────────────────────────────────────────────────

    public List<Pedido> listarPedidos() throws Exception {
        List<Pedido> lista = new ArrayList<>();
        String sql = """
                SELECT p.id_pedido,
                       c.nome_cliente,
                       f.nome_fruta,
                       fn.nome_funcionario,
                       p.quantidade
                FROM pedido p
                JOIN cliente     c  ON c.id_cliente      = p.id_cliente
                JOIN fruta       f  ON f.id_fruta         = p.id_fruta
                JOIN funcionario fn ON fn.id_funcionario  = p.id_funcionario
                ORDER BY p.id_pedido
                """;

        try (Connection conn = DataBaseConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Pedido(
                        rs.getInt("id_pedido"),
                        rs.getString("nome_cliente"),
                        rs.getString("nome_fruta"),
                        rs.getString("nome_funcionario"),
                        rs.getInt("quantidade")
                ));
            }
        }
        return lista;
    }

    // ── UPDATE ────────────────────────────────────────────────────────────────

    public void atualizarPedido(Pedido pedido) throws Exception {
        int idCliente = clienteDAO.inserirOuBuscarPorNome(pedido.getNome_cliente());
        int idFruta   = frutaDAO.inserirOuBuscarPorNome(pedido.getNome_fruta());

        String sql = "UPDATE pedido SET id_cliente=?, id_fruta=?, id_funcionario=?, quantidade=? "
                + "WHERE id_pedido=?";

        try (Connection conn = DataBaseConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCliente);
            ps.setInt(2, idFruta);
            ps.setInt(3, pedido.getId_funcionario());
            ps.setInt(4, pedido.getQuantidade());
            ps.setInt(5, pedido.getId_pedido());
            ps.executeUpdate();
        }
    }

    // ── DELETE ────────────────────────────────────────────────────────────────

    public void excluirPedido(int idPedido) throws Exception {
        String sql = "DELETE FROM pedido WHERE id_pedido = ?";

        try (Connection conn = DataBaseConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idPedido);
            ps.executeUpdate();
        }
    }
}