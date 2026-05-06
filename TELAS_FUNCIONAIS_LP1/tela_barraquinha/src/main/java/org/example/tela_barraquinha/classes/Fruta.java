package org.example.tela_barraquinha.classes;

public class Fruta {

    private Cliente cliente;
    private String nomeFruta;
    private String estado;
    private int quantidade;

    // Construtor completo
    public Fruta(Cliente cliente, String nomeFruta, String estado, int quantidade) {
        this.cliente = cliente;
        this.nomeFruta = nomeFruta;
        this.estado = estado;
        this.quantidade = quantidade;
    }

    // Construtor vazio
    public Fruta() {
    }

    // GETTERS
    public Cliente getCliente() {
        return cliente;
    }

    public String getNomeFruta() {
        return nomeFruta;
    }

    public String getEstado() {
        return estado;
    }

    public int getQuantidade() {
        return quantidade;
    }

    // SETTERS
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setNomeFruta(String nomeFruta) {
        this.nomeFruta = nomeFruta;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}