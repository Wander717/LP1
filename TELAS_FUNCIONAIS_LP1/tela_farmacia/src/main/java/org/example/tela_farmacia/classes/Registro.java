package org.example.tela_farmacia.entities;

public class Registro {

    private int id_registro;
    private Cliente cliente;
    private Remedio remedio;
    private Funcionario funcionario;
    private int quantidade;

    public Registro() {}

    public Registro(int id_registro, Cliente cliente, Remedio remedio, Funcionario funcionario, int quantidade) {
        this.id_registro = id_registro;
        this.cliente = cliente;
        this.remedio = remedio;
        this.funcionario = funcionario;
        this.quantidade = quantidade;
    }

    public Registro(Cliente cliente, Remedio remedio, Funcionario funcionario, int quantidade) {
        this.cliente = cliente;
        this.remedio = remedio;
        this.funcionario = funcionario;
        this.quantidade = quantidade;
    }

    public int getId_registro() { return id_registro; }
    public void setId_registro(int id_registro) { this.id_registro = id_registro; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Remedio getRemedio() { return remedio; }
    public void setRemedio(Remedio remedio) { this.remedio = remedio; }

    public Funcionario getFuncionario() { return funcionario; }
    public void setFuncionario(Funcionario funcionario) { this.funcionario = funcionario; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    @Override
    public String toString() {
        return "Registro{id_registro=" + id_registro +
                ", cliente=" + cliente +
                ", remedio=" + remedio +
                ", funcionario=" + funcionario +
                ", quantidade=" + quantidade + '}';
    }
}