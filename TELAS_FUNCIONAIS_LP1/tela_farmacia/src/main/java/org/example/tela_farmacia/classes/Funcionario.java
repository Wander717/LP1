package org.example.tela_farmacia.classes;

public class Funcionario {
    private String nome_funcionario;
    private String cargo_funcionario;
    private int id_funcionario;

    //CONSTRUTOR
    public Funcionario (String nome_funcionario, String cargo_funcionario, int id_funcionario) {
        this.nome_funcionario = nome_funcionario;
        this.cargo_funcionario = cargo_funcionario;
        this.id_funcionario = id_funcionario;
    }

    //CONSTRUTOR VAZIO
    public Funcionario() {}

    //GETTERS
    public String getNome_funcionario() {
        return nome_funcionario;
    }
    public String getCargo_funcionario() {
        return cargo_funcionario;
    }
    public int getId_funcionario() {return id_funcionario;}
}
