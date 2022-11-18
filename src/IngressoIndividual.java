public class IngressoIndividual extends Ingresso {
    public Jogo jogo;

    public IngressoIndividual (int codigo) {
        super(codigo);
    }

    @Override
    public double calcularValor() {
        return 1.2 * jogo.getPreco();
    }
}
