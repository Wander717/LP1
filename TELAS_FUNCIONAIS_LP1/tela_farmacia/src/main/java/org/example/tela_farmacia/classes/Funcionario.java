package org.example.tela_farmacia.classes;

public class Funcionario {
    private String nome;
    private String cargo;
    private int id;


    //CONSTRUTOR
    public Funcionario (String nome, String cargo, int id) {
        this.nome = nome;
        this.cargo = cargo;
        this.id = id;
    }

    //GETTERS

    public String getNome() {
        return nome;
    }
    public String getCargo() {
        return cargo;
    }
    public int getId() {return id;}
}
