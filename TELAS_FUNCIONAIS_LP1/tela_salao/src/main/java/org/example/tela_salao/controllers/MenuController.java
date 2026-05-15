package org.example.tela_salao.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.SpinnerValueFactory;

public class MenuController {

    @FXML
    private TextField txt_NomeFuncionario;

    @FXML
    private TextField txt_NomeCliente;

    @FXML
    private TextField txt_Produto;

    @FXML
    private TextField txt_TipoProduto;

    @FXML
    private Spinner<Integer> spin_Quantidade;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btn_Tabela;

    private EntidadesDAO dao = new EntidadesDAO();

    @FXML
    public void initialize() {
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 1);

        spin_Quantidade.setValueFactory(valueFactory);
    }

    @FXML
    private void Cadastrar() {

        try {
            String funcionario = txt_NomeFuncionario.getText();
            String cliente = txt_NomeCliente.getText();
            String produto = txt_Produto.getText();
            String tipo = txt_TipoProduto.getText();
            int quantidade = spin_Quantidade.getValue();

            if (funcionario.isEmpty() || cliente.isEmpty() || produto.isEmpty() || tipo.isEmpty()) {
                mostrarAlerta("Erro", "Preencha todos os campos!");
                return;
            }

            Salao e = new Salao(
                    funcionario,
                    cliente,
                    produto,
                    tipo,
                    quantidade
            );

            dao.inserir(e);

            mostrarAlerta("Sucesso", "Registro salvo no banco!");

            limparCampos();

        } catch (Exception ex) {
            mostrarAlerta("Erro", "Erro ao cadastrar!");
        }
    }

    @FXML
    private void IrparaTabela() {
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(
                    getClass().getResource("/Tela_Tabela.fxml")
            );

            javafx.scene.Parent root = loader.load();

            javafx.stage.Stage stage = (javafx.stage.Stage) btn_Tabela.getScene().getWindow();
            stage.setScene(new javafx.scene.Scene(root));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void limparCampos() {
        txt_NomeFuncionario.clear();
        txt_NomeCliente.clear();
        txt_Produto.clear();
        txt_TipoProduto.clear();
        spin_Quantidade.getValueFactory().setValue(1);
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}