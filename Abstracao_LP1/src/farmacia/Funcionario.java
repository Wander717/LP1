package farmacia;

public class Funcionario {
	private String nome;
	private String cargo;
	private int idade;
	
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getCargo() {
		return cargo;
	}
	
	public int getIdade() {
		return idade;
	}
	
	//Mostra informações na tela
	public void mostrarinfo () {
    	System.out.println("Funcionário: " + nome);
        System.out.println("\nCargo: " + cargo);
        System.out.println("\nIdade: " + idade);
    }
	
	//Verifica se o funcionário é maior de idade
	public void verificarMaioridade() { 
		if (idade >= 18) {
			System.out.println("O funcionário " + nome + " é maior de idade.");
		}else {
			System.out.println("O funcionário " + nome + " é menor de idade.");
		}
	}
	
	//Mostra que um determinado funcionário iniciou o seu turno
		public void iniciarTurno() {
			System.out.println("O funcionário " + nome + " iniciou o seu turno.");
		}
    
	
	
	
	
}
