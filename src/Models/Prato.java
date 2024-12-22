package Models;

public class Prato {
    private String nome;
    private String categoria; //Entrada, prato principal ou sobremesa
    private double precoCusto;
    private double precoVenda;
    private int unidadeTempo;
    private int estado; //Disponivel (1), Indisponivel (0)

    public Prato() {}

    public Prato(String nome, String categoria, double precoCusto, double precoVenda, int unidadeTempo, int estado) {
        this.nome = nome;
        this.categoria = categoria;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.unidadeTempo = unidadeTempo;
        this.estado = estado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(double precoCusto) {
        this.precoCusto = precoCusto;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public int getUnidadeTempo() {
        return unidadeTempo;
    }

    public void setUnidadeTempo(int unidadeTempo) {
        this.unidadeTempo = unidadeTempo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
