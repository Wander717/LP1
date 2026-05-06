package barraquinha;

public class Main {

	public static void main(String[] args) {
		Fruta frutinha1 = new Fruta();
		
		frutinha1.setNome("Maracujá");
		frutinha1.setEstado("OK");
		frutinha1.setQuantidade(50);
		
		frutinha1.adicionarfruta(4);
		frutinha1.removerfruta(4);
		frutinha1.mostrarinfo();
		
		

	}

}
