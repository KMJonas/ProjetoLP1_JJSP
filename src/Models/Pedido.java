package Models;

import java.util.ArrayList;

public class Pedido {
    private int idPedido;
    private Mesa mesaAssociada;
    private Prato[] listaPratos;
    private double custoTotal;
    private double lucro;
    private int status; //Ativo (1) , Desativo (0)

    public Pedido() {}

    public Pedido(int idPedido, Mesa mesaAssociada, Prato[] listaPratos, double custoTotal, double lucro, int status) {
        this.idPedido = idPedido;
        this.mesaAssociada = mesaAssociada;
        this.listaPratos = listaPratos;
        this.custoTotal = custoTotal;
        this.lucro = lucro;
        this.status = status;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Mesa getMesaAssociada() {
        return mesaAssociada;
    }

    public void setMesaAssociada(Mesa mesaAssociada) {
        this.mesaAssociada = mesaAssociada;
    }

    public Prato[] getListaPratos() {
        return listaPratos;
    }

    public void setListaPratos(Prato[] listaPratos) {
        this.listaPratos = listaPratos;
    }

    public double getCustoTotal() {
        return custoTotal;
    }

    public void setCustoTotal(double custoTotal) {
        this.custoTotal = custoTotal;
    }

    public double getLucro() {
        return lucro;
    }

    public void setLucro(double lucro) {
        this.lucro = lucro;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
