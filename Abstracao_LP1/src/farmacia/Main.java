package farmacia;

public class Main {

	public static void main(String[] args) {
		
		Funcionario funcionario1 = new Funcionario();
		
		funcionario1.setNome("Oliveira");
		funcionario1.setCargo("Chefinho");
		funcionario1.setIdade(20);
		
		funcionario1.mostrarinfo();
		funcionario1.verificarMaioridade();
	}

}
