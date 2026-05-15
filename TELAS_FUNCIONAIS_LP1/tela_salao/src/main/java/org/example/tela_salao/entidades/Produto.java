package org.example.tela_salao.entidades;

public class Produto {
    // Atributos baseados nas colunas da tabela 'produto'
    private int id;
    private String nome;
    private String tipo;
    private int quantidade;

    //CONSTRUTOR
    public Produto(int id, String nome, String tipo, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.quantidade = quantidade;
    }

    //CONSTRUTOR VAZIO
    public Produto() {}

    //GETTERS
    public int getId() {return id;}
    public String getNome() {return nome;}
    public String getTipo() {return tipo;}
    public int getQuantidade() {return quantidade;}

    //SETTERS
    public void setId(int id) {this.id = id;}
    public void setNome(String nome) {this.nome = nome;}
    public void setTipo(String tipo) {this.tipo = tipo;}
    public void setQuantidade(int quantidade) {this.quantidade = quantidade;}
}
