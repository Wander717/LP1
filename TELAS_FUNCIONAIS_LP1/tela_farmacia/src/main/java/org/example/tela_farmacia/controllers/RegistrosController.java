package org.example.tela_farmacia.controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
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

public class RegistrosController {

    // -------------------------------------------------------------------------
    // FXML — componentes da tela
    // -------------------------------------------------------------------------
    @FXML private TableView<Registro>            tabela_Registros;
    @FXML private TableColumn<Registro, Integer> col_IdRegistro;
    @FXML private TableColumn<Registro, String>  col_NomeCliente;
    @FXML private TableColumn<Registro, Integer> col_IdadeCliente;
    @FXML private TableColumn<Registro, String>  col_NomeRemedio;
    @FXML private TableColumn<Registro, String>  col_TipoRemedio;
    @FXML private TableColumn<Registro, Integer> col_QuantRemedio;
    @FXML private TableColumn<Registro, String>  col_NomeFuncionario;
    @FXML private Button                         btn_EditarSelecionado;
    @FXML private Button                         btn_Salvar;

    // Lista observável — qualquer alteração aqui reflete automaticamente na tabela
    private final ObservableList<Registro> dados = FXCollections.observableArrayList();

    // -------------------------------------------------------------------------
    // INICIALIZAÇÃO
    // -------------------------------------------------------------------------
    @FXML
    public void initialize() {
        configurarColunas();
        carregarDados();
        btn_Salvar.setDisable(true); // Salvar só habilita após clicar em Editar
    }

