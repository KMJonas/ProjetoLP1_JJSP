package Models;

public class Prato {
    private int idPrato;
    private String nome;
    private String categoria; //Entrada, prato principal ou sobremesa.
    private double precoCusto;
    private double precoVenda;
    private int unidadetempo;
    private int tempoConsumo;
    private int tempoPreparacao;
    private int estado; //Disponivel (1), Indisponivel (0) para seleção.

    public Prato() {}

    public Prato(int idPrato, String nome, String categoria, double precoCusto, double precoVenda ,int tempoPreparacao, int estado) {
        this.idPrato = idPrato;
        this.nome = nome;
        this.categoria = categoria;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.tempoPreparacao = tempoPreparacao;
        this.estado = estado;
    }



    public int getIdPrato() {
        return idPrato;
    }

    public void setIdPrato(int idPrato) {
        this.idPrato = idPrato;
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
        return unidadetempo;
    }

    public void setUnidadeTempo(int unidadeTempo) {
        this.unidadetempo = unidadeTempo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getTempoPreparacao() {
        return tempoPreparacao;
    }

    public int getTempoConsumo() {
        return unidadetempo;
    }


}
