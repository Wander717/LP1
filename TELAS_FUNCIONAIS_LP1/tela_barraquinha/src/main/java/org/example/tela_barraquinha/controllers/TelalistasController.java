package org.example.tela_barraquinha.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import org.example.tela_barraquinha.classes.Pedido;
import org.example.tela_barraquinha.DAOS.PedidoDAO;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class TelalistasController implements Initializable {

    @FXML private TableView<Pedido>            tabela_frutas;
    @FXML private TableColumn<Pedido, Integer> colIdPedido;
    @FXML private TableColumn<Pedido, String>  colNomeCliente;
    @FXML private TableColumn<Pedido, String>  colNomeFruta;
    @FXML private TableColumn<Pedido, String>  colFuncionario;
    @FXML private TableColumn<Pedido, Integer> colQuant;

    private final PedidoDAO pedidoDAO = new PedidoDAO();
    private ObservableList<Pedido> dadosTabela;

    // ── Inicialização ─────────────────────────────────────────────────────────

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarColunas();
        carregarDados();
    }

    private void configurarColunas() {
        // PropertyValueFactory usa o nome do getter sem "get" e com 1ª letra minúscula.
        // Ex: getId_pedido() → "id_pedido"
        colIdPedido.setCellValueFactory(new PropertyValueFactory<>("id_pedido"));

        // Cliente — editável com duplo clique
        colNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nome_cliente"));
        colNomeCliente.setCellFactory(TextFieldTableCell.forTableColumn());
        colNomeCliente.setOnEditCommit(e -> e.getRowValue().setNome_cliente(e.getNewValue()));

        // Fruta — editável com duplo clique
        colNomeFruta.setCellValueFactory(new PropertyValueFactory<>("nome_fruta"));
        colNomeFruta.setCellFactory(TextFieldTableCell.forTableColumn());
        colNomeFruta.setOnEditCommit(e -> e.getRowValue().setNome_fruta(e.getNewValue()));

        // Funcionário — somente leitura
        colFuncionario.setCellValueFactory(new PropertyValueFactory<>("nome_funcionario"));

        // Quantidade — editável com duplo clique
        colQuant.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colQuant.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colQuant.setOnEditCommit(e -> e.getRowValue().setQuantidade(e.getNewValue()));

        tabela_frutas.setEditable(true);
    }

    private void carregarDados() {
        try {
            dadosTabela = FXCollections.observableArrayList(pedidoDAO.listarPedidos());
            tabela_frutas.setItems(dadosTabela);
        } catch (Exception e) {
            alerta(Alert.AlertType.ERROR, "Erro ao carregar dados", e.getMessage());
        }
    }

    // ── Ações dos botões ──────────────────────────────────────────────────────

    @FXML
    private void salvarTudo() {
        if (dadosTabela == null || dadosTabela.isEmpty()) return;

        try {
            for (Pedido p : dadosTabela) {
                pedidoDAO.atualizarPedido(p);
            }
            alerta(Alert.AlertType.INFORMATION, "Salvo", "Todos os pedidos foram atualizados!");
        } catch (Exception e) {
            alerta(Alert.AlertType.ERROR, "Erro ao salvar", e.getMessage());
        }
    }

    @FXML
    private void excluirSelecionado() {
        Pedido selecionado = tabela_frutas.getSelectionModel().getSelectedItem();

        if (selecionado == null) {
            alerta(Alert.AlertType.WARNING, "Nenhum pedido selecionado",
                    "Clique em um pedido na tabela antes de excluir.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirmar exclusão");
        confirm.setHeaderText(null);
        confirm.setContentText("Excluir o pedido #" + selecionado.getId_pedido()
                + " de " + selecionado.getNome_cliente() + "?");

        Optional<ButtonType> resposta = confirm.showAndWait();
        if (resposta.isPresent() && resposta.get() == ButtonType.OK) {
            try {
                pedidoDAO.excluirPedido(selecionado.getId_pedido());
                dadosTabela.remove(selecionado);
            } catch (Exception e) {
                alerta(Alert.AlertType.ERROR, "Erro ao excluir", e.getMessage());
            }
        }
    }

    @FXML
    private void editarSelecionado() {
        int index = tabela_frutas.getSelectionModel().getSelectedIndex();

        if (index < 0) {
            alerta(Alert.AlertType.WARNING, "Nenhum pedido selecionado",
                    "Clique em um pedido para editar.");
            return;
        }
        // Abre edição inline na coluna Cliente
        tabela_frutas.edit(index, colNomeCliente);
    }

    @FXML
    private void voltarMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/tela_menu.fxml"));
            Stage stage = (Stage) tabela_frutas.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Barraquinha do Seu Zé");
        } catch (IOException e) {
            alerta(Alert.AlertType.ERROR, "Erro de navegação",
                    "Não foi possível voltar ao menu: " + e.getMessage());
        }
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    private void alerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}