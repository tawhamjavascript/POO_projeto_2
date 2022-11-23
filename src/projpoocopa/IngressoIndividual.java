package projpoocopa;

public class IngressoIndividual extends Ingresso {
    private Jogo jogo;

    public IngressoIndividual (int codigo) {
        super(codigo);
    }

    @Override
    public double calcularValor() {
        return 1.2 * jogo.getPreco();
    }
    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public String toString() {
        return "codigo=" + super.getCodigo() + ", jogo=" + jogo.getId();
    }

}
