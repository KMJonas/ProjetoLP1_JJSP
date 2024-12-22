package Models;

public class ClienteReserva {
    private int idReserva;
    private String nome;
    private int numPessoas;
    private int horaChegada;
    private int tempoMaxEspera;

    public ClienteReserva() {}

    public ClienteReserva(int idCliente, String nome, int numPessoas, int horaChegada, int tempoMaxEspera) {
        this.idReserva = idCliente;
        this.nome = nome;
        this.numPessoas = numPessoas;
        this.horaChegada = horaChegada;
        this.tempoMaxEspera = tempoMaxEspera;
    }

    public int getIdCliente() {
        return idReserva;
    }

    public void setIdCliente(int idCliente) {
        this.idReserva = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumPessoas() {
        return numPessoas;
    }

    public void setNumPessoas(int numPessoas) {
        this.numPessoas = numPessoas;
    }

    public int getHoraChegada() {
        return horaChegada;
    }

    public void setHoraChegada(int horaChegada) {
        this.horaChegada = horaChegada;
    }

    public int getTempoMaxEspera() {
        return tempoMaxEspera;
    }

    public void setTempoMaxEspera(int tempoMaxEspera) {
        this.tempoMaxEspera = tempoMaxEspera;
    }
}
