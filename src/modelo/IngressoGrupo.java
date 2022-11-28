package modelo;

import java.util.ArrayList;

public class IngressoGrupo extends Ingresso{
    private ArrayList<Jogo> jogos = new ArrayList<Jogo>();
    public IngressoGrupo (int codigo) {
        super(codigo);
    }
    public void adicionar (Jogo jogo) {
        jogos.add(jogo);
    }
    public ArrayList<Jogo> getJogos () { return jogos; }
    @Override
    public double calcularValor() {
        double soma = 0;
        for (Jogo jogo : jogos) {
            soma += (jogo.getPreco() * 0.9);
        }
        return soma;
    }
    public void remove (Jogo jogo) {
        jogos.remove(jogo);
    }

    @Override
    public int getCodigo() {
        return super.getCodigo();
    }

    @Override
    public String toString() {
        String result = "codigo=" + super.getCodigo() + ", jogos: ";
        for (Jogo jogo: jogos) {
            result += jogo.getId() + ", ";

        }
        return result;
    }
}