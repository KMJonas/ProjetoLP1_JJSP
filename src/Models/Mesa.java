package Models;

public class Mesa {
    private int idMesa;
    private int capacidade;
    private int status; //Livre (1), Ocupado (0)

    public Mesa() {}

    public Mesa(int idMesa, int capacidade, int status) {
        this.idMesa = idMesa;
        this.capacidade = capacidade;
        this.status = status;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
