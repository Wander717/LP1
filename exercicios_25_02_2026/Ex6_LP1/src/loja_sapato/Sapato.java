package loja_sapato;

public class Sapato {
	String marca;
	String cor;
	int tamanho;
	int quantidade;
	
	public Sapato (String marca, String cor, int tamanho, int quantidade) {
		this.marca = marca;
		this.cor = cor;
		this.tamanho = tamanho;
		this.quantidade = quantidade;
	}
	
	public void Informacoes () {
		System.out.println("Marca: " + marca);
		System.out.println ("\nCor: " + cor);
		System.out.println ("\nTamanho: " + tamanho);
		System.out.println ("\nQuantidade: " + quantidade);
	}

	

}
