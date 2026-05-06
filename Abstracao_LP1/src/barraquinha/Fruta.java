package barraquinha;

public class Fruta {

    private String nome;
    private String estado;
    private int quantidade;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public String getEstado() {
        return estado;
    }

    public int getQuantidade() {
        return quantidade;
    }
    
    //Adicionar frutas ao estoque
    public void adicionarfruta (int maisfrutas) {
    	quantidade = quantidade + maisfrutas;
    	System.out.println("\nFoi adicionado " + maisfrutas + " frutas.");
    }
    
    //Mostrar informações da fruta desejada na tela
    public void mostrarinfo () {
    	System.out.println("Fruta: " + nome);
        System.out.println("\nEstado: " + estado);
        System.out.println("\nQuantidade: " + quantidade);
    }
    
    //Remover frutas do estoque
    public void removerfruta (int menosfrutas ) {
    	if (menosfrutas <= quantidade) { 
    		quantidade = quantidade - menosfrutas;
    		System.out.println("\nFoi removido " + menosfrutas + " frutas.");
    	}else {
    		System.out.println("Não pode acabar com todas as frutas!");
    	}
    }
    
    
}