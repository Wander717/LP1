package org.example.tela_salao.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.tela_salao.DAOs.RegistroDAO;
import org.example.tela_salao.DAOs.FuncionarioDAO;
import org.example.tela_salao.DAOs.ProdutoDAO;
import org.example.tela_salao.entidades.Registro;
import org.example.tela_salao.entidades.Funcionario;
import org.example.tela_salao.entidades.Produto;

public class TabelaController {

    @FXML
    private TableView<Funcionario> tabelaFuncionario;

    @FXML
    private TableColumn<Funcionario, Integer> IdFuncionario;

    @FXML
    private TableColumn<Funcionario, String> NomeFuncionario;


// CLIENTE

    @FXML
    private TableView<Registro> tabelaCliente;

    @FXML
    private TableColumn<Registro, Integer> IdCliente;

    @FXML
    private TableColumn<Registro, String> NomeCliente;


// PRODUTO

    @FXML
    private TableView<Produto> tabelaProduto;

    @FXML
    private TableColumn<Produto, Integer> IdProduto;

    @FXML
    private TableColumn<Produto, String> NomeProduto;

    @FXML
    private TableColumn<Produto, String> TipoProduto;

    @FXML
    private TableColumn<Produto, Integer> QuantProduto;

    //BOTÕES
    @FXML
    private Button btn_Voltar;


    private RegistroDAO cliente1 = new RegistroDAO();
    private FuncionarioDAO funcionario1 = new FuncionarioDAO();
    private ProdutoDAO produto1 = new ProdutoDAO();


    @FXML
    public void initialize() {

        // FUNCIONÁRIO
        IdFuncionario.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );

        NomeFuncionario.setCellValueFactory(
                new PropertyValueFactory<>("nome")
        );

        // CLIENTE
        IdCliente.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );

        NomeCliente.setCellValueFactory(
                new PropertyValueFactory<>("nome")
        );

        // PRODUTO
        IdProduto.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );

        NomeProduto.setCellValueFactory(
                new PropertyValueFactory<>("nome")
        );

        TipoProduto.setCellValueFactory(
                new PropertyValueFactory<>("tipo")
        );

        QuantProduto.setCellValueFactory(
                new PropertyValueFactory<>("quantidade")
        );

        carregarTabelas();
    }


    private void carregarTabelas() {

        tabelaFuncionario.setItems(
                FXCollections.observableArrayList(
                        funcionario1.listarTodos()
                )
        );

        tabelaCliente.setItems(
                FXCollections.observableArrayList(
                        cliente1.listarTodos()
                )
        );

        tabelaProduto.setItems(
                FXCollections.observableArrayList(
                        produto1.listarTodos()
                )
        );
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