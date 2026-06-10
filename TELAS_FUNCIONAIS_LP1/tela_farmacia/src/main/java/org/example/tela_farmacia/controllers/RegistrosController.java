package org.example.tela_farmacia.controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.tela_farmacia.DatabaseConnection;
import org.example.tela_farmacia.classes.*;
import org.example.tela_farmacia.DAOs.RegistroDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RegistrosController {

    @FXML
    private TableView<Registro> tabela_Registros;

    @FXML
    private TableColumn<Registro, Integer> col_IdRegistro;

    @FXML
    private TableColumn<Registro, String> col_NomeCliente;

    @FXML
    private TableColumn<Registro, Integer> col_IdadeCliente;

    @FXML
    private TableColumn<Registro, String> col_NomeRemedio;

    @FXML
    private TableColumn<Registro, String> col_TipoRemedio;

    @FXML
    private TableColumn<Registro, Integer> col_QuantRemedio;

    @FXML
    private TableColumn<Registro, String> col_NomeFuncionario;

    @FXML
    private Button btn_VoltarMenu;

    @FXML
    private Button btn_ExcluirSelecionado;

    @FXML
    private Button btn_EditarSelecionado;

    @FXML
    public void initialize() {
        // Agora a tabela se prepara sozinha ao abrir
        configurarColunas();
        carregarDados();
    }

    private void configurarColunas() {
        // Mapeamento das colunas
        col_IdRegistro.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId_registro()).asObject());

        col_NomeCliente.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getCliente().getNome_cliente()));

        col_IdadeCliente.setCellValueFactory(data ->
                new SimpleIntegerProperty(data.getValue().getCliente().getIdade_cliente()).asObject());

        col_NomeRemedio.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getRemedio().getNome_remedio()));

        col_TipoRemedio.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getRemedio().getTipo_remedio()));

        col_QuantRemedio.setCellValueFactory(data ->
                new SimpleIntegerProperty(data.getValue().getRemedio().getQuantidade_remedio()).asObject());

        col_NomeFuncionario.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getFuncionario().getNome_funcionario()));
    }

    private void carregarDados() {
        try (Connection conexao = DatabaseConnection.getConnection()) {
            RegistroDAO dao = new RegistroDAO(conexao);
            List<Registro> lista = dao.listarTodos();
            ObservableList<Registro> obsLista = FXCollections.observableArrayList(lista);
            tabela_Registros.setItems(obsLista);
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Erro", "Erro ao carregar dados: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void excluirLinhaSelecionada() {
        Registro selecionado = tabela_Registros.getSelectionModel().getSelectedItem();

        if (selecionado != null) {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Confirmar Exclusão");
            alerta.setContentText("Deseja realmente excluir o registro do cliente " + selecionado.getCliente().getNome_cliente() + "?");

            if (alerta.showAndWait().get() == ButtonType.OK) {
                try (Connection conexao = DatabaseConnection.getConnection()) {
                    RegistroDAO dao = new RegistroDAO(conexao);
                    dao.deletar(selecionado.getId_registro());

                    carregarDados(); // Atualiza a tabela
                    mostrarAlerta("Sucesso", "Registro excluído com sucesso!", Alert.AlertType.INFORMATION);
                } catch (SQLException e) {
                    mostrarAlerta("Erro", "Erro ao excluir: " + e.getMessage(), Alert.AlertType.ERROR);
                }
            }
        } else {
            mostrarAlerta("Aviso", "Por favor, selecione um registro na tabela primeiro!", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void editarLinhaSelecionada() {
        Registro selecionado = tabela_Registros.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            // Lógica para abrir tela de edição ou preencher campos
            System.out.println("Editando registro ID: " + selecionado.getId_registro());
        } else {
            mostrarAlerta("Aviso", "Selecione um registro para editar!", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void voltarMenu() {
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("/Tela_Menu.fxml"));
            javafx.scene.Parent root = loader.load();
            javafx.stage.Stage stage = (javafx.stage.Stage) btn_VoltarMenu.getScene().getWindow();
            stage.setScene(new javafx.scene.Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método único para alertas, facilitando o código
    private void mostrarAlerta(String titulo, String msg, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}