package modelo;

public abstract class Ingresso {
    private final int codigo;

    public Ingresso (int codigo) {
        this.codigo = codigo;

    }

    public abstract double calcularValor();
    public int getCodigo () {
        return codigo;
    }
    public abstract String toString();
}
