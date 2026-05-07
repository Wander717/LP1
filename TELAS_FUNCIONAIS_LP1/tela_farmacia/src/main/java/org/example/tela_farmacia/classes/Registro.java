package org.example.tela_farmacia.classes;

public class Registro {

    private Integer id;
    private Cliente cliente;
    private Funcionario funcionario;
    private Remedio remedio;

    public Registro(Cliente cliente, Funcionario funcionario, Remedio remedio) {
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.remedio = remedio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public Remedio getRemedio() {
        return remedio;
    }
}