package modelo;

import java.util.ArrayList;

public class Time {
    private String nome;
    private String origem;
    public ArrayList<Jogo> jogos = new ArrayList<Jogo>();
    public Time (String nome, String origem) {
        this.nome = nome;
        this.origem = origem;
    }
    public void adicionar (Jogo jogo) {
        jogos.add(jogo);
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
    public String toString() {
        String result = "\nnome=" + this.nome + " origem=" + this.origem + " \njogos: ";
        for (Jogo jogo: jogos) {
            result += jogo.getId() + "=" + jogo.getData() + "," + jogo.getLocal() + " ";
        }
        return result;

    }
    
}
