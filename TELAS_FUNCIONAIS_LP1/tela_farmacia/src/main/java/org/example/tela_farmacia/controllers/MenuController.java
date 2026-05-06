package org.example.tela_farmacia.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.tela_farmacia.DatabaseConnection;
import org.example.tela_farmacia.classes.*;
import org.example.tela_farmacia.DAOs.RegistroDAO;
import java.sql.Connection;
import java.sql.SQLException;

public class MenuController {
    private RegistroDAO registroDAO = null;

    @FXML private TextField txt_NomeFuncionario;
    @FXML private TextField txt_NomeRemedio;
    @FXML private TextField txt_TipoRemedio;
    @FXML private Spinner<Integer> spi_QuantRemedio;
    @FXML private TextField txt_NomeCliente;
    @FXML private TextField txt_IdadeCliente;
    @FXML private Button btn_Consultar;

    @FXML
    public void initialize() {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 1);
        spi_QuantRemedio.setValueFactory(valueFactory);

        try {
            Connection conexao = DatabaseConnection.getConnection();
            if (conexao != null) {
                this.registroDAO = new RegistroDAO(conexao);
            }
        } catch (SQLException e) {
            System.err.println("Erro de conexão: " + e.getMessage());
        }
    }

    @FXML
    public void registrarTudo() {
        try {
            if (registroDAO == null) {
                mostrarAlerta("Erro", "Banco de dados não conectado.", Alert.AlertType.ERROR);
                return;
            }

            Cliente cliente = new Cliente(txt_NomeCliente.getText(), Integer.parseInt(txt_IdadeCliente.getText()), 0);
            Funcionario funcionario = new Funcionario(txt_NomeFuncionario.getText(), "Atendente", 0);
            Remedio remedio = new Remedio(txt_NomeRemedio.getText(), txt_TipoRemedio.getText(), spi_QuantRemedio.getValue(), 0);

            registroDAO.salvarTudoNoBanco(new Registro(cliente, funcionario, remedio));

            mostrarAlerta("Sucesso", "Dados salvos com sucesso!", Alert.AlertType.INFORMATION);
            limparCampos();

        } catch (NumberFormatException e) {
            mostrarAlerta("Erro", "Idade inválida.", Alert.AlertType.WARNING);

        } catch (Exception e) {  // 👈 pega tudo (inclui SQLException)
            mostrarAlerta("Erro", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void consultarTabela() {
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("/Tela_Registros.fxml"));
            javafx.scene.Parent root = loader.load();
            javafx.stage.Stage stage = (javafx.stage.Stage) btn_Consultar.getScene().getWindow();
            stage.setScene(new javafx.scene.Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void limparCampos() {
        txt_NomeFuncionario.clear();
        txt_NomeRemedio.clear();
        txt_TipoRemedio.clear();
        txt_NomeCliente.clear();
        txt_IdadeCliente.clear();
        spi_QuantRemedio.getValueFactory().setValue(1);
    }

    private void mostrarAlerta(String titulo, String msg, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}