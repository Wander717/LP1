package salao;

public class Produto {

    private String nome;
    private String tipo;
    private int quantidade;

    public void setNome(String nome) { this.nome = nome; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }


    // GETTERS
    public String getNome() { return nome; }
    public String getTipo() { return tipo; }
    public int getQuantidade() { return quantidade; }


    //Mostrar produto
    public void mostrarProduto() {
        System.out.println("Produto: " + nome + "\nTipo: " + tipo + "\nEstoque: " + quantidade);
    }

    //Venda
    public void venderProduto() {
        if (quantidade > 0) {
            quantidade--;
            System.out.println("Venda realizada: " + nome + ". Estoque restante: " + quantidade);
        } else {
            System.out.println("Produto " + nome + " esgotado.");
        }
    }
    
    //Repor estoque de produto
    public void reporEstoque(int quantidadeNova) {
        quantidade += quantidadeNova;
        System.out.println("Estoque de " + nome + " atualizado. Nova quantidade: " + quantidade);
    }
}