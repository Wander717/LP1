package org.example.tela_salao.DTO;

import org.example.tela_salao.entidades.Cliente;
import org.example.tela_salao.entidades.Funcionario;
import org.example.tela_salao.entidades.Produto;

public class RegistroTabelaDTO {

    private int id;

    private String funcionario;
    private String cliente;
    private String produto;

    private String tipo;
    private int quantidade;

    // CLIENTE
    public RegistroTabelaDTO(Cliente cliente) {

        this.id = cliente.getId();

        this.cliente = cliente.getNome();

        this.tipo = "-";
        this.quantidade = 0;
    }

    // FUNCIONÁRIO
    public RegistroTabelaDTO(Funcionario funcionario) {

        this.id = funcionario.getId();

        this.funcionario = funcionario.getNome();

        this.tipo = "-";
        this.quantidade = 0;
    }

    // PRODUTO
    public RegistroTabelaDTO(Produto produto) {

        this.id = produto.getId();

        this.produto = produto.getNome();

        this.tipo = produto.getTipo();
        this.quantidade = produto.getQuantidade();
    }

    // GETTERS

    public int getId() {
        return id;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public String getCliente() {
        return cliente;
    }

    public String getProduto() {
        return produto;
    }

    public String getTipo() {
        return tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
