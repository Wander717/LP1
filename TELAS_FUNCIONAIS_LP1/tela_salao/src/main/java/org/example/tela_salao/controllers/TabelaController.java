package org.example.tela_salao.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.tela_salao.DAOs.ClienteDAO;
import org.example.tela_salao.DAOs.FuncionarioDAO;
import org.example.tela_salao.DAOs.ProdutoDAO;

public class TabelaController {

    @FXML
    private TableView<Salao> tabelaGastos;

    @FXML
    private TableColumn<Salao, String> colunaFuncionario;

    @FXML
    private TableColumn<Salao, String> colunaCliente;

    @FXML
    private TableColumn<Salao, String> colunaProduto;

    @FXML
    private TableColumn<Salao, String> colunaTipo;

    @FXML
    private TableColumn<Salao, Integer> colunaQuantidade;

    @FXML
    private Button brnVoltar;

    private ClienteDAO cliente = new ClienteDAO();
    private FuncionarioDAO funcionario = new FuncionarioDAO();
    private ProdutoDAO produto = new ProdutoDAO();

    @FXML
    public void initialize() {

        colunaFuncionario.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getNome_funcionario()));

        colunaCliente.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getNome_cliente()));

        colunaProduto.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getProduto()));

        colunaTipo.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getTipo_produto()));

        colunaQuantidade.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(data.getValue().getQuantidade_produto()).asObject());

        carregarTabela();
    }

    private void carregarTabela() {
        ObservableList<Salao> lista =
                FXCollections.observableArrayList(dao.listar());

        tabelaGastos.setItems(lista);
    }

    @FXML
    private void VoltarMenu() {
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(
                    getClass().getResource("/Tela_Menu.fxml")
            );

            javafx.scene.Parent root = loader.load();

            javafx.stage.Stage stage =
                    (javafx.stage.Stage) brnVoltar.getScene().getWindow();

            stage.setScene(new javafx.scene.Scene(root));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}