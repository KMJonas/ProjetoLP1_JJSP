package Models;

import java.util.HashMap;
import java.util.Map;

public class Pedido {
    private int tMomentoAtribuicao;
    private int idPedido;
    private ClienteReserva idReserva;  // Reserva associada
    private Mesa mesaAssociada;
    private Prato[] pratos;
    private int momentoCriacao;
    private int tempoPreparacao;
    private int tempoConsumo;
    private boolean preparado;
    private boolean consumido;
    private boolean prontoParaEntrega;
    private int status;         // 0: Finalizado, 1: Ativo, 2: Entregue, etc.
    private int statusPedido;   // Para controlar etapas do pedido
    private boolean prejuizo;

    // MAPAS ESTÁTICOS para gerenciar as associações:
    //  – Associa Reserva (ID) à Mesa
    //  – Associa Mesa (ID) ao Pedido
    private static Map<Integer, Mesa> reservaMesaMap = new HashMap<>();
    private static Map<Integer, Pedido> mesaPedidoMap = new HashMap<>();

    // Construtor – observe que o seu construtor original exige 9 parâmetros
    public Pedido(int idPedido, ClienteReserva idReserva, Mesa mesaAssociada, Prato[] pratos,
                  int momentoCriacao, int tempoPreparacao, int tempoConsumo, int status, int dummy) {
        this.idPedido = idPedido;
        this.idReserva = idReserva;
        this.mesaAssociada = mesaAssociada;
        this.pratos = pratos;
        this.momentoCriacao = momentoCriacao;
        this.tempoPreparacao = tempoPreparacao;
        this.tempoConsumo = tempoConsumo;
        this.statusPedido = status;
        this.status = status;
        this.preparado = false;
        this.consumido = false;
    }


    public int gettMomentoAtribuicao() {
        return tMomentoAtribuicao;
    }

    public void settMomentoAtribuicao(int tMomentoAtribuicao) {
        this.tMomentoAtribuicao = tMomentoAtribuicao;
    }

    public static void atribuirMesaAReserva(ClienteReserva reserva, Mesa mesa) {
        reservaMesaMap.put(reserva.getIdReserva(), mesa);
    }

    /**
     * Retorna a mesa associada à reserva (ou null se não houver).
     */
    public static Mesa getMesaAssociada(ClienteReserva reserva) {
        return reservaMesaMap.get(reserva.getIdReserva());
    }

    /**
     * Registra um pedido para uma mesa.
     */
    public static void setPedidoAssociado(Mesa mesa, Pedido pedido) {
        mesaPedidoMap.put(mesa.getIdMesa(), pedido);
    }

    /**
     * Retorna o pedido associado à mesa (ou null se não houver).
     */
    public static Pedido getPedidoAssociado(Mesa mesa) {
        return mesaPedidoMap.get(mesa.getIdMesa());
    }

    /**
     * Remove as associações para uma mesa e reserva (usado ao finalizar o pedido).
     */
    public static void removerAssociacoes(Mesa mesa, ClienteReserva reserva) {
        mesaPedidoMap.remove(mesa.getIdMesa());
        reservaMesaMap.remove(reserva.getIdReserva());
    }

    // GETTERS E SETTERS DE INSTÂNCIA

    public boolean isPrejuizo() {
        return prejuizo;
    }

    public void setPrejuizo(boolean prejuizo) {
        this.prejuizo = prejuizo;
    }

    public void setTempoConsumo(int tempoConsumo) {
        this.tempoConsumo = tempoConsumo;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setStatusPedido(int statusPedido) {
        this.statusPedido = statusPedido;
    }

    public int getStatusPedido() {
        return statusPedido;
    }

    public void setProntoParaEntrega(boolean prontoParaEntrega) {
        this.prontoParaEntrega = prontoParaEntrega;
    }

    public boolean isProntoParaEntrega() {
        return prontoParaEntrega;
    }

    public boolean isConsumido() {
        return consumido;
    }

    /**
     * Marca o pedido como finalizado (status 0).
     */
    public void setFinalizado(boolean finalizado) {
        this.status = finalizado ? 0 : this.status;
    }

    /**
     * Define o momento de entrega, atualizando o momento de criação.
     * (momentoEntrega - tempoPreparacao = momentoCriacao)
     */
    public void setMomentoEntrega(int momentoEntrega) {
        this.momentoCriacao = momentoEntrega - tempoPreparacao;
    }

    /**
     * Retorna o momento de entrega (momentoCriacao + tempoPreparacao).
     */
    public int getMomentoEntrega() {
        return momentoCriacao + tempoPreparacao;
    }

    public int getTempoConsumo() {
        return tempoConsumo;
    }

    public int getTempoPreparacao() {
        return tempoPreparacao;
    }

    public int getMomentoCriacao() {
        return momentoCriacao;
    }

    public void setPreparado(boolean preparado) {
        this.preparado = preparado;
    }

    public boolean isPreparado() {
        return preparado;
    }

    public boolean isFinalizado() {
        return status == 0;
    }

    public void setConsumido(boolean consumido) {
        this.consumido = consumido;
    }

    public Mesa getMesaAssociada() {
        return mesaAssociada;
    }

    public Prato[] getPratos() {
        return pratos;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void adicionarPrato(Prato pratoSelecionado) {
        // Se necessário, implementar lógica de inclusão de prato.
    }

    // Método para acesso à reserva associada (pode ser usado para exibir informações)
    public ClienteReserva getReservaAssociada() {
        return idReserva;
    }
}
