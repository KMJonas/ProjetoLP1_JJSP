package Models;

public class ClienteReserva {
    private int idReserva;
    private String nome;
    private int numPessoas;
    private int numPessoasEntrada; //Numero de pessoas que vão comer entrada.
    private int numPessoasSobremesa; //Numero de pessoas que vão comer sobremesa.
    private int horaChegada;
    private int tempoMaxEsperaEntrada; //Tempo maximo de espera para a entrar em uma mesa.
    private int tempoMaxEsperaAtendimento; //Tempo maximo de espera para ser atendido.


    public ClienteReserva() {}

    public ClienteReserva(int idCliente, String nome, int numPessoas,int numPessoasEntrada, int numPessoasSobremesa, int horaChegada, int tempoMaxEsperaEntrada, int tempoMaxEsperaAtendimento) {
        this.idReserva = idCliente;
        this.nome = nome;
        this.numPessoas = numPessoas;
        this.numPessoasEntrada = numPessoasEntrada;
        this.numPessoasSobremesa = numPessoasSobremesa;
        this.horaChegada = horaChegada;
        this.tempoMaxEsperaEntrada = tempoMaxEsperaEntrada;
        this.tempoMaxEsperaAtendimento = tempoMaxEsperaAtendimento;
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

    public int getNumPessoasEntrada() {
        return numPessoasEntrada;
    }

    public void setNumPessoasEntrada(int numPessoasEntrada) {
        this.numPessoasEntrada = numPessoasEntrada;
    }

    public int getNumPessoasSobremesa() {
        return numPessoasSobremesa;
    }

    public void setNumPessoasSobremesa(int numPessoasSobremesa) {
        this.numPessoasSobremesa = numPessoasSobremesa;
    }

    public int getHoraChegada() {
        return horaChegada;
    }

    public void setHoraChegada(int horaChegada) {
        this.horaChegada = horaChegada;
    }

    public int getTempoMaxEsperaEntrada() {
        return tempoMaxEsperaEntrada;
    }

    public void setTempoMaxEsperaEntrada(int tempoMaxEsperaEntrada) {
        this.tempoMaxEsperaEntrada = tempoMaxEsperaEntrada;
    }

    public int getTempoMaxEsperaAtendimento() {
        return tempoMaxEsperaAtendimento;
    }

    public void setTempoMaxEsperaAtendimento(int tempoMaxEsperaAtendimento) {
        this.tempoMaxEsperaAtendimento = tempoMaxEsperaAtendimento;
    }
}
