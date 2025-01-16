package Models;

public class Mesa {
    private int idMesa;
    private int capacidade;
    private int status; // 1: Livre, 0: Ocupado, 3: Aguardando entrega, 4: Consumo em andamento
    private ClienteReserva reservaAssociada;
    private Pedido pedidoAssociado;

    // Construtor
    public Mesa(int idMesa, int capacidade, int status) {
        this.idMesa = idMesa;
        this.capacidade = capacidade;
        this.status = status;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public int getCapacidade() {
        return capacidade;
    }



    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ClienteReserva getReservaAssociada() {
        return reservaAssociada;
    }

    public void setReservaAssociada(ClienteReserva reservaAssociada) {
        this.reservaAssociada = reservaAssociada;
    }

    public int setCapacidade(int capacidade) {
        return capacidade;
    }

    public int setIdMesa(int i) {
        return i;
    }



    public Pedido getPedidoAssociado() {
        return pedidoAssociado;
    }

    public void setPedidoAssociado(Pedido pedido) {
        this.pedidoAssociado = pedido;
    }
}