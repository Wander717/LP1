package salao;

public class Main {
    public static void main(String[] args) {

        Funcionario f1 = new Funcionario();
        Cliente c1 = new Cliente();
        Produto p1 = new Produto();
        
        
        f1.setNome("Rogério");
        f1.setCargo("Barbeiro");
        f1.setIdade(19);
        
        c1.setNome("Lucas");
        c1.setIdade(16);
        c1.setTelefone("12991235487");
        
        p1.setNome("Minoxidil");
        p1.setTipo("Medicamento");
        p1.setQuantidade(1);
        

        c1.apresentar();
        c1.marcarServico("Corte de cabelo");

        f1.apresentar();
        f1.atenderCliente(c1.getNome());
        f1.iniciarServico();

        p1.mostrarProduto();
        p1.venderProduto();
        p1.reporEstoque(5);

        c1.agradecer();
    }
}