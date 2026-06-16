package org.example.tela_salao.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class TabelaController implements Initializable {

    @FXML private TableView<RegistroView> tableView;
    @FXML private TableColumn<RegistroView, Integer> colIdRegistro;
    @FXML private TableColumn<RegistroView, String> colFuncionario;
    @FXML private TableColumn<RegistroView, String> colProduto;
    @FXML private TableColumn<RegistroView, String> colTipoProduto;
    @FXML private TableColumn<RegistroView, Integer> colQuantidade;
    @FXML private TableColumn<RegistroView, LocalDate> colDataRegistro;
    @FXML private Button btnEditar;
    @FXML private Button btnExcluir;

    private final RegistroDAO registroDAO = new RegistroDAO();
    private final ProdutoDAO produtoDAO = new ProdutoDAO();
    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    private final ObservableList<RegistroView> dados = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarColunas();
        carregarDados();

        // Habilita os botões apenas quando há item selecionado
        btnEditar.setDisable(true);
        btnExcluir.setDisable(true);

        tableView.getSelectionModel().selectedItemProperty().addListener(
                (obs, antigo, novo) -> {
                    boolean semSelecao = (novo == null);
                    btnEditar.setDisable(semSelecao);
                    btnExcluir.setDisable(semSelecao);
                }
        );

        btnEditar.setOnAction(e -> editarItemSelecionado());
        btnExcluir.setOnAction(e -> excluirRegistroSelecionado());
    }

    private void configurarColunas() {
        colIdRegistro.setCellValueFactory(data -> data.getValue().idRegistroProperty().asObject());
        colFuncionario.setCellValueFactory(data -> data.getValue().nomeFuncionarioProperty());
        colProduto.setCellValueFactory(data -> data.getValue().nomeProdutoProperty());
        colTipoProduto.setCellValueFactory(data -> data.getValue().tipoProdutoProperty());
        colQuantidade.setCellValueFactory(data -> data.getValue().quantidadeProperty().asObject());
        colDataRegistro.setCellValueFactory(data -> data.getValue().dataRegistroProperty());
    }

    private void carregarDados() {
        dados.clear();

        List<Registro> registros = registroDAO.listarTodos();

        for (Registro r : registros) {
            Produto produto = produtoDAO.buscarPorId(r.getIdProduto());
            Funcionario funcionario = funcionarioDAO.buscarPorId(r.getIdFuncionario());

            String nomeProduto = produto != null ? produto.getNomeProduto() : "—";
            String tipoProduto = produto != null ? produto.getTipoProduto() : "—";
            int quantidade = produto != null ? produto.getQuantidadeProduto() : 0;
            String nomeFuncionario = funcionario != null ? funcionario.getNomeFuncionario() : "—";

            dados.add(new RegistroView(
                    r.getIdRegistro(),
                    nomeFuncionario,
                    nomeProduto,
                    tipoProduto,
                    quantidade,
                    r.getDataRegistro()
            ));
        }

        tableView.setItems(dados);
    }

    private void editarItemSelecionado() {
        RegistroView selecionado = tableView.getSelectionModel().getSelectedItem();
        if (selecionado == null) return;

        // Abre um DatePicker em um diálogo para o usuário escolher a nova data
        Dialog<LocalDate> dialog = new Dialog<>();
        dialog.setTitle("Editar Data do Registro");
        dialog.setHeaderText("Alterar a data do registro #" + selecionado.getIdRegistro());

        ButtonType btnConfirmar = new ButtonType("Confirmar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(btnConfirmar, ButtonType.CANCEL);

        DatePicker datePicker = new DatePicker(selecionado.getDataRegistro());
        dialog.getDialogPane().setContent(datePicker);

        dialog.setResultConverter(botao -> {
            if (botao == btnConfirmar) {
                return datePicker.getValue();
            }
            return null;
        });

        Optional<LocalDate> resultado = dialog.showAndWait();
        resultado.ifPresent(novaData -> {
            try {
                registroDAO.atualizarData(selecionado.getIdRegistro(), novaData);
                selecionado.setDataRegistro(novaData);
                tableView.refresh();
                mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Data atualizada com sucesso!");
            } catch (Exception e) {
                mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Erro ao atualizar data: " + e.getMessage());
            }
        });
    }

    private void excluirRegistroSelecionado() {
        RegistroView selecionado = tableView.getSelectionModel().getSelectedItem();
        if (selecionado == null) return;

        Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacao.setTitle("Confirmar exclusão");
        confirmacao.setHeaderText(null);
        confirmacao.setContentText("Deseja excluir o registro #" + selecionado.getIdRegistro() + "?");

        Optional<ButtonType> resposta = confirmacao.showAndWait();
        if (resposta.isPresent() && resposta.get() == ButtonType.OK) {
            try {
                registroDAO.deletar(selecionado.getIdRegistro());
                dados.remove(selecionado);
                mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Registro excluído com sucesso!");
            } catch (Exception e) {
                mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Erro ao excluir registro: " + e.getMessage());
            }
        }
    }

    @FXML
    private void voltarMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Tela_Menu.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) tableView.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro de navegação", "Não foi possível voltar ao menu: " + e.getMessage());
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}