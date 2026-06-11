package org.example.tela_farmacia.entities;

public class Cliente {

    private int id_cliente;
    private String nome_cliente;
    private int idade_cliente;

    public Cliente() {}

    public Cliente(int id_cliente, String nome_cliente, int idade_cliente) {
        this.id_cliente = id_cliente;
        this.nome_cliente = nome_cliente;
        this.idade_cliente = idade_cliente;
    }

    public Cliente(String nome_cliente, int idade_cliente) {
        this.nome_cliente = nome_cliente;
        this.idade_cliente = idade_cliente;
    }

    public int getId_cliente() { return id_cliente; }
    public void setId_cliente(int id_cliente) { this.id_cliente = id_cliente; }

    public String getNome_cliente() { return nome_cliente; }
    public void setNome_cliente(String nome_cliente) { this.nome_cliente = nome_cliente; }

    public int getIdade_cliente() { return idade_cliente; }
    public void setIdade_cliente(int idade_cliente) { this.idade_cliente = idade_cliente; }

    @Override
    public String toString() {
        return "Cliente{id_cliente=" + id_cliente +
                ", nome_cliente='" + nome_cliente + '\'' +
                ", idade_cliente=" + idade_cliente + '}';
    }
}