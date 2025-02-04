package Models;

import java.util.HashMap;
import java.util.Map;

public class Pedido {
    private static int idIncrement = 1;
    private int idPedido;
    private ClienteReserva reserva;  // Reserva associada
    private Mesa mesaAssociada;
    private Prato[] pratos;
    private int tMomentoAtribuicaoMesa;
    private int momentoCriacao;
    private int tempoPreparacao;
    private int tempoConsumo;
    private boolean preparado;
    private boolean consumido;
    private int status;         // 1: Ativo, 2: Espera de ser atendido, 3: Espera de comida, 4: Pronto para entrega, 5:Comida entregue, 6:Consumido, 7-Finalizado
    private boolean prejuizo;
    private int horaFinalizacao;
    private int momentoEntregaPrato;


    public Pedido(ClienteReserva reserva, Mesa mesaAssociada, Prato[] pratos, int tMomentoAtribuicaoMesa,
                  int momentoCriacao, int tempoPreparacao, int tempoConsumo, int status) {
        this.idPedido = idIncrement++;
        this.reserva = reserva;
        this.mesaAssociada = mesaAssociada;
        this.pratos = pratos;
        this.tMomentoAtribuicaoMesa = tMomentoAtribuicaoMesa;
        this.momentoCriacao = momentoCriacao;
        this.tempoPreparacao = tempoPreparacao;
        this.tempoConsumo = tempoConsumo;
        this.status = status;
    }

    public int getmomentoEntregaPrato(){return momentoEntregaPrato;}

    public void setmomentoEntregaPrato(int momentoEntregaPrato){
        this.momentoEntregaPrato = momentoEntregaPrato;
    }

    public int getHoraFinalizacao(){return horaFinalizacao;}

    public void setHoraFinalizacao(int horaFinalizacao){
        this.horaFinalizacao = horaFinalizacao;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public ClienteReserva getReserva() {
        return reserva;
    }

    public void setReserva(ClienteReserva reserva) {
        this.reserva = reserva;
    }

    public Mesa getMesaAssociada() {
        return mesaAssociada;
    }

    public void setMesaAssociada(Mesa mesaAssociada) {
        this.mesaAssociada = mesaAssociada;
    }

    public Prato[] getPratos() {
        return pratos;
    }

    public void setPratos(Prato[] pratos) {
        this.pratos = pratos;
    }

    public int gettMomentoAtribuicaoMesa() {
        return tMomentoAtribuicaoMesa;
    }

    public void settMomentoAtribuicaoMesa(int tMomentoAtribuicaoMesa) {
        this.tMomentoAtribuicaoMesa = tMomentoAtribuicaoMesa;
    }

    public int getMomentoCriacao() {
        return momentoCriacao;
    }

    public void setMomentoCriacao(int momentoCriacao) {
        this.momentoCriacao = momentoCriacao;
    }

    public int getTempoPreparacao() {
        return tempoPreparacao;
    }

    public void setTempoPreparacao(int tempoPreparacao) {
        this.tempoPreparacao = tempoPreparacao;
    }

    public int getTempoConsumo() {
        return tempoConsumo;
    }

    public void setTempoConsumo(int tempoConsumo) {
        this.tempoConsumo = tempoConsumo;
    }

    public boolean isPreparado() {
        return preparado;
    }

    public void setPreparado(boolean preparado) {
        this.preparado = preparado;
    }

    public boolean isConsumido() {
        return consumido;
    }

    public void setConsumido(boolean consumido) {
        this.consumido = consumido;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isPrejuizo() {
        return prejuizo;
    }

    public void setPrejuizo(boolean prejuizo) {
        this.prejuizo = prejuizo;
    }
}
