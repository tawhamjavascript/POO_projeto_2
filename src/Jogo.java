import java.util.ArrayList;

public class Jogo {
    public int id;
    public String data;
    public String local;
    public int estoque;
    public double preco;
    public Time time1;
    public Time time2;
    public ArrayList<Ingresso> ingressos;
    public Jogo (int id, String data, String local, int estoque, double preco, Time time1, Time time2) {
        this.id = id;
        this.data = data;
        this.local = local;
        this.estoque = estoque;
        this.preco = preco;
        this.time1 = time1;
        this.time2 = time2;
    }
    public void setTime1(Time time) {
        this.time1 = time;
    }
    public void setTime2(Time time) {
        this.time2 = time;
    }
    public void adicionar (Ingresso ing) {
        ingressos.add(ing);
        estoque -= 1;
    }
    public double obterValorArrecadado() {
        double soma = 0;
        for (Ingresso ingresso : ingressos) {
            soma += ingresso.calcularValor();

        }
        return soma;
    }
}
