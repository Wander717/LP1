package org.example.tela_farmacia.classes;

public class Remedio {
    private String nome_remedio;
    private String tipo_remedio;
    private int id_remedio  ;


    //CONSTRUTOR
    public Remedio (String nome_remedio, String tipo_remedio, int id_remedio) {
        this.nome_remedio = nome_remedio;
        this.tipo_remedio = tipo_remedio;
        this.id_remedio = id_remedio;
    }

    //GETTERS
    public String getNome_remedio() {
        return nome_remedio;
    }
    public String getTipo_remedio() {
        return tipo_remedio;
    }
    public int getId_remedio() {return id_remedio;}
}
