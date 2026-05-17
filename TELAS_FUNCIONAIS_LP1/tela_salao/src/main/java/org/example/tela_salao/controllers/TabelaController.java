package org.example.tela_salao.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.tela_salao.DAOs.ClienteDAO;
import org.example.tela_salao.DAOs.FuncionarioDAO;
import org.example.tela_salao.DAOs.ProdutoDAO;
import org.example.tela_salao.DTO.RegistroTabelaDTO;

public class TabelaController {

    @FXML
    private TableView<RegistroTabelaDTO> tabelaGastos;

    @FXML
    private TableColumn<RegistroTabelaDTO, Integer> colunaId;

    @FXML
    private TableColumn<RegistroTabelaDTO, String> colunaFuncionario;

    @FXML
    private TableColumn<RegistroTabelaDTO, String> colunaCliente;

    @FXML
    private TableColumn<RegistroTabelaDTO, String> colunaProduto;

    @FXML
    private TableColumn<RegistroTabelaDTO, String> colunaTipo;

    @FXML
    private TableColumn<RegistroTabelaDTO, Integer> colunaQuantidade;

    @FXML
    private Button btn_Voltar;

    @FXML
    private Button btn_Excluir;

    @FXML
    private Button btn_Editar;

    private ClienteDAO cliente = new ClienteDAO();
    private FuncionarioDAO funcionario = new FuncionarioDAO();
    private ProdutoDAO produto = new ProdutoDAO();

    @FXML
    public void initialize() {
        @FXML
        public void initialize() {

            colunaId.setCellValueFactory(
                    new PropertyValueFactory<>("id")
            );

            colunaFuncionario.setCellValueFactory(
                    new PropertyValueFactory<>("funcionario")
            );

            colunaCliente.setCellValueFactory(
                    new PropertyValueFactory<>("cliente")
            );

            colunaProduto.setCellValueFactory(
                    new PropertyValueFactory<>("produto")
            );

            colunaTipo.setCellValueFactory(
                    new PropertyValueFactory<>("tipo")
            );

            colunaQuantidade.setCellValueFactory(
                    new PropertyValueFactory<>("quantidade")
            );

            carregarTabela();
        }


    }

    private void carregarTabela() {

    }

    @FXML
    private void voltarMenu() {
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(
                    getClass().getResource("/Tela_Menu.fxml")
            );

            javafx.scene.Parent root = loader.load();

            javafx.stage.Stage stage =
                    (javafx.stage.Stage) btn_Voltar.getScene().getWindow();

            stage.setScene(new javafx.scene.Scene(root));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}