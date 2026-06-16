package org.example.tela_farmacia.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.tela_farmacia.DAOs.ClienteDAO;
import org.example.tela_farmacia.DAOs.FuncionarioDAO;
import org.example.tela_farmacia.DAOs.RegistroDAO;
import org.example.tela_farmacia.DAOs.RemedioDAO;
import org.example.tela_farmacia.classes.Cliente;
import org.example.tela_farmacia.classes.Funcionario;
import org.example.tela_farmacia.classes.Registro;
import org.example.tela_farmacia.classes.Remedio;

import java.io.IOException;
import java.sql.SQLException;

public class MenuController {

    @FXML private TextField        txt_NomeFuncionario;
    @FXML private TextField        txt_NomeRemedio;
    @FXML private TextField        txt_TipoRemedio;
    @FXML private Spinner<Integer> spi_QuantRemedio;
    @FXML private TextField        txt_NomeCliente;
    @FXML private TextField        txt_IdadeCliente;

    @FXML
    public void initialize() {
        spi_QuantRemedio.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 9999, 1)
        );
    }

    // -------------------------------------------------------------------------
    // AÇÕES
    // -------------------------------------------------------------------------

    @FXML
    private void registrarTudo() {
        if (camposVazios()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos obrigatórios", "Preencha todos os campos antes de registrar.");
            return;
        }

        int idade;
        try {
            idade = Integer.parseInt(txt_IdadeCliente.getText().trim());
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.WARNING, "Idade inválida", "Digite um número válido para a idade.");
            return;
        }

        try {
            // 1. Insere cada entidade e obtém o ID gerado
            Cliente cliente = new Cliente(
                    txt_NomeCliente.getText().trim(),
                    idade
            );
            new ClienteDAO().inserir(cliente);

            Funcionario funcionario = new Funcionario(
                    txt_NomeFuncionario.getText().trim(),
                    "atendente"
            );
            new FuncionarioDAO().inserir(funcionario);

            Remedio remedio = new Remedio(
                    txt_NomeRemedio.getText().trim(),
                    txt_TipoRemedio.getText().trim()
            );
            new RemedioDAO().inserir(remedio);

            // 2. Monta o Registro flat com os IDs gerados e insere
            Registro registro = new Registro();
            registro.setId_cliente(cliente.getId_cliente());
            registro.setId_funcionario(funcionario.getId_funcionario());
            registro.setId_remedio(remedio.getId_remedio());
            registro.setQuantidade(spi_QuantRemedio.getValue());
            new RegistroDAO().inserir(registro);

            mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Registro salvo com sucesso!");
            limparCampos();

        } catch (SQLException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro ao salvar", e.getMessage());
        }
    }

    @FXML
    private void consultarTabela() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Tela_Registros.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) txt_NomeCliente.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro de navegação", e.getMessage());
        }
    }

    // -------------------------------------------------------------------------
    // AUXILIARES
    // -------------------------------------------------------------------------

    private boolean camposVazios() {
        return txt_NomeFuncionario.getText().trim().isEmpty()
                || txt_NomeRemedio.getText().trim().isEmpty()
                || txt_TipoRemedio.getText().trim().isEmpty()
                || txt_NomeCliente.getText().trim().isEmpty()
                || txt_IdadeCliente.getText().trim().isEmpty();
    }

    private void limparCampos() {
        txt_NomeFuncionario.clear();
        txt_NomeRemedio.clear();
        txt_TipoRemedio.clear();
        txt_NomeCliente.clear();
        txt_IdadeCliente.clear();
        spi_QuantRemedio.getValueFactory().setValue(1);
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}