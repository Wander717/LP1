package org.example.tela_salao.entidades;

public class Funcionario {
    private int id;
    private String nome;
    private String sexo;
    private String cargo;
    private int idade;

    //CONSTRUTOR
    public Funcionario(int id, String nome, String sexo, String cargo, int idade) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.cargo = cargo;
        this.idade = idade;
    }

    //CONSTRUTOR VAZIO
    public Funcionario() {}

    //GETTERS
    public int getId() {return id;}
    public String getNome() {return nome;}
    public String getSexo() {return sexo;}
    public String getCargo() {return cargo;}
    public int getIdade() {return idade;}

    //SETTERS
    public void setId(int id) {this.id = id;}
    public void setNome(String nome) {this.nome = nome;}
    public void setSexo(String sexo) {this.sexo = sexo;}
    public void setCargo(String cargo) {this.cargo = cargo;}
    public void setIdade(int idade) {this.idade = idade;}
}