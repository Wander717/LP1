package org.example.tela_farmacia.classes;

public class Funcionario {

    private int id_funcionario;
    private String nome_funcionario;
    private String cargo_funcionario;

    public Funcionario() {}

    public Funcionario(int id_funcionario, String nome_funcionario, String cargo_funcionario) {
        this.id_funcionario = id_funcionario;
        this.nome_funcionario = nome_funcionario;
        this.cargo_funcionario = cargo_funcionario;
    }

    public Funcionario(String nome_funcionario, String cargo_funcionario) {
        this.nome_funcionario = nome_funcionario;
        this.cargo_funcionario = cargo_funcionario;
    }

    public int getId_funcionario() { return id_funcionario; }
    public void setId_funcionario(int id_funcionario) { this.id_funcionario = id_funcionario; }

    public String getNome_funcionario() { return nome_funcionario; }
    public void setNome_funcionario(String nome_funcionario) { this.nome_funcionario = nome_funcionario; }

    public String getCargo_funcionario() { return cargo_funcionario; }
    public void setCargo_funcionario(String cargo_funcionario) { this.cargo_funcionario = cargo_funcionario; }

    @Override
    public String toString() {
        return "Funcionario{id_funcionario=" + id_funcionario +
                ", nome_funcionario='" + nome_funcionario + '\'' +
                ", cargo_funcionario='" + cargo_funcionario + '\'' + '}';
    }
}