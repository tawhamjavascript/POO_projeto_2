package modelo;

import java.util.ArrayList;

public class Jogo {
    private int id;
    private String data;
    private String local;
    private int estoque;
    private double preco;
    private Time time1;
    private Time time2;
    private ArrayList<Ingresso> ingressos = new ArrayList<Ingresso>();
    public Jogo (int id, String data, String local, int estoque, double preco, Time time1, Time time2) {
        this.id = id;
        this.data = data;
        this.local = local;
        this.estoque = estoque;
        this.preco = preco;
        this.time1 = time1;
        this.time2 = time2;
    }
    public String getData () {
        return data;
    }
    public int getId () {
        return id;
    }
    public String getLocal () {
        return local;
    }
    public double getPreco () {
        return preco;
    }
    public int getEstoque () {
        return estoque;
    }
    public void setEstoque (int number) {
        this.estoque -= number;

    }
    public void remove (Ingresso ingresso) {
        ingressos.remove(ingresso);
    }
    public Time getTime1 () {
        return time1;
    }
    public void setTime1(Time time) {
        this.time1 = time;
    }
    public Time getTime2 () {
        return time2;
    }

    public void setTime2(Time time) {
        this.time2 = time;
    }

    public boolean has_ingresso (int id) {
        for (Ingresso ingresso : ingressos) {
            if (ingresso.getCodigo() == id) {
                return true;
            }
        }
        return false;
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
    public String toString() {
        String resultado =  "\nid=" + getId() + ", data=" + getData() + " , local=" + getLocal() + ", estoque=" + getEstoque() +
                ", preço=" + getPreco() + ", time1=" + time1.getNome() + " x time2=" + time2.getNome();

        resultado += "\n ingressos: ";
        for (Ingresso ingresso: ingressos) {
            resultado += "" + ingresso.getCodigo() + ", ";
        }
        return resultado;
    }
}
