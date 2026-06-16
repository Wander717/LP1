package org.example.tela_salao.entidades;

import java.time.LocalDate;

public class Registro {

    private int idRegistro;
    private LocalDate dataRegistro;
    private int idProduto;
    private int idFuncionario;

    public Registro() {}

    public Registro(int idRegistro, LocalDate dataRegistro, int idProduto, int idFuncionario) {
        this.idRegistro = idRegistro;
        this.dataRegistro = dataRegistro;
        this.idProduto = idProduto;
        this.idFuncionario = idFuncionario;
    }

    public Registro(LocalDate dataRegistro, int idProduto, int idFuncionario) {
        this.dataRegistro = dataRegistro;
        this.idProduto = idProduto;
        this.idFuncionario = idFuncionario;
    }

    public int getIdRegistro() { return idRegistro; }
    public void setIdRegistro(int idRegistro) { this.idRegistro = idRegistro; }

    public LocalDate getDataRegistro() { return dataRegistro; }
    public void setDataRegistro(LocalDate dataRegistro) { this.dataRegistro = dataRegistro; }

    public int getIdProduto() { return idProduto; }
    public void setIdProduto(int idProduto) { this.idProduto = idProduto; }

    public int getIdFuncionario() { return idFuncionario; }
    public void setIdFuncionario(int idFuncionario) { this.idFuncionario = idFuncionario; }

    @Override
    public String toString() {
        return "Registro{" +
                "idRegistro=" + idRegistro +
                ", dataRegistro=" + dataRegistro +
                ", idProduto=" + idProduto +
                ", idFuncionario=" + idFuncionario +
                '}';
    }
}