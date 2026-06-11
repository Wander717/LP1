package org.example.tela_farmacia.classes;

public class Cliente {
    private String nome_cliente;
    private int idade_cliente;
    private int id_cliente;

    // CONSTRUTOR
    public Cliente(String nome_cliente, int idade_cliente, int id_cliente) {
        this.nome_cliente = nome_cliente;
        this.idade_cliente = idade_cliente;
        this.id_cliente = id_cliente;
    }

    // CONSTRUTOR VAZIO
    public Cliente() {}


    // GETTERS
    public String getNome_cliente() {
        return nome_cliente;
    }

    public int getIdade_cliente() {
        return idade_cliente;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    // SETTERS
    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public void setIdade_cliente(int idade_cliente) {
        this.idade_cliente = idade_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }
}
