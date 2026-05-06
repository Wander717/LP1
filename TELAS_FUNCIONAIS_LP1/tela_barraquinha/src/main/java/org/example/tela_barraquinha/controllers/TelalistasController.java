package org.example.tela_barraquinha.controllers;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.example.tela_barraquinha.FrutaDAO;
import org.example.tela_barraquinha.classes.Fruta;

public class TelalistasController {

    @FXML
    private TableView<Fruta> tabela_frutas;

    @FXML
    private TableColumn<Fruta, String> col_NomeFruta;

    @FXML
    private TableColumn<Fruta, String> col_NomeCliente;

    @FXML
    private TableColumn<Fruta, String> col_Estado;

    @FXML
    private TableColumn<Fruta, Integer> col_Quant;

    // Inicializa tabela
    @FXML
    public void initialize() {

        col_NomeFruta.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getCliente().getNome())
        );
        col_NomeCliente.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getCliente().getNome())
        );

        col_Estado.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getEstado())
        );

        col_Quant.setCellValueFactory(data ->
                new SimpleIntegerProperty(data.getValue().getQuantidade()).asObject()
        );

        carregarTabela();
    }

    // Carrega dados do banco
    public void carregarTabela() {
        try {
            FrutaDAO dao = new FrutaDAO();
            tabela_frutas.getItems().setAll(dao.listar());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void voltarMenu(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tela_menu.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    // Botão sair
    @FXML
    public void sair() {
        System.exit(0);
    }
}