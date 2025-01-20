package Models;

public class ClienteReserva {
    private int idReserva;
    private String nome;
    private int numPessoas;
    private int horaChegada;


    public ClienteReserva() {}

    public ClienteReserva(int idCliente, String nome, int numPessoas, int horaChegada) {
        this.idReserva = idCliente;
        this.nome = nome;
        this.numPessoas = numPessoas;
        this.horaChegada = horaChegada;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idCliente) {
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

}
