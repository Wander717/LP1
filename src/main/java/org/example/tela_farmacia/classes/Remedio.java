package org.example.tela_farmacia.classes;

public class Remedio {
    private String nome;
    private String tipo;
    private int quantidade;
    private int id;


    //CONSTRUTOR
    public Remedio (String nome, String tipo, int quantidade, int id) {
        this.nome = nome;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.id = id;
    }

    //GETTERS
    public String getNome() {
        return nome;
    }
    public String getTipo() {
        return tipo;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public int getId() {return id;}
}
