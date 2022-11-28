package modelo;

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
    public Jogo getJogo() {return jogo;}

    @Override
    public int getCodigo() {
        return super.getCodigo();
    }

    public String toString() {
        return "codigo = " + super.getCodigo() + ", jogo = " + jogo.getId();
    }

}
