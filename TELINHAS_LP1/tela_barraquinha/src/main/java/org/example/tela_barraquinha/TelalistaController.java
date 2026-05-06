package org.example.tela_barraquinha;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

import javax.swing.*;
import java.io.IOException;

public class TelalistaController {

    @FXML
    private Label nome_cliente;

    @FXML
    private Button btn_voltar;

    @FXML
    private Button btn_sair;

    @FXML
    private Button btn_comprar;

    // 🔹 Receber nome do cliente (depois vamos melhorar isso)
    public void setNomeCliente(String nome) {
        nome_cliente.setText(nome);
    }

    // 🔹 Voltar para o menu
    @FXML
    private void voltarMenu() throws IOException {
        Parent root = FXMLLoader.load(
                getClass().getResource("/org/example/tela_barraquinha/tela_menu.fxml")
        );

        Stage stage = (Stage) nome_cliente.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    // 🔹 Sair
    @FXML
    private void sair() {
        Stage stage = (Stage) nome_cliente.getScene().getWindow();
        stage.close();
    }

    // 🔹 Comprar (placeholder por enquanto)
    @FXML
    private void comprar() {
        JOptionPane.showMessageDialog(null, "Compra realizada!");
        System.out.println("Compra realizada!");
    }
}