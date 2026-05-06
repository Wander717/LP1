package org.example.tela_farmacia.classes;
import java.time.LocalDateTime;

public class Registro {
    private int id;
    private Cliente cliente;
    private Funcionario funcionario;
    private Remedio remedio;
    private int quantidadeVendida;
    private LocalDateTime dataRegistro; // Para a coluna timestamp

    // Construtor para criar um novo registro
    public Registro(Cliente cliente, Funcionario funcionario, Remedio remedio) {
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.remedio = remedio;
    }

    // Getters
    public Cliente getCliente() { return cliente; }
    public Funcionario getFuncionario() { return funcionario; }
    public Remedio getRemedio() { return remedio; }
    public int getId() { return id; }
    public LocalDateTime getDataRegistro() { return dataRegistro; }
}