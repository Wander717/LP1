package org.example.tela_farmacia.classes;

public class Registro {

    private int id_registro;
    private int id_cliente;
    private int id_remedio;
    private int id_funcionario;
    private String nome_cliente;
    private int idade_cliente;
    private String nome_remedio;
    private String tipo_remedio;
    private String nome_funcionario;
    private int quantidade;

    //CONSTRUTOR
    public Registro(int id_registro,
                    String nome_cliente,
                    int idade_cliente,
                    String nome_remedio,
                    String tipo_remedio,
                    String nome_funcionario,
                    int quantidade) {

        this.id_registro = id_registro;
        this.nome_cliente = nome_cliente;
        this.idade_cliente = idade_cliente;
        this.nome_remedio = nome_remedio;
        this.tipo_remedio = tipo_remedio;
        this.nome_funcionario = nome_funcionario;
        this.quantidade = quantidade;
    }

    //CONSTRUTOR VAZIO
    public Registro() {}


    //GETTERS E SETTERS

    public int getId_registro() {
        return id_registro;
    }

    public void setId_registro(int id_registro) {
        this.id_registro = id_registro;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_remedio() {
        return id_remedio;
    }

    public void setId_remedio(int id_remedio) {
        this.id_remedio = id_remedio;
    }

    public int getId_funcionario() {return id_funcionario;}

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public int getIdade_cliente() {
        return idade_cliente;
    }

    public void setIdade_cliente(int idade_cliente) {
        this.idade_cliente = idade_cliente;
    }

    public String getNome_remedio() {
        return nome_remedio;
    }

    public void setNome_remedio(String nome_remedio) {
        this.nome_remedio = nome_remedio;
    }

    public String getTipo_remedio() {
        return tipo_remedio;
    }

    public void setTipo_remedio(String tipo_remedio) {
        this.tipo_remedio = tipo_remedio;
    }

    public String getNome_funcionario() {
        return nome_funcionario;
    }

    public void setNome_funcionario(String nome_funcionario) {
        this.nome_funcionario = nome_funcionario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Registro{" +
                "id_registro=" + id_registro +
                ", nome_cliente='" + nome_cliente + '\'' +
                ", nome_remedio='" + nome_remedio + '\'' +
                ", tipo_remedio='" + tipo_remedio + '\'' +
                ", nome_funcionario='" + nome_funcionario + '\'' +
                ", quantidade=" + quantidade +
                '}';
    }
}