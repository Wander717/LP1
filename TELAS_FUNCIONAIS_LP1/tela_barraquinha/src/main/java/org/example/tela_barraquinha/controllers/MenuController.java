package org.example.tela_barraquinha.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.tela_barraquinha.classes.Funcionario;
import org.example.tela_barraquinha.DAOS.FuncionarioDAO;
import org.example.tela_barraquinha.DAOS.PedidoDAO;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML private TextField             txtNomeCliente;
    @FXML private TextField             txtNomeFruta;
    @FXML private Spinner<Integer>      spn_quant_fruta;
    @FXML private ComboBox<Funcionario> cbFuncionario;

    private final PedidoDAO      pedidoDAO      = new PedidoDAO();
    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    // ── Inicialização ─────────────────────────────────────────────────────────

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Spinner: mínimo 1, máximo 9999, valor inicial 1
        spn_quant_fruta.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 9999, 1)
        );

        carregarFuncionarios();
    }

    private void carregarFuncionarios() {
        try {
            List<Funcionario> lista = funcionarioDAO.listarTodos();
            cbFuncionario.setItems(FXCollections.observableArrayList(lista));
        } catch (Exception e) {
            alerta(Alert.AlertType.ERROR, "Erro ao carregar funcionários", e.getMessage());
        }
    }

    // ── Ações dos botões ──────────────────────────────────────────────────────

    @FXML
    private void CadastrarTudo() {
        String nomeCliente      = txtNomeCliente.getText().trim();
        String nomeFruta        = txtNomeFruta.getText().trim();
        int    quantidade       = spn_quant_fruta.getValue();
        Funcionario funcionario = cbFuncionario.getValue();

        // Validações
        if (nomeCliente.isEmpty()) {
            alerta(Alert.AlertType.WARNING, "Campo obrigatório", "Informe o nome do cliente.");
            txtNomeCliente.requestFocus();
            return;
        }
        if (nomeFruta.isEmpty()) {
            alerta(Alert.AlertType.WARNING, "Campo obrigatório", "Informe o nome da fruta.");
            txtNomeFruta.requestFocus();
            return;
        }
        if (funcionario == null) {
            alerta(Alert.AlertType.WARNING, "Campo obrigatório", "Selecione o funcionário que fez o atendimento.");
            cbFuncionario.requestFocus();
            return;
        }

        // Persistência
        try {
            pedidoDAO.inserirPedido(nomeCliente, nomeFruta,
                    funcionario.getId_funcionario(), quantidade);
            alerta(Alert.AlertType.INFORMATION, "Sucesso", "Pedido cadastrado com sucesso!");
            limparCampos();
        } catch (Exception e) {
            alerta(Alert.AlertType.ERROR, "Erro ao cadastrar pedido", e.getMessage());
        }
    }

    @FXML
    private void irParaLista() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/tela_listafrutas.fxml"));
            Stage stage = (Stage) txtNomeCliente.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Barraquinha do Seu Zé — Pedidos");
        } catch (IOException e) {
            alerta(Alert.AlertType.ERROR, "Erro de navegação",
                    "Não foi possível abrir a lista: " + e.getMessage());
        }
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    private void limparCampos() {
        txtNomeCliente.clear();
        txtNomeFruta.clear();
        spn_quant_fruta.getValueFactory().setValue(1);
        cbFuncionario.setValue(null);
    }

    private void alerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}