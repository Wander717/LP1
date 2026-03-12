package salao;

public class Funcionario {

	    private String nome;
	    private String cargo;
	    private int idade;

	    public void setNome(String nome) { this.nome = nome; }
	    public void setCargo(String cargo) { this.cargo = cargo; }
	    public void setIdade(int idade) { this.idade = idade; }
	    
	    
	    public String getNome() { return nome; }
	    public String getCargo() { return cargo; }
	    public int getIdade() { return idade; }

	 

	    //Apresentar-se
	    public void apresentar() {
	        System.out.println("Olá! Sou " + nome + ", trabalho como " + cargo + " e tenho " + idade + " anos.");
	    }
	    
	    //Atender cliente
	    public void atenderCliente(String nomeCliente) {
	        System.out.println(nome + " está atendendo o cliente " + nomeCliente);
	    }

	    //Inicar expediente
	    public void iniciarServico() {
	        System.out.println(nome + " começou o serviço.");
	    }
	    
	    //Mostrar informações na tela
	    public void mostrarinfo() {
	    	System.out.println("Nome: " + nome);
	    	System.out.println("\nCargo: " + cargo);
	    	System.out.println("\nIdade: " + idade);
	    }
	
}
