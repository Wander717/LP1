package org.example.tela_farmacia.classes;

public class Cliente {
    private String nome_cliente;
    private int idade_cliente;
    private int id_cliente;

    //CONSTRUTOR
    public Cliente (String nome_cliente, int quantidade, int id_cliente) {
        this.nome_cliente = nome_cliente;
        this.idade_cliente = idade_cliente;
        this.id_cliente = id_cliente;
    }

    //GETTERS
    public String getNome_cliente() {
        return nome_cliente;
    }

    public int getIdade_cliente() {
        return idade_cliente;
    }

    public int getId_cliente() {return id_cliente;}


}
