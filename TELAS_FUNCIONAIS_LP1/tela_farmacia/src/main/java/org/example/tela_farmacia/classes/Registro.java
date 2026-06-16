package org.example.tela_farmacia.classes;

public class Registro {

    private int    id_registro;
    private int    id_cliente;
    private int    id_remedio;
    private int    id_funcionario;
    private String nome_cliente;
    private int    idade_cliente;
    private String nome_remedio;
    private String tipo_remedio;
    private String nome_funcionario;
    private int    quantidade;

    public Registro() {}

    public Registro(int id_registro, String nome_cliente, int idade_cliente,
                    String nome_remedio, String tipo_remedio,
                    String nome_funcionario, int quantidade) {
        this.id_registro      = id_registro;
        this.nome_cliente     = nome_cliente;
        this.idade_cliente    = idade_cliente;
        this.nome_remedio     = nome_remedio;
        this.tipo_remedio     = tipo_remedio;
        this.nome_funcionario = nome_funcionario;
        this.quantidade       = quantidade;
    }

    // ── Getters & Setters ────────────────────────────────────────────────────

    public int getId_registro()               { return id_registro; }
    public void setId_registro(int id)        { this.id_registro = id; }
    public int getId_cliente()                { return id_cliente; }
    public void setId_cliente(int id)         { this.id_cliente = id; }
    public int getId_remedio()                { return id_remedio; }
    public void setId_remedio(int id)         { this.id_remedio = id; }
    public int getId_funcionario()            { return id_funcionario; }
    public void setId_funcionario(int id)     { this.id_funcionario = id; }
    public String getNome_cliente()           { return nome_cliente; }
    public void setNome_cliente(String s)     { this.nome_cliente = s; }
    public int getIdade_cliente()             { return idade_cliente; }
    public void setIdade_cliente(int i)       { this.idade_cliente = i; }
    public String getNome_remedio()           { return nome_remedio; }
    public void setNome_remedio(String s)     { this.nome_remedio = s; }
    public String getTipo_remedio()           { return tipo_remedio; }
    public void setTipo_remedio(String s)     { this.tipo_remedio = s; }
    public String getNome_funcionario()       { return nome_funcionario; }
    public void setNome_funcionario(String s) { this.nome_funcionario = s; }
    public int getQuantidade()                { return quantidade; }
    public void setQuantidade(int q)          { this.quantidade = q; }

    @Override
    public String toString() {
        return "Registro{id=" + id_registro +
                ", cliente='"     + nome_cliente     + '\'' +
                ", idade="        + idade_cliente    +
                ", remedio='"     + nome_remedio     + '\'' +
                ", tipo='"        + tipo_remedio     + '\'' +
                ", funcionario='" + nome_funcionario + '\'' +
                ", qtd="          + quantidade       + '}';
    }
}