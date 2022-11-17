import java.util.ArrayList;

public class Time {
    public String nome;
    public String origem;
    public ArrayList<Jogo> jogos;
    public Time (String nome, String origem) {
        this.nome = nome;
        this.origem = origem;
    }
    public void adicionar (Jogo jogo) {
        jogos.add(jogo);
    }
    public double obterValorArrecadado() {
        double soma = 0;
        for (Jogo jogo : jogos) {
            soma += jogo.obterValorArrecadado();

        }
        return soma;
    }

}
