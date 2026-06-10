package org.example.tela_barraquinha.classes;

public class Cliente {

    private int id_cliente;
    private String nome_cliente;

    public Cliente() {}

    public Cliente(int id_cliente, String nome_cliente) {
        this.id_cliente = id_cliente;
        this.nome_cliente = nome_cliente;
    }

    public Cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public int getId_cliente()             { return id_cliente; }
    public void setId_cliente(int id)       { this.id_cliente = id; }

    public String getNome_cliente()           { return nome_cliente; }
    public void setNome_cliente(String s)   { this.nome_cliente = s; }

    @Override
    public String toString() {
        return "Cliente{id=" + id_cliente + ", nome='" + nome_cliente + "'}";
    }
}