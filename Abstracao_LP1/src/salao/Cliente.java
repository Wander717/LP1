package salao;

public class Cliente {

    private String nome;
    private int idade;
    private String telefone;

    public void setNome(String nome) { this.nome = nome; }
    public void setIdade(int idade) { this.idade = idade; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getNome() { return nome; }
    public int getIdade() { return idade; }
    public String getTelefone() { return telefone; }


    //Apresentar-se
    public void apresentar() {
        System.out.println("Meu nome é " + nome + ", tenho " + idade + " anos. Contato: " + telefone);
    }

    //Marcar serviço com o funcionário
    public void marcarServico(String servico) {
        System.out.println(nome + " marcou o serviço: " + servico);
    }

    //Agradecer o funcionário
    public void agradecer() {
        System.out.println(nome + " agradeceu pelo atendimento.");
    }
    
    //Mostrar informações
    public void mostrarinfo() {
    	System.out.println("Cliente: " + nome);
    	System.out.println("\nTelefone: " + telefone);
    	System.out.println("\nIdade: " + idade);
    }
}