// Pedido.java
package org.example.tela_barraquinha.classes;

public class Pedido {
    private int    id_pedido;
    private int    id_cliente;
    private int    id_fruta;
    private int    id_funcionario;
    private String nome_cliente;
    private String nome_fruta;
    private String nome_funcionario;
    private int    quantidade;

    public Pedido() {}

    public Pedido(int id_pedido, String nome_cliente, String nome_fruta,
                  String nome_funcionario, int id_funcionario, int quantidade) {
        this.id_pedido        = id_pedido;
        this.nome_cliente     = nome_cliente;
        this.nome_fruta       = nome_fruta;
        this.nome_funcionario = nome_funcionario;
        this.id_funcionario   = id_funcionario;
        this.quantidade       = quantidade;
    }

    public int    getId_pedido()               { return id_pedido; }
    public void   setId_pedido(int id)         { this.id_pedido = id; }
    public int    getId_cliente()              { return id_cliente; }
    public void   setId_cliente(int id)        { this.id_cliente = id; }
    public int    getId_fruta()                { return id_fruta; }
    public void   setId_fruta(int id)          { this.id_fruta = id; }
    public int    getId_funcionario()          { return id_funcionario; }
    public void   setId_funcionario(int id)    { this.id_funcionario = id; }
    public String getNome_cliente()            { return nome_cliente; }
    public void   setNome_cliente(String s)    { this.nome_cliente = s; }
    public String getNome_fruta()              { return nome_fruta; }
    public void   setNome_fruta(String s)      { this.nome_fruta = s; }
    public String getNome_funcionario()        { return nome_funcionario; }
    public void   setNome_funcionario(String s){ this.nome_funcionario = s; }
    public int    getQuantidade()              { return quantidade; }
    public void   setQuantidade(int q)         { this.quantidade = q; }

    @Override
    public String toString() {
        return "Pedido{id=" + id_pedido +
                ", cliente='" + nome_cliente + '\'' +
                ", fruta='" + nome_fruta + '\'' +
                ", funcionario='" + nome_funcionario + '\'' +
                ", qtd=" + quantidade + '}';
    }
}