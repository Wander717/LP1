package farmacia;

public class Cliente {

    private String nome;
    private int idade;
    private String telefone;


    // GETTERS
    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getTelefone() {
        return telefone;
    }

    // SETTERS
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    //Apresentar cliente
    public void apresentar() {
        System.out.println("Olá, meu nome é " + nome + ", tenho " + idade + " anos e meu telefone é " + telefone + ".");
    }

    //Verificar se é maior de idade
    public void verificarMaioridade() { 
		if (idade >= 18) {
			System.out.println("O cliente " + nome + " é maior de idade.");
		}else {
			System.out.println("O cliente " + nome + " é menor de idade.");
		}
	}

    //Mostrar informações na tela.
    public void mostrarinfo() {
	    System.out.println("Nome: " + nome);
	    System.out.println("\nTelefone: " + telefone);
	    System.out.println("\nIdade: " + idade);
	}
}