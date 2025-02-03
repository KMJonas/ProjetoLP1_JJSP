package Models;

public class Estatisticas {
    private double totalFaturado;
    private double totalGastos;
    private double totalLucro;
    private int numPedidosAtendidos;
    private int numPedidosNaoAtendidos;
    private Prato pratoMaisPedido;
    private int totalClientesAtendidos;
    private double tempoMedioEspera;
    private double tempoMedioTotal;

    public Estatisticas() {
        this.totalFaturado = 0;
        this.totalGastos = 0;
        this.totalLucro = 0;
        this.numPedidosAtendidos = 0;
        this.numPedidosNaoAtendidos = 0;
        this.pratoMaisPedido = null;
        this.totalClientesAtendidos = 0;
        this.tempoMedioEspera = 0;
        this.tempoMedioTotal = 0;
    }

    // Getters e Setters
    public double getTotalFaturado() {
        return totalFaturado;
    }

    public void setTotalFaturado(double totalFaturado) {
        this.totalFaturado = totalFaturado;
    }

    public double getTotalGastos() {
        return totalGastos;
    }

    public void setTotalGastos(double totalGastos) {
        this.totalGastos = totalGastos;
    }

    public double getTotalLucro() {
        return totalLucro;
    }

    public void setTotalLucro(double totalLucro) {
        this.totalLucro = totalLucro;
    }

    public int getNumPedidosAtendidos() {
        return numPedidosAtendidos;
    }

    public void setNumPedidosAtendidos(int numPedidosAtendidos) {
        this.numPedidosAtendidos = numPedidosAtendidos;
    }

    public int getNumPedidosNaoAtendidos() {
        return numPedidosNaoAtendidos;
    }

    public void setNumPedidosNaoAtendidos(int numPedidosNaoAtendidos) {
        this.numPedidosNaoAtendidos = numPedidosNaoAtendidos;
    }

    public Prato getPratoMaisPedido() {
        return pratoMaisPedido;
    }

    public void setPratoMaisPedido(Prato pratoMaisPedido) {
        this.pratoMaisPedido = pratoMaisPedido;
    }

    public int getTotalClientesAtendidos() {
        return totalClientesAtendidos;
    }

    public void setTotalClientesAtendidos(int totalClientesAtendidos) {
        this.totalClientesAtendidos = totalClientesAtendidos;
    }

    public double getTempoMedioEspera() {
        return tempoMedioEspera;
    }

    public void setTempoMedioEspera(double tempoMedioEspera) {
        this.tempoMedioEspera = tempoMedioEspera;
    }

    public double getTempoMedioTotal() {
        return tempoMedioTotal;
    }

    public void setTempoMedioTotal(double tempoMedioTotal) {
        this.tempoMedioTotal = tempoMedioTotal;
    }

    public void atualizarEstatisticas(double totalFaturado, double totalGastos, double totalLucro,
                                      int numPedidosAtendidos, int numPedidosNaoAtendidos,
                                      Prato pratoMaisPedido, int totalClientesAtendidos,
                                      double tempoMedioEspera, double tempoMedioTotal) {
        this.totalFaturado = totalFaturado;
        this.totalGastos = totalGastos;
        this.totalLucro = totalLucro;
        this.numPedidosAtendidos = numPedidosAtendidos;
        this.numPedidosNaoAtendidos = numPedidosNaoAtendidos;
        this.pratoMaisPedido = pratoMaisPedido;
        this.totalClientesAtendidos = totalClientesAtendidos;
        this.tempoMedioEspera = tempoMedioEspera;
        this.tempoMedioTotal = tempoMedioTotal;
    }
}
