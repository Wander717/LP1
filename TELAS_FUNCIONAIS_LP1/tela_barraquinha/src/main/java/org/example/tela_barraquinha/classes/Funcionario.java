// Funcionario.java
package org.example.tela_barraquinha.classes;

public class Funcionario {
    private int    id_funcionario;
    private String nome_funcionario;

    public Funcionario() {}
    public Funcionario(int id_funcionario, String nome_funcionario) {
        this.id_funcionario   = id_funcionario;
        this.nome_funcionario = nome_funcionario;
    }

    public int    getId_funcionario()              { return id_funcionario; }
    public void   setId_funcionario(int id)        { this.id_funcionario = id; }
    public String getNome_funcionario()            { return nome_funcionario; }
    public void   setNome_funcionario(String s)    { this.nome_funcionario = s; }

    @Override
    public String toString() { return nome_funcionario; }
}