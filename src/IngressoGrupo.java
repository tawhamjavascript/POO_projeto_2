import java.util.ArrayList;

public class IngressoGrupo extends Ingresso{
    private ArrayList<Jogo> jogos;
    public IngressoGrupo (int codigo) {
        super(codigo);
    }
    public void adicionar (Jogo jogo) {
        jogos.add(jogo);
    }
    @Override
    public double calcularValor() {
        double soma = 0;
        for (Jogo jogo : jogos) {
            soma += (jogo.getPreco() * 0.9);
        }
        return soma;
    }
}
