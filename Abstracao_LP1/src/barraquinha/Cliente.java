package barraquinha;

public class Cliente {
	
	private String nome;
    private int idade;
    private double dinheiro;
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setDinheiro(double dinheiro) {
        this.dinheiro = dinheiro;
    }
    
    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public double getDinheiro() {
        return dinheiro;
    }
    
    //Apresentar o Cliente
    public void apresentar() {
        System.out.println("Meu nome é " + nome + ", tenho " + idade + " anos e possuo R$ " + dinheiro);
    }

    //Comprou?
    public void comprar() {
        System.out.println(nome + " está comprando produtos na feira.");
    }

    //Obrigado!
    public void agradecer() {
        System.out.println(nome + " agradeceu pela compra.");
    }
    
    
}
