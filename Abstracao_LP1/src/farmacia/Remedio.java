package farmacia;

public class Remedio {
	
	private String nome;
	private String tipo;
	private int quant;
	
	public void setNome (String nome) {
		this.nome = nome;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public void setQuant(int quant) {
		this.quant = quant;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public int getQuant() {
		return quant;
	}
	
	//Adicionar remedio ao estoque
	
	public void adicionarestoque(int qtd) {
	    quant = qtd + quant;
	    System.out.println("\nForam adicionados " + qtd + " remédios");
	}
	
	//Mostrar as informações na tela
	
	public void mostrarinfo() {
	    System.out.println("Nome: " + nome);
	    System.out.println("Tipo: " + tipo);
	    System.out.println("Quantidade: " + quant);
	}
	
	//Vender remédios
	public void venderremedio(int qtd) {
	    if (qtd <= quant) {
	        quant = quant - qtd;
	        System.out.println("\nForam vendidos " + qtd + " remédios");
	    } else {
	        System.out.println("\nEstoque insuficiente!");
	    }
	}
	
	
	
	
	
}
