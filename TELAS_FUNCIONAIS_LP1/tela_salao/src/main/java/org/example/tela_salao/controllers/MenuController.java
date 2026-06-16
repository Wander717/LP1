package org.example.tela_salao.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.example.tela_salao.DAOs.FuncionarioDAO;
import org.example.tela_salao.DAOs.ProdutoDAO;
import org.example.tela_salao.DAOs.RegistroDAO;
import org.example.tela_salao.entidades.Funcionario;
import org.example.tela_salao.entidades.Produto;
import org.example.tela_salao.entidades.Registro;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML private TextField txtProduto;
    @FXML private TextField txtTipoProduto;
    @FXML private Spinner<Integer> spinQuantidade;
    @FXML private ComboBox<Funcionario> cbSelecionarFuncionario;
    @FXML private DatePicker dateDataRegistro;

    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private final ProdutoDAO produtoDAO = new ProdutoDAO();
    private final RegistroDAO registroDAO = new RegistroDAO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configura o Spinner de quantidade (mín 1, máx 9999, valor inicial 1)
        spinQuantidade.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 9999, 1)
        );

        // Carrega os funcionários no ComboBox
        carregarFuncionarios();

        // Exibe o nome do funcionário no ComboBox
        cbSelecionarFuncionario.setConverter(new StringConverter<Funcionario>() {
            @Override
            public String toString(Funcionario f) {
                return f != null ? f.getNomeFuncionario() : "";
            }

            @Override
            public Funcionario fromString(String s) {
                return null;
            }
        });
    }

    private void carregarFuncionarios() {
        List<Funcionario> funcionarios = funcionarioDAO.listarTodos();
        cbSelecionarFuncionario.setItems(FXCollections.observableArrayList(funcionarios));
    }

    @FXML
    private void cadastrarTudo() {
        // Validações
        if (txtProduto.getText().isBlank()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campo obrigatório", "Informe o nome do produto.");
            return;
        }
        if (txtTipoProduto.getText().isBlank()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campo obrigatório", "Informe o tipo do produto.");
            return;
        }
        if (cbSelecionarFuncionario.getValue() == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campo obrigatório", "Selecione um funcionário.");
            return;
        }
        if (dateDataRegistro.getValue() == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campo obrigatório", "Informe a data do registro.");
            return;
        }

        try {
            // Cadastra o produto
            Produto produto = new Produto(
                    txtProduto.getText().trim(),
                    txtTipoProduto.getText().trim(),
                    spinQuantidade.getValue()
            );
            produtoDAO.inserir(produto);

            // Cria o registro vinculando produto e funcionário
            Funcionario funcionarioSelecionado = cbSelecionarFuncionario.getValue();
            LocalDate data = dateDataRegistro.getValue();

            Registro registro = new Registro(data, produto.getIdProduto(), funcionarioSelecionado.getIdFuncionario());
            registroDAO.inserir(registro);

            mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Cadastro realizado com sucesso!");
            limparCampos();

        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Erro ao cadastrar: " + e.getMessage());
        }
    }

    @FXML
    private void irparaTabela() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Tela_Tabela.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) txtProduto.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro de navegação", "Não foi possível abrir a tabela: " + e.getMessage());
        }
    }

    private void limparCampos() {
        txtProduto.clear();
        txtTipoProduto.clear();
        spinQuantidade.getValueFactory().setValue(1);
        cbSelecionarFuncionario.setValue(null);
        dateDataRegistro.setValue(null);
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}