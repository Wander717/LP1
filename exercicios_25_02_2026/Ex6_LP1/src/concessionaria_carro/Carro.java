package concessionaria_carro;

public class Carro {
	String nome;
	String fabricante;
	int ano;
	double cilindrada;
	
	public Carro (String nome, String fabricante, int ano, double cilindrada) {
		this.nome = nome;
		this.fabricante = fabricante;
		this.ano = ano;
		this.cilindrada = cilindrada;
		
	}
	
	public void Exibir () { 
		System.out.println("Nome: " + nome);
		System.out.println("\nFabricante: " + fabricante);
		System.out.println("\nAno: " + ano);
		System.out.println("\nCilindrada: " + cilindrada);
		
	}
	
	

}
