package Models;

public class Mesa {
    private int idMesa;
    private int capacidade;
    private int status; // 0: Livre,10: Ocupado, 3: Aguardando entrega, 4: Consumo em andamento

    // Construtor
    public Mesa(){

    }
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

    public int setCapacidade(int capacidade) {
        return capacidade;
    }

    public int setIdMesa(int i) {
        return i;
    }

}