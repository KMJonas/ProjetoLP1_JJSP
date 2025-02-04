package Models;

public class Prato {
    private int idPrato;
    private String nome;
    private String categoria; //Entrada, prato principal ou sobremesa.
    private double precoCusto;
    private double precoVenda;
    private int unidadeTempoPreparacao; //Unidade tempo para a sua preparação
    private int unidadeTempoConsumo; //Unidade tempo para o seu consumo
    private int estado; //Disponivel (1), Indisponivel (0) para seleção.

    public Prato() {}

    public Prato(int idPrato, String nome, String categoria, double precoCusto, double precoVenda, int unidadeTempoPreparacao,int unidadeTempoConsumo, int estado) {
        this.idPrato = idPrato;
        this.nome = nome;
        this.categoria = categoria;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.unidadeTempoPreparacao = unidadeTempoPreparacao;
        this.unidadeTempoConsumo = unidadeTempoConsumo;
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

    public int getunidadeTempoPreparacao() {
        return unidadeTempoPreparacao;
    }

    public void setunidadeTempoPreparacao(int unidadeTempo) {
        this.unidadeTempoPreparacao = unidadeTempo;
    }

    public int getunidadeTempoConsumo() {
        return unidadeTempoConsumo;
    }

    public void setunidadeTempoConsumo(int unidadeTempo) {
        this.unidadeTempoConsumo = unidadeTempo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getUnidadeTempoPreparacao() {
        return unidadeTempoPreparacao;
    }

    public void setUnidadeTempoPreparacao(int unidadeTempoPreparacao) {
        this.unidadeTempoPreparacao = unidadeTempoPreparacao;
    }

    public int getUnidadeTempoConsumo() {
        return unidadeTempoConsumo;
    }

    public void setUnidadeTempoConsumo(int unidadeTempoConsumo) {
        this.unidadeTempoConsumo = unidadeTempoConsumo;
    }
}
