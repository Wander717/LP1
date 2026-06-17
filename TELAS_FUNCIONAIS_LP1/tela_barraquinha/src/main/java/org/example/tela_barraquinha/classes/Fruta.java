// Fruta.java
package org.example.tela_barraquinha.classes;

public class Fruta {
    private int    id_fruta;
    private String nome_fruta;

    public Fruta() {}
    public Fruta(int id_fruta, String nome_fruta) {
        this.id_fruta   = id_fruta;
        this.nome_fruta = nome_fruta;
    }

    public int    getId_fruta()           { return id_fruta; }
    public void   setId_fruta(int id)     { this.id_fruta = id; }
    public String getNome_fruta()         { return nome_fruta; }
    public void   setNome_fruta(String s) { this.nome_fruta = s; }

    @Override
    public String toString() { return nome_fruta; }
}