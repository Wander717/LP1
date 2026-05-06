package barraquinha;

public class Vendedor {

    private String nome;
    private int idade;
    private String barraca;
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setBarraca(String barraca) {
        this.barraca = barraca;
    }
    
    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getBarraca() {
        return barraca;
    }

    
    //Apresentar vendedor
    public void apresentar() {
        System.out.println("Olá! Eu sou " + nome + ", vendedor da barraca " + barraca + ".");
    }
    
    //Mostrar informações na tela
    public void MostrarInfo() {
    	System.out.println("Nome do vendedor: " + nome);
    	System.out.println("\nBarraca: " + barraca);
    	System.out.println("\nIdade: " + idade);
    }
    
    //Verificar idade 
    public void verificarIdade() {
    	if (idade >= 18) {
    		System.out.println("O vendedor " + nome + " já é maior de idade.");
    	}else {
    		System.out.println("Menor de idade. Provavelmente um ajudante!");
    	}
    }





}