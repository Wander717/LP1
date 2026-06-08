package org.example.tela_barraquinha.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.tela_barraquinha.DAOS.FrutaDAO;
import org.example.tela_barraquinha.classes.Cliente;
import org.example.tela_barraquinha.classes.Fruta;

public class MenuController {

    @FXML
    private TextField txt_NomeCliente;

    @FXML
    private TextField txt_NomeFruta;

    @FXML
    private ComboBox<String> combobox_selecionarfruta;

    @FXML
    private Spinner<Integer> spn_quant_fruta;

    // Inicializa componentes
    @FXML
    public void initialize() {

        // Estados da fruta
        combobox_selecionarfruta.getItems().addAll("Fresco", "Maduro", "Estragado");

        // Spinner (1 a 100)
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1);

        spn_quant_fruta.setValueFactory(valueFactory);
    }

    // Botão cadastrar
    @FXML
    public void CadastrarTudo() {
        try {
            String nome = txt_NomeCliente.getText();
            String nomeFruta = txt_NomeFruta.getText();
            String estado = combobox_selecionarfruta.getValue();
            int quantidade = spn_quant_fruta.getValue();

            // Validação simples
            if (nome.isEmpty() || nomeFruta.isEmpty() || estado == null) {
                mostrarAlerta("Erro", "Preencha todos os campos!");
                return;
            }

            Cliente cliente = new Cliente(nome);
            Fruta fruta = new Fruta(cliente, nomeFruta, estado, quantidade);

            FrutaDAO dao = new FrutaDAO();
            dao.inserir(fruta);

            mostrarAlerta("Sucesso", "Cadastro realizado!");

            limparCampos();

        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Erro", "Erro ao salvar no banco!");
        }
    }

    // Ir para tela de lista
    @FXML
    public void irParaLista(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tela_listafrutas.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    // Botão sair
    @FXML
    public void sair() {
        System.exit(0);
    }

    // Limpar campos
    private void limparCampos() {
        txt_NomeCliente.clear();
        txt_NomeFruta.clear();
        combobox_selecionarfruta.setValue(null);
        spn_quant_fruta.getValueFactory().setValue(1);
    }

    // Alertas
    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}