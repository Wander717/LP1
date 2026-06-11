package org.example.tela_farmacia.entities;

public class Remedio {

    private int id_remedio;
    private String nome_remedio;
    private String tipo_remedio;

    public Remedio() {}

    public Remedio(int id_remedio, String nome_remedio, String tipo_remedio) {
        this.id_remedio = id_remedio;
        this.nome_remedio = nome_remedio;
        this.tipo_remedio = tipo_remedio;
    }

    public Remedio(String nome_remedio, String tipo_remedio) {
        this.nome_remedio = nome_remedio;
        this.tipo_remedio = tipo_remedio;
    }

    public int getId_remedio() { return id_remedio; }
    public void setId_remedio(int id_remedio) { this.id_remedio = id_remedio; }

    public String getNome_remedio() { return nome_remedio; }
    public void setNome_remedio(String nome_remedio) { this.nome_remedio = nome_remedio; }

    public String getTipo_remedio() { return tipo_remedio; }
    public void setTipo_remedio(String tipo_remedio) { this.tipo_remedio = tipo_remedio; }

    @Override
    public String toString() {
        return "Remedio{id_remedio=" + id_remedio +
                ", nome_remedio='" + nome_remedio + '\'' +
                ", tipo_remedio='" + tipo_remedio + '\'' + '}';
    }
}