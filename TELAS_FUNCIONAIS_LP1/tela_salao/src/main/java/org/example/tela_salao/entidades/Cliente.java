package org.example.tela_salao.entidades;

public class Cliente {
    private int id;
    private String nome;
    private String sexo;
    private int idade;

    //CONSTRUTOR
    public Cliente (int id, String nome, String sexo, int idade) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;
    }

    //CONSTRUTOR VAZIO
    public Cliente () {}

    //GETTERS
    public int getId() {return id;}
    public String getNome() {return nome;}
    public String getSexo() {return sexo;}
    public int getIdade() {return idade;}

    //SETTERS
    public void setId(int id) {this.id = id;}
    public void setNome(String nome) {this.nome = nome;}
    public void setSexo(String sexo) {this.sexo = sexo;}
    public void setIdade(int idade) {this.idade = idade;}
}
