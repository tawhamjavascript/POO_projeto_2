import java.util.ArrayList;

public class Time {
    private String nome;
    private String origem;
    public ArrayList<Jogo> jogos;
    public Time (String nome, String origem) {
        this.nome = nome;
        this.origem = origem;
    }
    public void adicionar (Jogo jogo) {
        jogos.add(jogo);
        jogo.setEstoque(1);
    }
    public String getNome () {
        return this.nome;
    }
    public String getOrigem () {
        return this.origem;
    }
    public double obterValorArrecadado() {
        double soma = 0;
        for (Jogo jogo : jogos) {
            soma += jogo.obterValorArrecadado();

        }
        return soma;
    }

}
