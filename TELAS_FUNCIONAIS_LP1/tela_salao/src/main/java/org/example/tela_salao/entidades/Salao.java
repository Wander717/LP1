package org.example.tela_salao.entidades;

public class Salao {

    private String nome_funcionario;
    private String nome_cliente;
    private String produto;
    private String tipo_produto;
    private int quantidade_produto;

    public Salao(String nome_funcionario, String nome_cliente, String produto, String tipo_produto, int quantidade_produto) {
        this.nome_funcionario = nome_funcionario;
        this.nome_cliente = nome_cliente;
        this.produto = produto;
        this.tipo_produto = tipo_produto;
        this.quantidade_produto = quantidade_produto;
    }

    public String getNome_funcionario() { return nome_funcionario; }
    public String getNome_cliente() { return nome_cliente; }
    public String getProduto() { return produto; }
    public String getTipo_produto() { return tipo_produto; }
    public int getQuantidade_produto() { return quantidade_produto; }
}