package org.example.tela_salao.controllers;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
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

    // -------------------------------------------------------------------------
    // ViewModel interno
    // -------------------------------------------------------------------------
    public static class RegistroView {

        private final IntegerProperty idRegistro;
        private final StringProperty  nomeFuncionario;
        private final StringProperty  nomeProduto;
        private final StringProperty  tipoProduto;
        private final IntegerProperty quantidade;
        private final ObjectProperty<LocalDate> dataRegistro;

        // IDs reais no banco, necessários para persistir as edições
        private final int idRegistroBanco;
        private final int idProdutoBanco;

        public RegistroView(int idRegistro, String nomeFuncionario, String nomeProduto,
                            String tipoProduto, int quantidade, LocalDate dataRegistro,
                            int idProdutoBanco) {
            this.idRegistro      = new SimpleIntegerProperty(idRegistro);
            this.nomeFuncionario = new SimpleStringProperty(nomeFuncionario);
            this.nomeProduto     = new SimpleStringProperty(nomeProduto);
            this.tipoProduto     = new SimpleStringProperty(tipoProduto);
            this.quantidade      = new SimpleIntegerProperty(quantidade);
            this.dataRegistro    = new SimpleObjectProperty<>(dataRegistro);
            this.idRegistroBanco = idRegistro;
            this.idProdutoBanco  = idProdutoBanco;
        }

        public int    getIdRegistro()      { return idRegistro.get(); }
        public IntegerProperty idRegistroProperty() { return idRegistro; }

        public String getNomeFuncionario() { return nomeFuncionario.get(); }
        public StringProperty nomeFuncionarioProperty() { return nomeFuncionario; }

        public String getNomeProduto()     { return nomeProduto.get(); }
        public StringProperty nomeProdutoProperty() { return nomeProduto; }

        public String getTipoProduto()     { return tipoProduto.get(); }
        public StringProperty tipoProdutoProperty() { return tipoProduto; }

        public int    getQuantidade()      { return quantidade.get(); }
        public IntegerProperty quantidadeProperty() { return quantidade; }
        public void   setQuantidade(int v) { quantidade.set(v); }

        public LocalDate getDataRegistro() { return dataRegistro.get(); }
        public ObjectProperty<LocalDate> dataRegistroProperty() { return dataRegistro; }

        public int getIdProdutoBanco()     { return idProdutoBanco; }
        public int getIdRegistroBanco()    { return idRegistroBanco; }
    }

    // -------------------------------------------------------------------------
    // FXML
    // -------------------------------------------------------------------------
    @FXML private TableView<RegistroView>          tableView;
    @FXML private TableColumn<RegistroView, Integer> colIdRegistro;
    @FXML private TableColumn<RegistroView, String>  colFuncionario;
    @FXML private TableColumn<RegistroView, String>  colProduto;
    @FXML private TableColumn<RegistroView, String>  colTipoProduto;
    @FXML private TableColumn<RegistroView, Integer> colQuantidade;
    @FXML private TableColumn<RegistroView, LocalDate> colDataRegistro;
    @FXML private Button btnEditar;
    @FXML private Button btnExcluir;

    private final RegistroDAO    registroDAO    = new RegistroDAO();
    private final ProdutoDAO     produtoDAO     = new ProdutoDAO();
    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    private final ObservableList<RegistroView> dados = FXCollections.observableArrayList();

    // -------------------------------------------------------------------------
    // Inicialização
    // -------------------------------------------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarColunas();
        carregarDados();

        btnEditar.setDisable(true);
        btnExcluir.setDisable(true);

        tableView.getSelectionModel().selectedItemProperty().addListener(
                (obs, antigo, novo) -> {
                    boolean semSelecao = (novo == null);
                    btnEditar.setDisable(semSelecao);
                    btnExcluir.setDisable(semSelecao);
                }
        );
    }

    private void configurarColunas() {
        colIdRegistro.setCellValueFactory(d -> d.getValue().idRegistroProperty().asObject());
        colFuncionario.setCellValueFactory(d -> d.getValue().nomeFuncionarioProperty());
        colProduto.setCellValueFactory(d -> d.getValue().nomeProdutoProperty());
        colTipoProduto.setCellValueFactory(d -> d.getValue().tipoProdutoProperty());
        colQuantidade.setCellValueFactory(d -> d.getValue().quantidadeProperty().asObject());
        colDataRegistro.setCellValueFactory(d -> d.getValue().dataRegistroProperty());

        // Tabela começa não-editável; só ativa no modo de edição
        tableView.setEditable(false);
    }

    private void carregarDados() {
        dados.clear();

        for (Registro r : registroDAO.listarTodos()) {
            Produto     produto     = produtoDAO.buscarPorId(r.getIdProduto());
            Funcionario funcionario = funcionarioDAO.buscarPorId(r.getIdFuncionario());

            dados.add(new RegistroView(
                    r.getIdRegistro(),
                    funcionario != null ? funcionario.getNomeFuncionario() : "—",
                    produto     != null ? produto.getNomeProduto()         : "—",
                    produto     != null ? produto.getTipoProduto()         : "—",
                    produto     != null ? produto.getQuantidadeProduto()   : 0,
                    r.getDataRegistro(),
                    r.getIdProduto()
            ));
        }

        tableView.setItems(dados);
    }

    // -------------------------------------------------------------------------
    // Edição inline
    // -------------------------------------------------------------------------
    @FXML
    private void editarItemSelecionado() {
        RegistroView selecionado = tableView.getSelectionModel().getSelectedItem();
        if (selecionado == null) return;

        // Ativa edição e configura cell factories para as colunas editáveis
        tableView.setEditable(true);

        colFuncionario.setCellFactory(TextFieldTableCell.forTableColumn());
        colFuncionario.setOnEditCommit(e -> e.getRowValue().nomeFuncionarioProperty().set(e.getNewValue()));

        colProduto.setCellFactory(TextFieldTableCell.forTableColumn());
        colProduto.setOnEditCommit(e -> e.getRowValue().nomeProdutoProperty().set(e.getNewValue()));

        colTipoProduto.setCellFactory(TextFieldTableCell.forTableColumn());
        colTipoProduto.setOnEditCommit(e -> e.getRowValue().tipoProdutoProperty().set(e.getNewValue()));

        colQuantidade.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colQuantidade.setOnEditCommit(e -> {
            if (e.getNewValue() != null) {
                e.getRowValue().setQuantidade(e.getNewValue());
            }
        });

        // Troca o botão "Editar" por "Salvar"
        btnEditar.setText("Salvar");
        btnEditar.setOnAction(e -> salvarEdicao(selecionado));
        btnExcluir.setDisable(true);
    }

    private void salvarEdicao(RegistroView item) {
        try {
            // Persiste alterações no produto
            Produto produto = produtoDAO.buscarPorId(item.getIdProdutoBanco());
            if (produto != null) {
                produto.setNomeProduto(item.getNomeProduto());
                produto.setTipoProduto(item.getTipoProduto());
                produto.setQuantidadeProduto(item.getQuantidade());
                produtoDAO.atualizar(produto);
            }

            // Persiste alterações no funcionário (pelo nome — busca o funcionário atual do registro)
            Registro registro = registroDAO.buscarPorId(item.getIdRegistroBanco());
            if (registro != null) {
                Funcionario funcionario = funcionarioDAO.buscarPorId(registro.getIdFuncionario());
                if (funcionario != null) {
                    funcionario.setNomeFuncionario(item.getNomeFuncionario());
                    funcionarioDAO.atualizar(funcionario);
                }
            }

            mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Registro atualizado com sucesso!");

        } catch (Exception ex) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Erro ao salvar: " + ex.getMessage());
        } finally {
            desativarEdicao();
        }
    }

    private void desativarEdicao() {
        // Remove cell factories editáveis — volta ao modo somente leitura
        colFuncionario.setCellFactory(col -> new TableCell<>() {
            @Override protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item);
            }
        });
        colProduto.setCellFactory(col -> new TableCell<>() {
            @Override protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item);
            }
        });
        colTipoProduto.setCellFactory(col -> new TableCell<>() {
            @Override protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item);
            }
        });
        colQuantidade.setCellFactory(col -> new TableCell<>() {
            @Override protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.toString());
            }
        });

        tableView.setEditable(false);

        // Restaura botão "Editar" e seu comportamento original
        btnEditar.setText("Editar Item selecionado");
        btnEditar.setOnAction(e -> editarItemSelecionado());

        // Reativa o botão excluir de acordo com a seleção atual
        boolean semSelecao = tableView.getSelectionModel().getSelectedItem() == null;
        btnExcluir.setDisable(semSelecao);
    }

    // -------------------------------------------------------------------------
    // Exclusão
    // -------------------------------------------------------------------------
    @FXML
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

    // -------------------------------------------------------------------------
    // Navegação
    // -------------------------------------------------------------------------
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

    // -------------------------------------------------------------------------
    // Utilitário
    // -------------------------------------------------------------------------
    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}