    // -------------------------------------------------------------------------
    // CONFIGURAÇÃO DAS COLUNAS
    // -------------------------------------------------------------------------
    private void configurarColunas() {

        // Leitura — vincula cada coluna ao campo correspondente do objeto Registro
        col_IdRegistro.setCellValueFactory(     c -> new SimpleIntegerProperty(c.getValue().getId_registro()).asObject());
        col_NomeCliente.setCellValueFactory(    c -> new SimpleStringProperty(c.getValue().getNome_cliente()));
        col_IdadeCliente.setCellValueFactory(   c -> new SimpleIntegerProperty(c.getValue().getIdade_cliente()).asObject());
        col_NomeRemedio.setCellValueFactory(    c -> new SimpleStringProperty(c.getValue().getNome_remedio()));
        col_TipoRemedio.setCellValueFactory(    c -> new SimpleStringProperty(c.getValue().getTipo_remedio()));
        col_QuantRemedio.setCellValueFactory(   c -> new SimpleIntegerProperty(c.getValue().getQuantidade()).asObject());
        col_NomeFuncionario.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNome_funcionario()));

        // Edição — ao confirmar com Enter, atualiza apenas o objeto em memória
        // O banco só é atualizado quando o usuário clicar em "Salvar"
        col_NomeCliente.setCellFactory(TextFieldTableCell.forTableColumn());
        col_NomeCliente.setOnEditCommit(    e -> e.getRowValue().setNome_cliente(e.getNewValue()));

        col_IdadeCliente.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        col_IdadeCliente.setOnEditCommit(   e -> e.getRowValue().setIdade_cliente(e.getNewValue()));

        col_NomeRemedio.setCellFactory(TextFieldTableCell.forTableColumn());
        col_NomeRemedio.setOnEditCommit(    e -> e.getRowValue().setNome_remedio(e.getNewValue()));

        col_TipoRemedio.setCellFactory(TextFieldTableCell.forTableColumn());
        col_TipoRemedio.setOnEditCommit(    e -> e.getRowValue().setTipo_remedio(e.getNewValue()));

        col_QuantRemedio.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        col_QuantRemedio.setOnEditCommit(   e -> e.getRowValue().setQuantidade(e.getNewValue()));

        col_NomeFuncionario.setCellFactory(TextFieldTableCell.forTableColumn());
        col_NomeFuncionario.setOnEditCommit(e -> e.getRowValue().setNome_funcionario(e.getNewValue()));

        // ID é apenas exibição — não pode ser editado
        col_IdRegistro.setEditable(false);

        // Tabela começa não editável — só habilita ao clicar em "Editar"
        tabela_Registros.setEditable(false);
    }

    // -------------------------------------------------------------------------
    // AÇÃO — Editar linha selecionada
    // -------------------------------------------------------------------------
    @FXML
    private void editarLinhaSelecionada() {
        if (tabela_Registros.getSelectionModel().getSelectedItem() == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Nenhuma linha selecionada", "Selecione um registro para editar.");
            return;
        }

        // Habilita edição e troca estado dos botões
        tabela_Registros.setEditable(true);
        btn_EditarSelecionado.setDisable(true);
        btn_Salvar.setDisable(false);

        // Posiciona o cursor na primeira célula editável da linha
        tabela_Registros.edit(tabela_Registros.getSelectionModel().getSelectedIndex(), col_NomeCliente);
    }

    // -------------------------------------------------------------------------
    // AÇÃO — Salvar alterações no banco
    // -------------------------------------------------------------------------
    @FXML
    private void salvarAlteracoes() {
        Registro selecionado = tabela_Registros.getSelectionModel().getSelectedItem();
        if (selecionado == null) return;

        try {
            // Cada DAO atualiza apenas a sua própria tabela,
            // usando o ID já carregado no Registro pelo listarTodos()
            new ClienteDAO().atualizar(new Cliente(
                    selecionado.getId_cliente(),
                    selecionado.getNome_cliente(),
                    selecionado.getIdade_cliente()
            ));

            new RemedioDAO().atualizar(new Remedio(
                    selecionado.getId_remedio(),
                    selecionado.getNome_remedio(),
                    selecionado.getTipo_remedio()
            ));

            new FuncionarioDAO().atualizar(new Funcionario(
                    selecionado.getId_funcionario(),
                    selecionado.getNome_funcionario(),
                    "atendente" // cargo não é exibido nem editado na tabela
            ));

            new RegistroDAO().atualizarQuantidade(
                    selecionado.getId_registro(),
                    selecionado.getQuantidade()
            );

            mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Registro atualizado com sucesso!");

        } catch (SQLException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro ao salvar", e.getMessage());
        } finally {
            // Sempre volta ao estado não editável, independente de sucesso ou erro
            tabela_Registros.setEditable(false);
            btn_EditarSelecionado.setDisable(false);
            btn_Salvar.setDisable(true);
        }
    }

    // -------------------------------------------------------------------------
    // AÇÃO — Excluir linha selecionada
    // -------------------------------------------------------------------------
    @FXML
    private void excluirLinhaSelecionada() {
        Registro selecionado = tabela_Registros.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Nenhuma linha selecionada", "Selecione um registro para excluir.");
            return;
        }

        Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacao.setTitle("Confirmar exclusão");
        confirmacao.setHeaderText(null);
        confirmacao.setContentText("Deseja excluir o registro de " + selecionado.getNome_cliente() + "?");

        confirmacao.showAndWait().ifPresent(resposta -> {
            if (resposta == ButtonType.OK) {
                try {
                    new RegistroDAO().deletar(selecionado.getId_registro());
                    dados.remove(selecionado); // Remove da lista — tabela atualiza automaticamente
                } catch (SQLException e) {
                    mostrarAlerta(Alert.AlertType.ERROR, "Erro ao excluir", e.getMessage());
                }
            }
        });
    }

    // -------------------------------------------------------------------------
    // AÇÃO — Voltar ao menu
    // -------------------------------------------------------------------------
    @FXML
    private void voltarMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Tela_Menu.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) tabela_Registros.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro de navegação", e.getMessage());
        }
    }

    // -------------------------------------------------------------------------
    // AUXILIARES
    // -------------------------------------------------------------------------

    private void carregarDados() {
        try {
            dados.setAll(new RegistroDAO().listarTodos());
            tabela_Registros.setItems(dados);
        } catch (SQLException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro ao carregar dados", e.getMessage());
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