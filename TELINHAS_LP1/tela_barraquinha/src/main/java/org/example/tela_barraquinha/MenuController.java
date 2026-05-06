package org.example.tela_barraquinha;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

import java.io.IOException;

public class MenuController {

    @FXML
    private Button btn_ver_lista;

    @FXML
    private Button btn_sair;

    @FXML
    private Button btn_cadastrar_nome;

    @FXML
    private TextField txtfield_nome;

    private String nomeCliente;

    // 🔹 Inicialização (opcional)
    @FXML
    public void initialize() {
        // Aqui você pode configurar coisas iniciais
    }

    // 🔹 Cadastrar nome
    @FXML
    private void cadastrarNome() {
        nomeCliente = txtfield_nome.getText();

        if (nomeCliente == null || nomeCliente.isEmpty()) {
            System.out.println("Digite um nome válido!");
            JOptionPane.showMessageDialog(null,"Digite um nome válido!");
        } else {
            JOptionPane.showMessageDialog(null,"Cliente cadastrado: " + nomeCliente);
            System.out.println("Cliente cadastrado: " + nomeCliente);
        }
    }

    // 🔹 Ir para lista de frutas
    @FXML
    private void irParaLista() throws IOException {
        Parent root = FXMLLoader.load(
                getClass().getResource("/org/example/tela_barraquinha/tela_listafrutas.fxml")
        );

        Stage stage = (Stage) btn_ver_lista.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    // 🔹 Sair do sistema
    @FXML
    private void sair() {
        Stage stage = (Stage) btn_sair.getScene().getWindow();
        stage.close();
    }
}