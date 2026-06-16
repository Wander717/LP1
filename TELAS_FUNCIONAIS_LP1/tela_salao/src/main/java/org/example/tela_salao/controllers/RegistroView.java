package org.example.tela_salao.controllers;

import javafx.beans.property.*;
import java.time.LocalDate;

public class RegistroView {

    private final IntegerProperty idRegistro;
    private final StringProperty nomeFuncionario;
    private final StringProperty nomeProduto;
    private final StringProperty tipoProduto;
    private final IntegerProperty quantidade;
    private final ObjectProperty<LocalDate> dataRegistro;

    public RegistroView(int idRegistro, String nomeFuncionario, String nomeProduto,
                        String tipoProduto, int quantidade, LocalDate dataRegistro) {
        this.idRegistro = new SimpleIntegerProperty(idRegistro);
        this.nomeFuncionario = new SimpleStringProperty(nomeFuncionario);
        this.nomeProduto = new SimpleStringProperty(nomeProduto);
        this.tipoProduto = new SimpleStringProperty(tipoProduto);
        this.quantidade = new SimpleIntegerProperty(quantidade);
        this.dataRegistro = new SimpleObjectProperty<>(dataRegistro);
    }

    public int getIdRegistro() { return idRegistro.get(); }
    public IntegerProperty idRegistroProperty() { return idRegistro; }

    public String getNomeFuncionario() { return nomeFuncionario.get(); }
    public StringProperty nomeFuncionarioProperty() { return nomeFuncionario; }

    public String getNomeProduto() { return nomeProduto.get(); }
    public StringProperty nomeProdutoProperty() { return nomeProduto; }

    public String getTipoProduto() { return tipoProduto.get(); }
    public StringProperty tipoProdutoProperty() { return tipoProduto; }

    public int getQuantidade() { return quantidade.get(); }
    public IntegerProperty quantidadeProperty() { return quantidade; }

    public LocalDate getDataRegistro() { return dataRegistro.get(); }
    public ObjectProperty<LocalDate> dataRegistroProperty() { return dataRegistro; }
    public void setDataRegistro(LocalDate data) { this.dataRegistro.set(data); }
}