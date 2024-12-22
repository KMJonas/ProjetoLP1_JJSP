package Models;

public class Cliente {
    private int idCliente;
    private String nome;
    private int numPessoas;
    private int horaChegada;
    private int tempoMaxEspera;

    public Cliente() {}

    public Cliente(int idCliente, String nome, int numPessoas, int horaChegada, int tempoMaxEspera) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.numPessoas = numPessoas;
        this.horaChegada = horaChegada;
        this.tempoMaxEspera = tempoMaxEspera;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
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
